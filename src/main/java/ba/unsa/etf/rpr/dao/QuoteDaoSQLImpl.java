package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuoteDaoSQLImpl implements QuoteDao{

    private Connection connection;

    public QuoteDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/root", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Quote getById(int id) {
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

    /**
     * @author ahajro2
     * @param id for searching category for quotes
     * @return specific Category for specific quote from db
     */

    public Category returnCategoryForId(int id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                return c;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author ahajro2
     * @param text search string for quotes
     * @return list of quotes
     */

    @Override
    public List<Quote> searchByText(String text) {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM quotes WHERE quote LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Quote> quoteLista = new ArrayList<>();
            while(rs.next()) {
                Quote q = new Quote();
                q.setId(rs.getInt(1));
                q.setQuote(rs.getString(2));
                q.setCategory(returnCategoryForId(rs.getInt(4)));
                q.setCreated(rs.getDate(3));
                quoteLista.add(q);
            }
            return quoteLista;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @author ahajro2
     * @param category search string for quotes
     * @return list of quotes
     */

    @Override
    public List<Quote> searchByCategory(Category category) {
        String query = "SELECT * FROM quotes WHERE category = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, category.getId());
            ResultSet rs = stmt.executeQuery();
            ArrayList<Quote> quoteLista = new ArrayList<>();
            while(rs.next()) {
                Quote q = new Quote();
                q.setId(rs.getInt(1));
                q.setQuote(rs.getString(2));
                q.setCategory(category);
                q.setCreated(rs.getDate(3));
                quoteLista.add(q);
            }
            return quoteLista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
