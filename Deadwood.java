import javax.xml.parsers.ParserConfigurationException;

public class Deadwood {
    public static void main(String[] args) throws NullPointerException, ParserConfigurationException { 
        int numPlayers = Integer.parseInt(args[0]);
        int r = 8;
        if (numPlayers < 2 || numPlayers > 8) {
            System.out.println("That is not a valid number of players");
        } else {
            GameManager manager = new GameManager(numPlayers);
            manager.setup("board.xml", "cards.xml");
            int numDays = 4;
            if (numPlayers < 4)
                numDays = 3;
            for (int day = 1; day <= numDays; day++) {
                System.out.println("Day " + day);
                manager.playDay();
                if (day < numDays) {
                    manager.endDay();
                }    
            }
            manager.decideWinner();
        }
    }
}   