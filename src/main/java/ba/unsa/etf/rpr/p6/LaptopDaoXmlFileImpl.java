package ba.unsa.etf.rpr.p6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LaptopDaoXmlFileImpl implements LaptopDao{

    private File file;

    public LaptopDaoXmlFileImpl(){
        this.file = new File("myObjects.xml");
    }

    @Override
    public void save(Laptop laptop) throws IOException {

        XmlMapper mapper = new XmlMapper();
        String xml = mapper.writeValueAsString(laptop);

        FileOutputStream fo = new FileOutputStream(this.file);
        fo.write(xml.getBytes());
        fo.close();
    }

    /**
     * @return
     * @throws IOException
     */
    @Override
    public Laptop get() throws IOException {
        return null;
    }
}
