package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Quote;
import ba.unsa.etf.rpr.exceptions.QuoteException;

import java.util.List;

public class QuoteManager {

    public List<Quote> getAll() throws QuoteException {
        return DaoFactory.quoteDao().getAll();
    }

    public List<Quote> searchQuotes(String text) throws QuoteException {
        return DaoFactory.quoteDao().searchByText(text);
    }

    public void delete(int id) throws QuoteException{
        DaoFactory.quoteDao().delete(id);
    }

    public Quote getById(int quoteId) throws QuoteException{
        return DaoFactory.quoteDao().getById(quoteId);
    }

    public void update(Quote q) throws QuoteException{
        DaoFactory.quoteDao().update(q);
    }

    public Quote add(Quote q) throws QuoteException{
        return DaoFactory.quoteDao().add(q);
    }

    public Quote randomQuote() throws QuoteException{
        return DaoFactory.quoteDao().randomQuote();
    }

}
