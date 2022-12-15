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
            history.setGenerated(rs.getDate("generated"));
            history.setQuote(DaoFactory.quoteDao().getById(rs.getInt("quote_id")));
            return history;
        }catch (SQLException e){
            throw new QuoteException(e.getMessage(), e);
        }

    }

    @Override
    public Map<String, Object> object2row(QuoteHistory object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("quote_id", object.getQuote().getId());
        item.put("generated", object.getGenerated());
        return item;
    }

    @Override
    public List<QuoteHistory> getByDateRange(Date start, Date end) throws QuoteException {
        List<QuoteHistory> histories = new ArrayList<>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM quote_history WHERE generated BETWEEN ? AND ?");
            stmt.setObject(1, start);
            stmt.setObject(2, end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                histories.add(row2object(rs));
            }
            rs.close();
            return histories;
        }catch (SQLException e){
            throw new QuoteException(e.getMessage(), e);
        }
    }
}
