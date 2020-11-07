import javax.xml.parsers.ParserConfigurationException;
public class TestFileReader {
    public static void main(String[] args) throws ParserConfigurationException {
        //FileReader f = new FileReader();
        //f.parseCardsXML("cards.xml");
        FileReader f2 = new FileReader();
        f2.parseBoardXML("board.xml");
        //f.printCardList();
        f2.printBoard();
    }
}