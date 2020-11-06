import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

//good luck out there, rip to the old xml parser
public class FileReader {

    private Location[] room = new Location[12];
    private List<Card> cardList = new ArrayList<Card>();
    private Set[] allSets = new Set[10];
    
    public void parseCardsXML(String cardFileName) throws ParserConfigurationException {
        Document cardDoc = getDocFromFile(cardFileName);
        Element root = cardDoc.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");
        for (int i = 0; i < cards.getLength(); i++) {
            Element card = (Element) cards.item(i);
            String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
            NodeList children = card.getElementsByTagName("part");
            Role[] roles = new Role[children.getLength()];
            
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                String level = child.getAttributes().getNamedItem("level").getNodeValue();
                roles[j] = new Role(Integer.parseInt(level));  
            }
            cardList.add(new Card(Integer.parseInt(budget), roles));
        }
    }
    
    public void parseBoardXML(String boardFileName) throws ParserConfigurationException {
    	Document boardDoc = getDocFromFile(boardFileName);
        Element root = boardDoc.getDocumentElement();
        NodeList setList = root.getElementsByTagName("set");
        NodeList trailerList = root.getElementsByTagName("trailer");
        NodeList officeList = root.getElementsByTagName("office");
        
        int roomNum = 0;
        for(int i = 0; i < setList.getLength(); i++) {
        	Node setNode = setList.item(i);
        	roomNum = i;
        	if(setNode.getNodeType() == Node.ELEMENT_NODE) {
        		Element set = (Element) setNode;
        		String setName = set.getAttribute("name");
        		//System.out.println("Set Name = "+ setName);
        	
        		NodeList neighbors = set.getElementsByTagName("neighbor");
        		NodeList takes = set.getElementsByTagName("take");
        		NodeList parts = set.getElementsByTagName("part");
        		//NodeList area = set.getElementsByTagName("area");
        		Location[] setNeighbors = new Location[neighbors.getLength()]; 
        		for(int j = 0; j < neighbors.getLength(); j++) {
        			Element neighbor = (Element) neighbors.item(j);
        			String neighborName = neighbor.getAttribute("name");
        			//System.out.println("Neighbor Name = " + neighborName);
        			setNeighbors[j] = new Location(neighborName);
        		
        		}
        		room[i] = new Location(setNeighbors, false, setName);
        		
        		Element take = (Element) takes.item(0);
        		int numTakes = Integer.parseInt(take.getAttribute("number"));
        		//System.out.println("Number of Takes = " + numTakes);
        	
        		
        		Role[] ocRole = new Role[parts.getLength()];
        		for(int k = 0; k < parts.getLength(); k++) {
        			Element part = (Element) parts.item(k);
        			String partName = part.getAttribute("name");
        			int partRank = Integer.parseInt(part.getAttribute("level"));
        			//System.out.println("Part Name = " + partName);
        			//System.out.println("Required Rank = " + partRank);
        			ocRole[k] = new Role(partRank);
        		}
        		allSets[i] = new Set(numTakes, ocRole);
        	}
        }
        //System.out.println("trailer list length: " + trailerList.getLength());
        
        Element trailer = (Element) trailerList.item(0);
        	
        NodeList trailerNeighbs = trailer.getElementsByTagName("neighbor");
        Location[] trailerNeighbors = new Location[trailerNeighbs.getLength()];
        for(int s = 0; s < trailerNeighbs.getLength(); s++) {
        	Element neighborT = (Element) trailerNeighbs.item(s);
        	String trailNeighb = neighborT.getAttribute("name");
        	//System.out.println("Trailer Neighbors = " + trailNeighb);
        	trailerNeighbors[s] = new Location(trailNeighb);
        }
        room[roomNum+1] = new Location(trailerNeighbors, false, "Trailer");
        //something for area here eventually
        
        
        
        Element office = (Element) officeList.item(0);
        	
        NodeList neighborO = office.getElementsByTagName("neighbor");
        NodeList upgrade = office.getElementsByTagName("upgrade");
        Location[] officeNeighbs = new Location[neighborO.getLength()];
        for(int n = 0; n < neighborO.getLength(); n++) {
        	Element ofNeighb = (Element) neighborO.item(n);
        	String officeNeighbors = ofNeighb.getAttribute("name");
        	//System.out.println("Office Neighbors = " + officeNeighbors);
        	officeNeighbs[n] = new Location(officeNeighbors);
        }
        for(int m = 0; m < upgrade.getLength(); m++) {
        	Element upgradeEl = (Element) upgrade.item(m);
        	int rank = Integer.parseInt(upgradeEl.getAttribute("level"));
        	String currency = upgradeEl.getAttribute("currency");
        	int amount = Integer.parseInt(upgradeEl.getAttribute("amt"));
        	//System.out.println("Upgrade: rank, currency, amt = " + rank + " " + currency + " " + amount);
        }
        room[roomNum+2] = new Location(officeNeighbs, true, "Office");
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
    
    //felt cute, might delete later
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

