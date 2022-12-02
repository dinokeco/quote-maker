package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.domain.QuoteHistory;
import com.sun.org.apache.xpath.internal.operations.Quo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteHistoryDaoSQLImpl implements QuoteHistoryDao{
    private Connection conn;

    public QuoteHistoryDaoSQLImpl() {
        try {
            this.conn= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582258", "sql7582258", "m6JTHrdhjw");
        } catch (SQLException e) {
            System.out.println("Greska u radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public QuoteHistory getById(int id) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM categories WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                QuoteHistory quoteHistory = new QuoteHistory();
                quoteHistory.setId(rs.getInt("id"));
                quoteHistory.setQuote(QuoteDaoSQLImpl(getQuoteById(rs.getString("quote")))); //ovo samo kad se doda implementacija i toga ce raditi....
                rs.close();
                return quoteHistory;
            }else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.conn.prepareStatement("SELECT MAX(id)+1 FROM quote_history");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
      return id;
    }

    @Override
    public QuoteHistory add(QuoteHistory item) {
        int id = getMaxId();
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO quote_history VALUES (id, item.getQuote().getId(), item.getGenerated())");
            stmt.executeUpdate();
            item.setId(id);
            return item;
        } catch (SQLException e) {
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public QuoteHistory update(QuoteHistory item) {
        try{
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE quote_history SET quote=?, generated=? WHERE id=?");
            stmt.setInt(1, item.getQuote().getId());
            stmt.setDate(2, (java.sql.Date) item.getGenerated());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try{
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM quote_history WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<QuoteHistory> getAll() {
        List<QuoteHistory> histories = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM quote_history");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                QuoteHistory quoteHistory = new QuoteHistory();
                quoteHistory.setId(rs.getInt("id"));
                quoteHistory.setQuote(QuoteDaoSQLImpl(getQuoteById(rs.getString("quote")))); //ovo samo kad se doda implementacija i toga ce raditi....
                histories.add(quoteHistory);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return histories;
    }

    @Override
    public List<QuoteHistory> getByDateRange(Date start, Date end) {
        List<QuoteHistory> histories = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM quote_history WHERE generated BETWEEN start AND end");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                QuoteHistory quoteHistory = new QuoteHistory();
                quoteHistory.setId(rs.getInt("id"));
                quoteHistory.setQuote(QuoteDaoSQLImpl(getQuoteById(rs.getString("quote")))); //ovo samo kad se doda implementacija i toga ce raditi....
                histories.add(quoteHistory);
            }
            rs.close();
        }catch (SQLException e){
            System.out.println("Problem pri radu sa bazom podataka");
            System.out.println(e.getMessage());
        }
        return histories;
    }
}
