package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Pet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractPetDaoFile implements PetDao{

    protected File file;
    protected List<Pet> pets;

    public AbstractPetDaoFile(){
        setup();
    }

    /**
     * setup method has to create file that is used to store data and read stored data (deserialize) into the pets variable
     */
    protected abstract void setup();

    /**
     * Persist method has to save list of pets into the file with appropriate serialization
     * @throws IOException in case when not able to save to file
     */
    protected abstract void persist() throws IOException;

    /**
     * Saves pet into the file
     * @param pet pet instance
     * @return saved pet
     */
    @Override
    public Pet save(Pet pet) throws IOException {
        this.pets.add(pet);
        this.persist();
        return pet;
    }

    /**
     * Lists all pets in the storage
     * @return list of all pets
     */
    @Override
    public List<Pet> getAll() {
        return this.pets;
    }

    /**
     * Delete pet from the file
     * @param pet to delete
     * @return deleted pet
     */
    @Override
    public Pet delete(Pet pet) throws IOException {
        this.pets.remove(pet);
        this.persist();
        return pet;
    }

    /**
     * overwrites the pet with given ID. If not found update doesn't do any changes
     * @param pet to update
     * @return updated pet
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
     * finds a pet with given ID
     * @param id of the pet
     * @return Pet or null
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
     * Search pet by name
     * @param criteria - name of the pet. This can be update to have more flexible search
     * @return Pet or null
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
