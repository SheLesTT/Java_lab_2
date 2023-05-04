import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONReader implements Reader {
    private Reader nextReader;

    @Override
    public void setNextReader(Reader nextReader) {
        this.nextReader = nextReader;
    }

    @Override
    public ArrayList<Reactor> readFile(String address) throws IOException, XMLStreamException {
        if (address.endsWith("ReactorsJSON.json")) {

            File file = new File(address);
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Reactor> collection = (ArrayList<Reactor>) mapper.readValue(file, new TypeReference<List<Reactor>>() {
            });
            // source is not setting any more
//            setSource(collection, "json");
            return collection;

        } else {
            return nextReader.readFile(address);
        }
    }
}
