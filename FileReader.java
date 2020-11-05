import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class FileReader {
    private String locationFileName;
    private String cardFileName;
    private Location[] locations;
    private Card[] cards;
    
    public FileReader(String locationFileName, String cardFileName) {
        this.locationFileName = locationFileName;
        this.cardFileName = cardFileName;
    }
    
    public void parseCardXML() {
        
    }
    
    public Location[] getLocations() {
        return null;
    }
    
    public Card[] getCardDeck() {
        return null;
    }
}