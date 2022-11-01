package ba.unsa.etf.rpr.p6;

import java.io.*;

public class LaptopDaoSerializableFileImpl implements LaptopDao{

    private File file;

    public LaptopDaoSerializableFileImpl() throws IOException {
        this.file = new File("myObjects.txt");
    }

    @Override
    public void save(Laptop laptop) throws IOException {
        FileOutputStream f = new FileOutputStream(this.file);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(laptop);
        o.close();
        f.close();
    }

    @Override
    public Laptop get() throws IOException {
        return null;
    }

}
