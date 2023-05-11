import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;

public class Chain_file_reader {
    Reader jsonReader = new JSONReader();
    Reader yamlReaer = new YAMLReader();
    Reader xmlReader = new XMLReader();

    public ArrayList<Reactor> read_file(String file) throws XMLStreamException, IOException {
        jsonReader.setNextReader(yamlReaer);
        yamlReaer.setNextReader(xmlReader);
        xmlReader.setNextReader(null);

        return  jsonReader.readFile(file);
    }

}
