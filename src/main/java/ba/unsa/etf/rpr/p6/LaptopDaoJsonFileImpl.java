package ba.unsa.etf.rpr.p6;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LaptopDaoJsonFileImpl implements LaptopDao{

    private File file;

    private List<Laptop> items = new ArrayList<Laptop>();

    public LaptopDaoJsonFileImpl(){
        this.file = new File("myObjects.json");
        try {
            this.items = (new ObjectMapper()).readValue(this.file, new TypeReference<List<Laptop>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Laptop laptop) throws IOException {
        this.items.add(laptop);
        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(this.items);
        FileOutputStream fo = new FileOutputStream(this.file);
        fo.write(json.getBytes());
        fo.close();
    }

    @Override
    public Laptop get() throws IOException {
        return this.items.get(0);
    }
}
