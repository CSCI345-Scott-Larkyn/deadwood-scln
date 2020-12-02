
////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.*;

public class FileReader {

    private Location[] rooms = new Location[12];
    private List<Card> cardList = new ArrayList<Card>();
    private List<RoleGUI> offCardRoleGUIs = new ArrayList<>();
    private static int roomsNum = 0;
    
    //reads in and creates cards, adding them to the cardList
    public void parseCardsXML(String cardFileName) throws ParserConfigurationException {
        Document cardDoc = getDocFromFile(cardFileName);
        Element root = cardDoc.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");

        //new variables to store additional info
        List<String> cardName = new ArrayList<String>();
        List<String> roleName = new ArrayList<String>();
        List<int[]> roleArea = new ArrayList<int[]>();
        int[] roleAArr = new int[4];

        for (int i = 0; i < cards.getLength(); i++) {
            Element card = (Element) cards.item(i);
            String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
            NodeList children = card.getElementsByTagName("part");
            Role[] roles = new Role[children.getLength()];

            String cName = card.getAttributes().getNamedItem("name").getNodeValue();
            cardName.add(cName);
            //System.out.println(cName);
            
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);


                String pName = child.getAttributes().getNamedItem("name").getNodeValue();
                roleName.add(pName);
                //System.out.println(pName);
                Element part = (Element) children.item(j);
                NodeList area = part.getElementsByTagName("area");
                Element areaE = (Element) area.item(0);
                int x = Integer.parseInt(areaE.getAttribute("x"));
                int y = Integer.parseInt(areaE.getAttribute("y"));
                int h = Integer.parseInt(areaE.getAttribute("h"));
                int w = Integer.parseInt(areaE.getAttribute("w"));
                PixelData rolePixelData = new PixelData(x, y, h, w);
                //System.out.println(roleAArr[0] + " " + roleAArr[1] + " " + roleAArr[2] + " " + roleAArr[3]);
                roleArea.add(roleAArr);

                String level = child.getAttributes().getNamedItem("level").getNodeValue();
                //took out pixelData
                roles[j] = new Role(Integer.parseInt(level));
            }
            Card cardToAdd = new Card(Integer.parseInt(budget), roles);
            cardList.add(cardToAdd);
            String front = "cards/" + card.getAttributes().getNamedItem("img").getNodeValue();
            CardGUI cardGUI = new CardGUI(front, "CardBack.jpg", cardToAdd, children.getLength());
            cardToAdd.addCardGUI(cardGUI);
        }
    }
    

    //parseBoardXML
    //  there are 3 main lists from the root- set, trailer, and office
    //      there are separate methods to parse each of these called from parseBoardXML
    //      nothing returned
    //      special method to fix neighbors from shadow objects to connected.
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
    
    //parse through the setList
    //  saves each set into an array of locations
    //  saves neighbors as a list, the number of takes, and parts as well
    //      parts represent off card roles- these have associated ranks as well
    //      takes represent the number of shots
    //      name is name of location as well as names of neighbors

    public void setListParse(NodeList setList) {
    	int[] setArea = new int[4];
    	int[] partArea = new int[4];

        for(int i = 0; i < setList.getLength(); i++) {
        	Node setNode = setList.item(i);
        	roomsNum = i;
        	if(setNode.getNodeType() == Node.ELEMENT_NODE) {
        		Element set = (Element) setNode;
        		String setName = set.getAttribute("name");

        		NodeList neighbors = set.getElementsByTagName("neighbor");
        		NodeList takes = set.getElementsByTagName("take");
        		NodeList parts = set.getElementsByTagName("part");
        		NodeList areas = set.getElementsByTagName("area");
        		Location[] setNeighbors = new Location[neighbors.getLength()];

        		for(int j = 0; j < neighbors.getLength(); j++) {
        			Element neighbor = (Element) neighbors.item(j);
        			String neighborName = neighbor.getAttribute("name");
        			
        			setNeighbors[j] = new Location(neighborName);
        		
        		}
        		rooms[i] = new Location(setNeighbors, false, setName);
                Element area = (Element) areas.item(0);
                setArea[0] = Integer.parseInt(area.getAttribute("x"));
                setArea[1] = Integer.parseInt(area.getAttribute("y"));
                setArea[2] = Integer.parseInt(area.getAttribute("h"));
                setArea[3] = Integer.parseInt(area.getAttribute("w"));

                //System.out.println(setArea[0] + " " + setArea[1] + " " + setArea[2] + " " + setArea[3]);
        		Element take = (Element) takes.item(0);
        		int numTakes = Integer.parseInt(take.getAttribute("number"));

        		
        		Role[] ocRole = new Role[parts.getLength()];
        		for(int k = 0; k < parts.getLength(); k++) {
        			Element part = (Element) parts.item(k);
        			String partName = part.getAttribute("name");
        			int partRank = Integer.parseInt(part.getAttribute("level"));

        			NodeList pArea = part.getElementsByTagName("area");
                    Element pAreaE = (Element) pArea.item(0);
                    int x = Integer.parseInt(pAreaE.getAttribute("x"));
                    int y = Integer.parseInt(pAreaE.getAttribute("y"));
                    int h = Integer.parseInt(pAreaE.getAttribute("h"));
                    int w = Integer.parseInt(pAreaE.getAttribute("w"));
                    //System.out.println(partArea[0] + " " + partArea[1] + " " + partArea[2] + " " + partArea[3]);
                    PixelData rolePixelData = new PixelData(x, y, h, w);
                    //took out pixelData
                    ocRole[k] = new Role(partRank);
                    RoleGUI roleGUI = new RoleGUI(new ImageView("dice/w1.png"), ocRole[k]);
                    offCardRoleGUIs.add(roleGUI);
                    ocRole[k].addRoleGUI(roleGUI);
        		}

        		rooms[i].createSet(numTakes, ocRole);

        		// The lines for each part will be here somewhere also... jk we don't need those
        	}
        }
    }
    

    //trailerListParse
    //  saves neighbors of the trailer

    public void trailerListParse(NodeList trailerList) {
        int[] trailerArea = new int[4];
    	Element trailer = (Element) trailerList.item(0);
    	
        NodeList trailerNeighbs = trailer.getElementsByTagName("neighbor");
        Location[] trailerNeighbors = new Location[trailerNeighbs.getLength()];
        for(int s = 0; s < trailerNeighbs.getLength(); s++) {
        	Element neighborT = (Element) trailerNeighbs.item(s);
        	String trailNeighb = neighborT.getAttribute("name");
        	
        	trailerNeighbors[s] = new Location(trailNeighb);
        	
        }
        rooms[roomsNum+1] = new Location(trailerNeighbors, false, "Trailer");
        NodeList trailA = trailer.getElementsByTagName("area");
        Element tArea = (Element) trailA.item(0);
        trailerArea[0] = Integer.parseInt(tArea.getAttribute("x"));
        trailerArea[1] = Integer.parseInt(tArea.getAttribute("y"));
        trailerArea[2] = Integer.parseInt(tArea.getAttribute("h"));
        trailerArea[3] = Integer.parseInt(tArea.getAttribute("w"));
        //System.out.println(trailerArea[0] + " " + trailerArea[1] + " " +trailerArea[2] + " " +trailerArea[3]);
    }
    
    //officeListParse
    //  gets names of neighbors 
    //      when creating this object, the flag for upgradeOkay will be set to true

    public void officeListParse(NodeList officeList) {
         int[] officeArea = new int[4];
    	 Element office = (Element) officeList.item(0);
     	
         NodeList neighborO = office.getElementsByTagName("neighbor");
         NodeList upgrade = office.getElementsByTagName("upgrade");
         NodeList oArea = office.getElementsByTagName("area");
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
         rooms[roomsNum+2] = new Location(officeNeighbs, true, "office");

         Element officeA = (Element) oArea.item(0);
         officeArea[0] = Integer.parseInt(officeA.getAttribute("x"));
         officeArea[1] = Integer.parseInt(officeA.getAttribute("y"));
         officeArea[2] = Integer.parseInt(officeA.getAttribute("h"));
         officeArea[3] = Integer.parseInt(officeA.getAttribute("w"));
         //System.out.println(officeArea[0] + " " + officeArea[1] + " " +officeArea[2] + " " +officeArea[3]);

        
    }
    
    //void fixNeighbors()
    //  goes through neighbors to connect them to real objects, 
    //      before, some of them were constructed by name and not connected to the other locations
    //  loop through each room and get the neighbors and connect them to the actual room.
    //      another functionality: change name of "office" to "Casting Office"
    public void fixNeighbors() {

        for (Location room : rooms) {
            Location[] neighborsS = room.getNeighbors();

            for (int j = 0; j < neighborsS.length; j++) {
                String neighbName = neighborsS[j].getName();
                for (Location location : rooms) {
                    String roomName = location.getName();

                    if (roomName.equalsIgnoreCase(neighbName)) {
                        neighborsS[j] = location;

                        break;
                    }
                }
            }
        }
    	rooms[11].setName("Casting Office");
    }
    
    //getter for locations
    public Location[] getLocations() {
        return rooms;
    }
    
    //getter for cards
    public List<Card> getCardDeck() {
        return cardList;
    }

    //given code

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
    

    //for testing
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

