import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;

public interface Reader {

    public void  setNextReader(Reader nextReader);
    public ArrayList<Reactor> readFile(String file) throws IOException, XMLStreamException;
}
