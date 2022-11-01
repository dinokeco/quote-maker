package ba.unsa.etf.rpr.p6;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface LaptopDao {

    public void save(Laptop laptop) throws IOException;

    public Laptop get() throws IOException;
}
