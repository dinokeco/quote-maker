package ba.unsa.etf.rpr.p6;

import ba.unsa.etf.rpr.p6.dao.PetDao;
import ba.unsa.etf.rpr.p6.dao.PetDaoJavaImpl;
import ba.unsa.etf.rpr.p6.dao.PetDaoJsonImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{

        Pet p = new Pet();
        p.setId(3);
        p.setName("Figaro2");

        PetDao dao = new PetDaoJsonImpl();
        //dao.save(p);
       //dao.update(p);
       dao.delete(p);
        List<Pet> pets = dao.getAll();
        System.out.println(pets);
        //System.out.println(p);
    }
}
