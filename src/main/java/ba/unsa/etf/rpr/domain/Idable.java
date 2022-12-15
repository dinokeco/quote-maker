package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have ID field. Name is stupid but per standard
 */
public interface Idable {

    void setId(int id);

    int getId();
}
