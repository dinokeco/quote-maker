package ba.unsa.etf.rpr.p6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LaptopDaoJsonFileImpl implements LaptopDao{

    private File file;

    public LaptopDaoJsonFileImpl(){
        this.file = new File("myObjects.json");
    }

    @Override
    public void save(Laptop laptop) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(laptop);

        FileOutputStream fo = new FileOutputStream(this.file);
        fo.write(json.getBytes());
        fo.close();
    }

    @Override
    public Laptop get() throws IOException {
        Laptop laptop = (new ObjectMapper()).readValue(new File("myObjects.json"), Laptop.class);
        return laptop;
    }
}
