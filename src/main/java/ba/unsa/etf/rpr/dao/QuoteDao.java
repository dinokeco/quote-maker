package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Quote;

import java.util.List;

/**
 * Dao interface for Quote domain bean
 *
 * @author Dino Keco
 */
public interface QuoteDao extends Dao<Quote>{

    /**
     * Returns all quotes that contains given text.
     *
     * @param text search string for quotes
     * @return list of quotes
     */
    List<Quote> searchByText(String text);

    /**
     * Returns all quotes that contains given text.
     *
     * @param category search string for quotes
     * @return list of quotes
     */
    List<Quote> searchByCategory(Category category);
}
