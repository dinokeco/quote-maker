package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.QuoteHistory;
import ba.unsa.etf.rpr.exceptions.QuoteException;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * MySQL implementation of DAO
 * @author Dino Keco
 */
public class QuoteHistoryDaoSQLImpl extends AbstractDao<QuoteHistory> implements QuoteHistoryDao{
    public QuoteHistoryDaoSQLImpl() {
        super("quote_history");
    }

    @Override
    public QuoteHistory row2object(ResultSet rs) throws QuoteException {
        try{
            QuoteHistory history = new QuoteHistory();
            history.setId(rs.getInt("id"));
            history.setCreated(rs.getDate("created"));
            history.setQuote(DaoFactory.quoteDao().getById(rs.getInt("quote_id")));
            return history;
        }catch (SQLException e){
            throw new QuoteException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(QuoteHistory object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("quote_id", object.getQuote().getId());
        item.put("created", object.getCreated());
        return item;
    }

    @Override
    public List<QuoteHistory> getByDateRange(Date start, Date end) throws QuoteException {
        return executeQuery("SELECT * FROM quote_history WHERE generated BETWEEN ? AND ?", new Object[]{start, end});
    }
}
