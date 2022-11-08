package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Toy;

import java.io.IOException;
import java.util.List;

/**
 * Interface that declares how instances of Toy class will be stored into file or database or any other storage.
 */
public interface ToyDao {

    public Toy save(Toy toy) throws IOException;

    public List<Toy> getAll();

    public Toy delete(Toy toy) throws IOException;

    public Toy update(Toy toy) throws IOException;

    public Toy get(Integer id);

    public Toy search(String criteria);

}
