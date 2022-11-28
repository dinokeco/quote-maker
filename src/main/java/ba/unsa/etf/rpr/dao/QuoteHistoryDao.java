package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.QuoteHistory;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for QuoteHistory domain bean
 *
 * @author Dino Keco
 */
public interface QuoteHistoryDao extends Dao<QuoteHistory> {

    /**
     * Search quote history in database based on date range
     * @param start start date
     * @param end end date
     * @return List of quotes from history table
     */
    List<QuoteHistory> getByDateRange(Date start, Date end);
}
