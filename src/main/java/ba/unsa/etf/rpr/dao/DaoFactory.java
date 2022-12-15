package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Dino Keco
 */
public class DaoFactory {

    private static final CategoryDao categoryDao = new CategoryDaoSQLImpl();
    private static final QuoteDao quoteDao = new QuoteDaoSQLImpl();
    private static final QuoteHistoryDao quoteHistoryDao = new QuoteHistoryDaoSQLImpl();

    private DaoFactory(){
    }

    public static CategoryDao categoryDao(){
        return categoryDao;
    }

    public static QuoteDao quoteDao(){
        return quoteDao;
    }

    public static QuoteHistoryDao quoteHistoryDao(){
        return quoteHistoryDao;
    }

}


