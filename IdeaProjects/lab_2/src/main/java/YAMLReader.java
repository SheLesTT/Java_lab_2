import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YAMLReader implements Reader {
    private Reader nextReader;

    @Override
    public void setNextReader(Reader nextReader) {
        this.nextReader = nextReader;
    }

    @Override
    public ArrayList<Reactor> readFile(String address) throws IOException, XMLStreamException {
        if (address.endsWith("ReactorsYAML.yaml")) {
            File file = new File(address);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            ArrayList<Reactor> collection = (ArrayList<Reactor>) mapper.readValue(file, new TypeReference<List<Reactor>>() {
            });

            return collection;
        } else {
            return nextReader.readFile(address);
        }


    }
}
