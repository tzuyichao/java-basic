package basic;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.baeldung.com/java-stax
 */
public class Example {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/resources/example.xml"));

        List<WebSite> websites = new ArrayList<>();
        WebSite webSite = null;

        while(xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if(xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "website":
                        webSite = new WebSite();
                        Attribute url = startElement.getAttributeByName(new QName("url"));
                        if(url != null) {
                            webSite.setUrl(url.getValue());
                        }
                        break;
                    case "name":
                        xmlEvent = xmlEventReader.nextEvent();
                        if(webSite != null) {
                            webSite.setName(xmlEvent.asCharacters().getData());
                        }
                        break;
                    case "category":
                        xmlEvent = xmlEventReader.nextEvent();
                        if(webSite != null) {
                            webSite.setCategory(xmlEvent.asCharacters().getData());
                        }
                        break;
                    case "status":
                        xmlEvent = xmlEventReader.nextEvent();
                        if(webSite != null) {
                            webSite.setStatus(xmlEvent.asCharacters().getData());
                        }
                        break;
                }
            }
            if(xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if(endElement.getName().getLocalPart().equals("website") && webSite != null) {
                    websites.add(webSite);
                }
            }
        }

        System.out.println(websites);
    }
}

class WebSite {
    private String name;
    private String category;
    private String status;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WebSite{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
