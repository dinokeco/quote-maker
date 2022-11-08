package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Toy;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToyDaoJson implements ToyDao{

    private List<Toy> toys;

    public ToyDaoJson(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.toys = mapper.readValue(new File("toys.json"), new TypeReference<List<Toy>>(){});
        } catch (IOException e) {
            // ignore exception
            this.toys = new ArrayList<Toy>();
        }
    }
    /**
     * @param toy
     * @return
     * @throws IOException
     */
    @Override
    public Toy save(Toy toy) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.toys.add(toy);
        mapper.writeValue(new File("toys.json"), this.toys);
        return toy;
    }

    /**
     * @return
     */
    @Override
    public List<Toy> getAll() {
        return this.toys;
    }

    /**
     * @param toy
     * @return
     * @throws IOException
     */
    @Override
    public Toy delete(Toy toy) throws IOException {
        this.toys.remove(toy);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("toys.json"), this.toys);
        return toy;
    }

    /**
     * @param toy
     * @return
     * @throws IOException
     */
    @Override
    public Toy update(Toy toy) throws IOException {
        for(int i = 0 ; i< this.toys.size(); i++){
            if (this.toys.get(i).getId().equals(toy.getId())){
                this.toys.set(i, toy);
                break;
            }
        }
        return toy;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Toy get(Integer id) {
        for(Toy toy : this.toys){
            if (toy.getId().equals(id)){
                return toy;
            }
        }
        return null;
    }

    /**
     * @param criteria
     * @return
     */
    @Override
    public Toy search(String criteria) {
        for(Toy toy : this.toys){
            if (toy.getName().contains(criteria)){
                return toy;
            }
        }
        return null;
    }
}
