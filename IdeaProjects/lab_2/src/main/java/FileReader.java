import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private void setSource(ArrayList<Reactor> reactors, String source ){
        for(Reactor reactor:reactors){
            reactor.setDownload_source(source);
        }
    }
    public ArrayList<Reactor> getFromJSON(String json) throws IOException {
        File file = new File(json);
        ObjectMapper mapper  = new ObjectMapper();
        ArrayList<Reactor> collection = (ArrayList<Reactor>) mapper.readValue(file, new TypeReference<List<Reactor>>() {});
        setSource(collection, "json");
        return collection;
    }

    public ArrayList<Reactor> getFromYaml(String yaml) throws IOException{
        File file = new File(yaml);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ArrayList<Reactor> collection = (ArrayList<Reactor>) mapper.readValue(file, new TypeReference<List<Reactor>>() {});
        setSource(collection,"yaml");
        return collection;
    }

    public ArrayList<Reactor> getFromXML(String xml) throws IOException, JAXBException, XMLStreamException {

        Reactor reactor =new Reactor();
        ArrayList<Reactor> reactors = new ArrayList<Reactor>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(xml));
        while (reader.hasNext())
        {
            XMLEvent event = reader.nextEvent();
            if(event.isStartElement())
            {
                StartElement start_element = event.asStartElement();
                if(start_element.getName().getLocalPart().equals("Reactor"))
                {
                    reactor = new Reactor();
                }
                if(start_element.getName().getLocalPart().equals("type"))
                {
                    event = reader.nextEvent();
                    reactor.setType(event.asCharacters().getData());
                }
                if(start_element.getName().getLocalPart().equals("burnup")){
                    event = reader.nextEvent();
                    reactor.setBurnup(Float.parseFloat(event.asCharacters().getData()));
                }
                if(start_element.getName().getLocalPart().equals("kpd")){
                    event = reader.nextEvent();
                    reactor.setKpd(Float.parseFloat(event.asCharacters().getData()));
                }
                if(start_element.getName().getLocalPart().equals("enrichment")){
                    event = reader.nextEvent();
                    reactor.setEnrichment(Float.parseFloat(event.asCharacters().getData()));
                }
                if(start_element.getName().getLocalPart().equals("termal_capacity")){
                    event = reader.nextEvent();
                    reactor.setTermal_capacity(Float.parseFloat(event.asCharacters().getData()));
                }
                if(start_element.getName().getLocalPart().equals("electrical_capacity")){
                    event = reader.nextEvent();
                    reactor.setElectrical_capacity(Float.parseFloat(event.asCharacters().getData()));
                }
                if(start_element.getName().getLocalPart().equals("life_time")){
                    event = reader.nextEvent();
                    reactor.setLife_time(Float.parseFloat(event.asCharacters().getData()));
                }
                if(start_element.getName().getLocalPart().equals("first_load")){
                    event = reader.nextEvent();
                    reactor.setFirst_load(Float.parseFloat(event.asCharacters().getData()));
                }
            }
            if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals("Reactor"))
                {
                    reactors.add(reactor);
                }
            }
            setSource(reactors, "xml");
        }
        return reactors;
    }
}
