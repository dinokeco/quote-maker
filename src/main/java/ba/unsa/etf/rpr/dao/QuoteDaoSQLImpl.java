package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;

import java.sql.*;
import java.util.List;

public class QuoteDaoSQLImpl implements QuoteDao{

    private Connection connection;

    public QuoteDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/root", "root", "root");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Quote getById(int id) {
        String query = "SELECT * FROM quotes WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Quote quote = new Quote();
                quote.setId(rs.getInt("id"));
                quote.setQuote(rs.getString("quote"));
                quote.setCreated(rs.getDate("created"));
                rs.close();
                return quote;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }


    @Override
    public Quote add(Quote item) {
        return null;
    }


    @Override
    public Quote update(Quote item) {
        return null;
    }


    @Override
    public void delete(int id) {

    }

    @Override
    public List<Quote> getAll() {
        return null;
    }

    @Override
    public List<Quote> searchByText(String text) {
        return null;
    }

    @Override
    public List<Quote> getByCategory(Category category) {
        return null;
    }
}
