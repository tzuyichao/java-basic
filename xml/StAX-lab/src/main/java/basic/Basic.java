package basic;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Basic {
    public static void dumpXMLEvent(XMLEvent xmlEvent) {
        System.out.println("Event Type: " + xmlEvent.getEventType());
        System.out.println("isNamespace: " + xmlEvent.isNamespace());
        System.out.println("isStartDocument: " + xmlEvent.isStartDocument());
        if(xmlEvent.isStartDocument()) {
            System.out.println(xmlEvent.toString());
        }
        System.out.println("isStartElement " + xmlEvent.isStartElement());
        if(xmlEvent.isStartElement()) {
            StartElement startElement = xmlEvent.asStartElement();
            System.out.println("name: " + startElement.getName().getLocalPart());
        }
        if(xmlEvent.isEndElement()) {
            EndElement endElement = xmlEvent.asEndElement();
            System.out.println("End of " + endElement.getName().getLocalPart());
        }
        System.out.println("==============================");
    }

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/resources/example.xml"));

        while(xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            dumpXMLEvent(xmlEvent);
        }
    }
}
