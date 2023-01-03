package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.QuoteException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of the DAO
 * @author Dino Keco
 */
public class CategoryDaoSQLImpl extends AbstractDao<Category> implements CategoryDao{
     private static  CategoryDaoSQLImpl instance = null;
    private CategoryDaoSQLImpl() {
        super("categories");
    }

    public static CategoryDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new CategoryDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Category row2object(ResultSet rs) throws QuoteException {
        try {
            Category cat = new Category();
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            return cat;
        } catch (SQLException e) {
            throw new QuoteException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Category object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        return row;
    }
}