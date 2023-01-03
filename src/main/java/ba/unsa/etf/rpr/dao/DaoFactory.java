package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Dino Keco
 */
public class DaoFactory {

    private static final CategoryDao categoryDao = CategoryDaoSQLImpl.getInstance();
    private static final QuoteDao quoteDao = QuoteDaoSQLImpl.getInstance();
    private static final QuoteHistoryDao quoteHistoryDao = QuoteHistoryDaoSQLImpl.getInstance();


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


