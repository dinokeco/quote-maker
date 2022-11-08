package ba.unsa.etf.rpr.p6;

import ba.unsa.etf.rpr.p6.dao.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{

        Toy t = new Toy();
        t.setId(1);
        t.setName("Autic2");

        Toy t2 = new Toy();
        t2.setId(2);
        t2.setName("Kliker");

        ToyDao dao = new ToyDaoJson();


        List<Toy> list = dao.getAll();
        System.out.println(list);

        //dao.save(t2);
       // dao.save(t2);
        dao.update(t);


        list = dao.getAll();
        System.out.println(list);


    }
}
