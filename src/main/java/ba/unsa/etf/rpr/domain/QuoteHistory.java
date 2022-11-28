package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Holds history of all generated quotes
 *
 * @author Dino Keco
 */
public class QuoteHistory {
    private int id;
    private Quote quote;
    private Date generated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public Date getGenerated() {
        return generated;
    }

    public void setGenerated(Date generated) {
        this.generated = generated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteHistory that = (QuoteHistory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quote, generated);
    }
}
