package ba.unsa.etf.rpr.p6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        LaptopDao dao = new LaptopDaoJsonFileImpl();
        //dao.save(new Laptop("macbook", "apple"));
        Laptop l = dao.get();
        System.out.println(l);
    }
}
