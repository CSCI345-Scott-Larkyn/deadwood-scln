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
    
    public Document getDocFromFile(String filename)
        throws ParserConfigurationException{
        {
            
                  
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;
           
           try{
               doc = db.parse(filename);
           } catch (Exception ex){
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } 
        
        }

    public void parseBoardXML(Document d){
        
            Element root = d.getDocumentElement();
            
            NodeList set = root.getElementsByTagName("set");
            
            for (int i=0; i<set.getLength();i++){
                
                System.out.println("Printing information for set "+(i+1));
                
                //reads data from the nodes
                Node boardSet = set.item(i);
                String setName = board.getAttributes().getNamedItem("name").getNodeValue();
                System.out.println("Name = "+ setName);
                
                //reads data
                                             
                NodeList children = boardSet.getChildNodes();
                
                for (int j=0; j< children.getLength(); j++){
                    
                  Node sub = children.item(j);
                
                  if("neighbor".equals(sub.getNodeName())){
                     String bookLanguage = sub.getAttributes().getNamedItem("lang").getNodeValue();
                     System.out.println("Language = "+bookLanguage);
                     String title = sub.getTextContent();
                     System.out.println("Title = "+title);
                     
                  }
                  
                  else if("author".equals(sub.getNodeName())){
                     String authorName = sub.getTextContent();
                     System.out.println(" Author = "+authorName);
                     
                  }
                  else if("year".equals(sub.getNodeName())){
                     String yearVal = sub.getTextContent();
                     System.out.println(" Publication Year = "+yearVal);
                     
                  }
                  else if("price".equals(sub.getNodeName())){
                     String priceVal = sub.getTextContent();
                     System.out.println(" Price = "+priceVal);
                     
                  }
                                 
                
                } 
                
                System.out.println("\n");
                
            }
        
        }
    
    public Location[] getLocations() {
        return null;
    }
    
    public Card[] getCardDeck() {
        return null;
    }
}