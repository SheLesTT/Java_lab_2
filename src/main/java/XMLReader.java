import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader implements Reader {
    private Reader nextReader;

    @Override
    public void setNextReader(Reader nextReader) {
        this.nextReader = nextReader;
    }

    @Override
    public ArrayList<Reactor> readFile(String address) throws IOException, XMLStreamException {

        if(address.endsWith("ReactorsXML.xml")) {
        Reactor reactor = new Reactor();
        ArrayList<Reactor> reactors = new ArrayList<Reactor>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(address));
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement start_element = event.asStartElement();
                if (start_element.getName().getLocalPart().equals("Reactor")) {
                    reactor = new Reactor();
                }
                if (start_element.getName().getLocalPart().equals("type")) {
                    event = reader.nextEvent();
                    reactor.setType(event.asCharacters().getData());
                }
                if (start_element.getName().getLocalPart().equals("burnup")) {
                    event = reader.nextEvent();
                    reactor.setBurnup(Float.parseFloat(event.asCharacters().getData()));
                }
                if (start_element.getName().getLocalPart().equals("kpd")) {
                    event = reader.nextEvent();
                    reactor.setKpd(Float.parseFloat(event.asCharacters().getData()));
                }
                if (start_element.getName().getLocalPart().equals("enrichment")) {
                    event = reader.nextEvent();
                    reactor.setEnrichment(Float.parseFloat(event.asCharacters().getData()));
                }
                if (start_element.getName().getLocalPart().equals("termal_capacity")) {
                    event = reader.nextEvent();
                    reactor.setTermal_capacity(Float.parseFloat(event.asCharacters().getData()));
                }
                if (start_element.getName().getLocalPart().equals("electrical_capacity")) {
                    event = reader.nextEvent();
                    reactor.setElectrical_capacity(Float.parseFloat(event.asCharacters().getData()));
                }
                if (start_element.getName().getLocalPart().equals("life_time")) {
                    event = reader.nextEvent();
                    reactor.setLife_time(Float.parseFloat(event.asCharacters().getData()));
                }
                if (start_element.getName().getLocalPart().equals("first_load")) {
                    event = reader.nextEvent();
                    reactor.setFirst_load(Float.parseFloat(event.asCharacters().getData()));
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("Reactor")) {
                    reactors.add(reactor);
                }
            }

        }
        return reactors;
    }
        else{
            System.out.println("Only can read yaml, json or xml ");
            return null;
        }
}

}
