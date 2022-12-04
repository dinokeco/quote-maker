package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.CategoryDao;
import ba.unsa.etf.rpr.dao.CategoryDaoSQLImpl;
import ba.unsa.etf.rpr.dao.QuoteDao;
import ba.unsa.etf.rpr.dao.QuoteDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Hello world!
 * comment by dino
 * saso mange
 */
public class App {
    public static void main(String[] args) {
//    {
//        CategoryDao dao = new CategoryDaoSQLImpl();
//
//
//        List<Category> categories = dao.getAll();
//        System.out.println(categories);
//
//        Category c2 = new Category();
//        c2.setId(2);
//        c2.setName("Courage");
//        dao.delete(2);
//        categories = dao.getAll();
//        System.out.println(categories);
//    }

        QuoteDao dao = new QuoteDaoSQLImpl() ;

        Category category = new Category();
        category.setId(2);
        category.setName("Narodne izreke");
        ArrayList<Quote> quotesByCategory = new ArrayList<Quote>(dao.searchByCategory(category));
        System.out.println("Treba ispisati 2 quote-a po ovoj kategoriji: ");
        quotesByCategory.forEach(q -> System.out.println(q.getQuote()));


        System.out.println("\n Jedan quote sa inside word \"gora\": ");
        ArrayList<Quote> quotes = new ArrayList<Quote>(dao.searchByText("gora"));
        for (Quote q : quotes) {
            System.out.println(q.getQuote());
        }
    }
}