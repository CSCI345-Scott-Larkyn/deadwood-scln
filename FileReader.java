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

    private Location[] rooms = new Location[12];
    private List<Card> cardList = new ArrayList<Card>();
    private static int roomsNum = 0;
    
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
        
        setListParse(setList);
        
        trailerListParse(trailerList);
        
        officeListParse(officeList);
        //still need lines for parts and areas
        fixNeighbors();
        
        
       }
        
    public void setListParse(NodeList setList) {
    	
        for(int i = 0; i < setList.getLength(); i++) {
        	Node setNode = setList.item(i);
        	roomsNum = i;
        	if(setNode.getNodeType() == Node.ELEMENT_NODE) {
        		Element set = (Element) setNode;
        		String setName = set.getAttribute("name");
        		
        	
        		NodeList neighbors = set.getElementsByTagName("neighbor");
        		NodeList takes = set.getElementsByTagName("take");
        		NodeList parts = set.getElementsByTagName("part");
        		//NodeList area = set.getElementsByTagName("area");
        		Location[] setNeighbors = new Location[neighbors.getLength()]; 
        		for(int j = 0; j < neighbors.getLength(); j++) {
        			Element neighbor = (Element) neighbors.item(j);
        			String neighborName = neighbor.getAttribute("name");
        			
        			setNeighbors[j] = new Location(neighborName);
        		
        		}
        		rooms[i] = new Location(setNeighbors, false, setName);
        		
        		Element take = (Element) takes.item(0);
        		int numTakes = Integer.parseInt(take.getAttribute("number"));
        		
        	
        		
        		Role[] ocRole = new Role[parts.getLength()];
        		for(int k = 0; k < parts.getLength(); k++) {
        			Element part = (Element) parts.item(k);
        			String partName = part.getAttribute("name");
        			int partRank = Integer.parseInt(part.getAttribute("level"));
        			
        			ocRole[k] = new Role(partRank);
        		}
        		rooms[i].createSet(numTakes, ocRole);
        		// also need to add the lines here somewhere
        	}
        }
    }
    
    public void trailerListParse(NodeList trailerList) {
    	Element trailer = (Element) trailerList.item(0);
    	
        NodeList trailerNeighbs = trailer.getElementsByTagName("neighbor");
        Location[] trailerNeighbors = new Location[trailerNeighbs.getLength()];
        for(int s = 0; s < trailerNeighbs.getLength(); s++) {
        	Element neighborT = (Element) trailerNeighbs.item(s);
        	String trailNeighb = neighborT.getAttribute("name");
        	
        	trailerNeighbors[s] = new Location(trailNeighb);
        	
        }
        rooms[roomsNum+1] = new Location(trailerNeighbors, false, "Trailer");
    }
    
    public void officeListParse(NodeList officeList) {
    	 Element office = (Element) officeList.item(0);
     	
         NodeList neighborO = office.getElementsByTagName("neighbor");
         NodeList upgrade = office.getElementsByTagName("upgrade");
         Location[] officeNeighbs = new Location[neighborO.getLength()];
         for(int n = 0; n < neighborO.getLength(); n++) {
         	Element ofNeighb = (Element) neighborO.item(n);
         	String officeNeighbors = ofNeighb.getAttribute("name");
         	
         	officeNeighbs[n] = new Location(officeNeighbors);
         }
         for(int m = 0; m < upgrade.getLength(); m++) {
         	Element upgradeEl = (Element) upgrade.item(m);
         	int rank = Integer.parseInt(upgradeEl.getAttribute("level"));
         	String currency = upgradeEl.getAttribute("currency");
         	int amount = Integer.parseInt(upgradeEl.getAttribute("amt"));
         	
         }
         rooms[roomsNum+2] = new Location(officeNeighbs, true, "Casting Office");
        
    }
    
    public void fixNeighbors() {
    	
    	for(int i = 0; i < rooms.length; i++) {
    		Location[] neighborsS = rooms[i].getNeighbors();
    		
    		for(int j = 0; j < neighborsS.length; j++) {
    			String neighbName = neighborsS[j].getName();
    			for(int k = 0; k < rooms.length; k++) {
    				String roomName = rooms[k].getName();
    				
    				if(roomName.equalsIgnoreCase(neighbName)) {
    					neighborsS[j] = rooms[k];
    					
    					break;
    				}
    			}
    		}
    	}
    	
    }
    
    public Location[] getLocations() {
        return rooms;
    }
    
    public List<Card> getCardDeck() {
        return cardList;
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
    
    public void printBoard() {
    
    }

}

