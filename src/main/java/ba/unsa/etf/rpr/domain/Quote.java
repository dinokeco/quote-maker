package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * bean for quote
 * @author Dino Keco
 */
public class Quote {

    private int id;
    private String quote;
    private Category category;
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                ", category=" + category +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return id == quote.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quote, category, created);
    }
}
