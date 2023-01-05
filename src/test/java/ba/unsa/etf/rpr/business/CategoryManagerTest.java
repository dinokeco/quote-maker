//package ba.unsa.etf.rpr.business;
//
//import ba.unsa.etf.rpr.dao.CategoryDaoSQLImpl;
//import ba.unsa.etf.rpr.dao.DaoFactory;
//import ba.unsa.etf.rpr.domain.Category;
//import ba.unsa.etf.rpr.exceptions.QuoteException;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//
//
///**
// * @author abrulic1
// *
// */
//class CategoryManagerTest {
//
//    private CategoryManager categoryManager;
//    private Category category;
//
//    /**
//     * This method will be called before each test method
//     */
//    @BeforeEach
//    public void initializeObjectsWeNeed(){
//        categoryManager = new CategoryManager();
//        category = new Category();
//        category.setName("Poslovice");
//        category.setId(50);
//    }
//
//    /**
//     * In this method we will test validateCategoryName(String name) for correct and incorrect passed parameters
//     */
//    @Test
//    void validateCategoryName() {
//        String correctName = "Citati";
//        try {
//            categoryManager.validateCategoryName(correctName);
//        } catch (QuoteException e) {
//            //Test will fall if method validateCategoryName(name) throws an exception for correct parameter
//            e.printStackTrace();
//            Assertions.assertTrue(false);
//        }
//
//        String incorrectNameShort = "A";
//        QuoteException quoteException1 = Assertions.assertThrows(QuoteException.class, () -> {
//            categoryManager.validateCategoryName(incorrectNameShort);}, "Category name must be between 3 and 45 chars");
//        Assertions.assertEquals("Category name must be between 3 and 45 chars", quoteException1.getMessage());
//
//        String incorrectNameLong = RandomStringUtils.randomAlphabetic(50);
//        QuoteException quoteException2 = Assertions.assertThrows(QuoteException.class, () -> {
//            categoryManager.validateCategoryName(incorrectNameLong);}, "Category name must be between 3 and 45 chars");
//        Assertions.assertEquals("Category name must be between 3 and 45 chars", quoteException2.getMessage());
//    }
//
//
//    /**
//     * We are testing add() method. An explanation will be covered in the comments below
//     */
//    @Test
//    void add() {
//        CategoryDaoSQLImpl categoryDaoSQL = Mockito.mock(CategoryDaoSQLImpl.class);
//        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
//        daoFactoryMockedStatic.when(DaoFactory::categoryDao).thenReturn(categoryDaoSQL);
//     /*
//     EXPLANATION:
//       As you can see, we are using DaoFactory.categoryDao().add(cat) in CategoryManager.java class (line 29 of the current project version).
//       Because we are using method from another class, we should use 'mocking'.
//       You should notice that DaoFactory.categoryDao() is a static method call, so we cannot use 'mocking' as we learned before.
//       For mocking of static method we need:
//         1. dependency for mockito-inline (lines [77, 81] in pom.xml file of the current project version)
//         2. dependency for mockito-core must be 3.5.0 or higher, because this solution to 'mock' a static method is not supported in older versions
//
//       Now pay attention on these following code lines (67. and 68. line):
//          MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
//          daoFactoryMockedStatic.when(DaoFactory::categoryDao).thenReturn(categoryDaoSQL);
//
//       Since we need categoryDao method from DaoFactory.java which is static method, we have to create a MockedStatic<DaoFactory> instance.
//       Also, when categoryDao method is called in the CategoryManager.java class, instance of CategoryDaoSQLImpl.java class should be returned.
//       That instance can be mock instance as well, or it can be real instance that will represent our DAO layer for 'categories' table from the database.
//       In this test method we used mock instance because we are not interested in real state of our database, we just need an instance.
//          (Actually, that should be a dummy instance of CategoryDaoSQLImpl because we don't need mocked version, but to avoid complicating code with adding new interfaces or something else, we will use mock instance)
//       So, we should add one more line of code before previous lines (66. line):
//          CategoryDaoSQLImpl categoryDaoSQL = Mockito.mock(CategoryDaoSQLImpl.class);
//       So, whenever DaoFactory.categoryDao is called in the method 'add' from CategoryManager.java class, mock instance of CategoryDaoSQLImpl will be returned
//      */
//
//
//        /*
//        An exception will be thrown because our instance of Category.java class has value for id
//         */
//        QuoteException quoteException = Assertions.assertThrows(QuoteException.class, () -> {
//            categoryManager.add(category);}, "Can't add category with ID. ID is autogenerated");
//        Assertions.assertEquals("Can't add category with ID. ID is autogenerated", quoteException.getMessage());
//
//        category.setId(0);
//
//        /*
//        Now it won't be thrown any exception when we try to add a new category to the table because we used a mocked version of the CategoryDaoSQLImpl.java instance.
//        You can try to use one real instance of CategoryDaoSQLImpl in the code above (line 66), so you will see that an exception will be thrown because we already have the category 'Poslovice' in the database.
//        */
//        try {
//            categoryManager.add(category);
//            Assertions.assertTrue(true);
//        } catch (QuoteException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        daoFactoryMockedStatic.verify(DaoFactory::categoryDao);
//        daoFactoryMockedStatic.close();
//    }
//
//
//    /**
//     * In this method we will try to add one category that is already in the table
//     */
//    @Test
//    public void add2(){
//        CategoryDaoSQLImpl categoryDaoSQL = CategoryDaoSQLImpl.getInstance();
//        try {
//            categoryDaoSQL.add(category);
//            Assertions.assertTrue(false);
//        } catch (QuoteException e) {
//            //It will be thrown an exception, so this test will pass in that case
//            Assertions.assertTrue(true);
//        }
//    }
//
//}