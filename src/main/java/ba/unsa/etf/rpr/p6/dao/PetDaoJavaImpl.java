package ba.unsa.etf.rpr.p6.dao;

import ba.unsa.etf.rpr.p6.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistance into file using Java Serialization
 * @author Dino Keco
 */
public class PetDaoJavaImpl extends AbstractPetDaoFile {

    @Override
    protected void setup() {
        this.file = new File("mypets.dat");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.file));
            List<Pet> o = (List)in.readObject();
            this.pets = o;
        }catch (Exception e){
            this.pets = new ArrayList<Pet>();
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void persist() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.file));
        out.writeObject(this.pets);
        out.close();
    }

}
