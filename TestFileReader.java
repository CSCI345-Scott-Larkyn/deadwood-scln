import javax.xml.parsers.ParserConfigurationException;
public class TestFileReader {
    public static void main(String[] args) throws ParserConfigurationException {
        FileReader f = new FileReader();
        f.parseCardsXML("cards.xml");
        f.printCardList();
    }
}