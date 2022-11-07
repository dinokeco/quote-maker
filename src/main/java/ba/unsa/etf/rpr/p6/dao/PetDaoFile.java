package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Pet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class PetDaoFile implements PetDao{

    protected File file;
    protected List<Pet> pets;

    protected abstract void persist() throws IOException;

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet save(Pet pet) throws IOException {
        this.pets.add(pet);
        this.persist();
        return pet;
    }

    /**
     * @return
     */
    @Override
    public List<Pet> getAll() {
        return this.pets;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet delete(Pet pet) throws IOException {
        this.pets.remove(pet);
        this.persist();
        return pet;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet update(Pet pet) throws IOException {
        for (int i = 0 ; i< this.pets.size(); i++){
            if (this.pets.get(i).getId().equals(pet.getId())){
                this.pets.set(i, pet);
                break;
            }
        }
        this.persist();
        return pet;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Pet get(Integer id) {
        for(Pet p : this.pets){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    /**
     * @param criteria
     * @return
     */
    @Override
    public Pet search(String criteria) {
        for(Pet p : this.pets){
            if (p.getName().equals(criteria)){
                return p;
            }
        }
        return null;
    }
}
