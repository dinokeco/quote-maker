package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 * @author Dino Keco
 */
public class QuoteDaoSQLImpl extends AbstractDao<Quote> implements QuoteDao {

    public QuoteDaoSQLImpl() {
        super("quotes");
    }

    @Override
    public Quote row2object(ResultSet rs) throws QuoteException{
        try {
            Quote q = new Quote();
            q.setId(rs.getInt("id"));
            q.setQuote(rs.getString("quote"));
            q.setCreated(rs.getDate("created"));
            q.setCategory(DaoFactory.categoryDao().getById(rs.getInt("category_id")));
            return q;
        } catch (Exception e) {
            throw new QuoteException(e.getMessage(), e);
        }
    }

    /**
     * @param object - object to be mapped
     * @return map representation of object
     */
    @Override
    public Map<String, Object> object2row(Quote object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("quote", object.getQuote());
        item.put("created", object.getCreated());
        item.put("category_id", object.getCategory().getId());
        return item;
    }

    /**
     * @param text search string for quotes
     * @return list of quotes
     * @author ahajro2
     */

    @Override
    public List<Quote> searchByText(String text) throws QuoteException{
        return executeQuery("SELECT * FROM quotes WHERE quote LIKE concat('%', ?, '%')", new Object[]{text});
    }

    /**
     * @param category search string for quotes
     * @return list of quotes
     * @author ahajro2
     */
    @Override
    public List<Quote> searchByCategory(Category category) throws QuoteException{
        return executeQuery("SELECT * FROM quotes WHERE category_id = ?", new Object[]{category.getId()});
    }

    /**
     * @return random quote from DB
     * @throws QuoteException in case of error working with db
     */
    @Override
    public Quote randomQuote() throws QuoteException {
        return executeQueryUnique("SELECT * FROM quotes ORDER BY RAND() LIMIT 1", null);
    }
}
