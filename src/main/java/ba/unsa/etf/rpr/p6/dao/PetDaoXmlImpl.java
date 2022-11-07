package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Pet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistance into file using XML serialization with Jackson Library
 */
public class PetDaoXmlImpl extends AbstractPetDaoFile {

    private XmlMapper mapper;

    @Override
    protected void setup() {
        this.file = new File("mypets.xml");
        this.mapper = new XmlMapper();

        try {
            this.pets = mapper.readValue(this.file, new TypeReference<List<Pet>>(){});
        } catch (IOException e) {
            this.pets = new ArrayList<Pet>();
            // ignore exception and create empty list
        }
    }


    @Override
    protected void persist() throws IOException {
        try {
            this.mapper.writeValue(this.file, this.pets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
