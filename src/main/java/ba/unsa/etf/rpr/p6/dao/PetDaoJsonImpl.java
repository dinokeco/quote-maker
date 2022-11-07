package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PetDaoJsonImpl extends PetDaoFile{

    private ObjectMapper mapper;

    public PetDaoJsonImpl(){
        this.file = new File("mypets.json");
        this.mapper = new ObjectMapper();

        try {
            this.pets = mapper.readValue(this.file, new TypeReference<List<Pet>>(){});
        } catch (IOException e) {
            this.pets = new ArrayList<Pet>();
            throw new RuntimeException(e);
        }
    }

    protected void persist(){
        try {
            this.mapper.writeValue(this.file, this.pets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
