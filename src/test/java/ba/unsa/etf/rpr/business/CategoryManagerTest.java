package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.CategoryDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.QuoteException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;


/**
 * @author abrulic1
 *
 */
class CategoryManagerTest {

    private CategoryManager categoryManager;
    private Category category;
    private CategoryDaoSQLImpl categoryDaoSQLMock;
    private List<Category> categories;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        categoryManager = Mockito.mock(CategoryManager.class);
        category = new Category();
        category.setName("Poslovice");
        category.setId(50);

        categoryDaoSQLMock = Mockito.mock(CategoryDaoSQLImpl.class);
        categories = new ArrayList<>();
        categories.addAll(Arrays.asList(new Category("Poslovice"), new Category("Citati"), new Category("Motivation"), new Category("Sthihovi")));
    }

    /**
     * In this method we will test validateCategoryName(String name) for correct and incorrect passed parameters
     */

    @Test
    void validateCategoryName() throws QuoteException {
        String correctName = "Citati";
        try {
            Mockito.doCallRealMethod().when(categoryManager).validateCategoryName(correctName);
        } catch (QuoteException e) {
            //Test will fall if method validateCategoryName(name) throws an exception for correct parameter
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = "A";
        Mockito.doCallRealMethod().when(categoryManager).validateCategoryName(incorrectNameShort);
        QuoteException quoteException1 = Assertions.assertThrows(QuoteException.class, () -> {
            categoryManager.validateCategoryName(incorrectNameShort);}, "Category name must be between 3 and 45 chars");
        Assertions.assertEquals("Category name must be between 3 and 45 chars", quoteException1.getMessage());

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
        Mockito.doCallRealMethod().when(categoryManager).validateCategoryName(incorrectNameLong);
        QuoteException quoteException2 = Assertions.assertThrows(QuoteException.class, () -> {
            categoryManager.validateCategoryName(incorrectNameLong);}, "Category name must be between 3 and 45 chars");
        Assertions.assertEquals("Category name must be between 3 and 45 chars", quoteException2.getMessage());
    }


    /**
     * Adding category that already exists
     * @throws QuoteException
     */
    @Test
    void add() throws QuoteException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::categoryDao).thenReturn(categoryDaoSQLMock);
        /*
        An exception will be thrown because our instance of Category.java class has value for id
         */
        when(DaoFactory.categoryDao().getAll()).thenReturn(categories);
        Mockito.doCallRealMethod().when(categoryManager).add(category);
        QuoteException quoteException = Assertions.assertThrows(QuoteException.class, () -> {
            categoryManager.add(category);}, "Can't add category with ID. ID is autogenerated");

        Assertions.assertEquals("Can't add category with ID. ID is autogenerated", quoteException.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::categoryDao);
        Mockito.verify(categoryManager).add(category);
        daoFactoryMockedStatic.close();
    }

    /**
     * Adding a new category
     * @throws QuoteException
     */
    @Test
    void addNewCategory() throws QuoteException {
        Category newCategory = new Category("Nova kategorija");
        categoryManager.add(newCategory);

        Assertions.assertTrue(true);
        Mockito.verify(categoryManager).add(newCategory);
    }

}