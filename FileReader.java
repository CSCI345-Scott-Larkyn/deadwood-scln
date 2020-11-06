import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class FileReader {

    private Location[] locations;
    private List<Card> cardList = new ArrayList<Card>();
    
    public void parseCardsXML(String cardFileName) throws ParserConfigurationException {
        Document cardDoc = getDocFromFile(cardFileName);
        Element root = cardDoc.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");
        for (int i = 0; i < cards.getLength(); i++) {
            Node card = cards.item(i);
            String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
            NodeList children = card.getChildNodes();
            Role[] roles = new Role[children.getLength()];
            
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                if ("part".equals(child.getNodeName())) {
                    String level = child.getAttributes().getNamedItem("level").getNodeValue();
                    roles[j] = new Role(Integer.parseInt(level));
                }    
            }
            roles = shortenRoleArray(roles);
            cardList.add(new Card(Integer.parseInt(budget), roles));
        }
    }
    
    private Role[] shortenRoleArray(Role[] roles) {
        int roleCount = 0;
        for (int i = 0; i < roles.length; i++) {
            if (roles[i] != null) {
                roleCount++;
            }
        }
        Role[] shortRoles = new Role[roleCount];
        int index = 0;
        for (int i = 0; i < roles.length; i++) {
            if (roles[i] != null) {
                shortRoles[index] = roles[i];
                index++;
            }
        }
        return shortRoles;
    }
    
    public Location[] getLocations() {
        return null;
    }
    
    public Card[] getCardDeck() {
        return null;
    }
    
    private Document getDocFromFile(String fileName) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = null;
        try {
            doc = db.parse(fileName);
        } catch (Exception ex) {
            System.out.println("XML parse failure");
            ex.printStackTrace();
        }
        return doc;
    }
    
    //unnecessary but nice to see that it works, will delete later
    public void printCardList() {
        for (int i = 0; i < cardList.size(); i++) {
            Card cur = cardList.get(i);
            System.out.print((i+1) + ". " + cur.getBudget() + ": ");
            Role[] roles = cur.getRoles();
            for (Role r : roles) {
                System.out.print(r.getRank() + " ");
            }
            System.out.println();
            if (i % 5 == 4) {
                System.out.println();
            }
        }
    }
}