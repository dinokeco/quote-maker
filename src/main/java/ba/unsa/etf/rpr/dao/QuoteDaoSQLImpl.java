package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;

import java.sql.*;
import java.util.ArrayList;
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
     * @param object
     * @return
     */
    @Override
    public Map<String, Object> object2row(Quote object) {
        Map<String, Object> item = new TreeMap<String, Object>();
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
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM quotes WHERE quote LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Quote> quoteLista = new ArrayList<>();
            while (rs.next()) {
                quoteLista.add(row2object(rs));
            }
            return quoteLista;
        } catch (SQLException e) {
            throw new QuoteException(e.getMessage(), e);
        }
    }

    /**
     * @param category search string for quotes
     * @return list of quotes
     * @author ahajro2
     */
    @Override
    public List<Quote> searchByCategory(Category category) throws QuoteException{
        String query = "SELECT * FROM quotes WHERE category_id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, category.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Quote> quoteLista = new ArrayList<>();
            while (rs.next()) {
                quoteLista.add(row2object(rs));
            }
            return quoteLista;
        } catch (SQLException e) {
            throw new QuoteException(e.getMessage(), e);
        }
    }

    /**
     * @return
     * @throws QuoteException
     */
    @Override
    public Quote randomQuote() throws QuoteException {
        String query = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return row2object(rs);
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new QuoteException(e.getMessage(), e);
        }
    }
}
