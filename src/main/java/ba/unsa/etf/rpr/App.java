package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.domain.QuoteHistory;
import ba.unsa.etf.rpr.exceptions.QuoteException;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Hello world!
 * comment by dino
 * saso mange
 */
public class App {
    public static void main(String[] args) throws Exception {
        Quote cat = DaoFactory.quoteDao().getById(1);
        System.out.println(cat);

        /*Quote q = DaoFactory.quoteDao().getById(1);

        QuoteHistory h = new QuoteHistory();
        h.setQuote(q);
        h.setGenerated(new Date());
        DaoFactory.quoteHistoryDao().add(h);

        Date start = new Date();
        start.setTime(1671024938);
        Date end = new Date();
        end.setTime(1671197738);
        List<QuoteHistory> history = DaoFactory.quoteHistoryDao().getByDateRange(start, end);
        System.out.println(q);
        System.out.println(cat);
        System.out.println(h);
        System.out.println(history);*/
    }
}