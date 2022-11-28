package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.CategoryDao;
import ba.unsa.etf.rpr.dao.CategoryDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Category;

import java.util.List;
import java.util.Stack;

/**
 * Hello world!
 * comment by dino
 * saso mange
 */
public class App 
{
    public static void main( String[] args )
    {
        CategoryDao dao = new CategoryDaoSQLImpl();


        List<Category> categories = dao.getAll();
        System.out.println(categories);

        Category c2 = new Category();
        c2.setId(2);
        c2.setName("Courage");
        dao.delete(2);
        categories = dao.getAll();
        System.out.println(categories);
    }
}
