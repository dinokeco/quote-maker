package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Pet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PetDao {

    public Pet save(Pet pet) throws IOException;

    public List<Pet> getAll();

    public Pet delete(Pet pet) throws IOException;

    public Pet update(Pet pet) throws IOException;

    public Pet get(Integer id);

    public Pet search(String criteria);

}
