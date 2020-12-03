////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//this class is the controller in the MVC model
//it sets up everything from the files 
//and takes care of finishing days and the game

import java.util.*;
import javax.xml.parsers.ParserConfigurationException;

public class GameManager {
    //public GUIBoardController getGUIBoardController() { return new GUIBoardController(); }

    private int numPlayers;
    private Board board;
    private BoardView boardView = new BoardViewText();
    private Location[] locations;
    private Player[] players;
    private List<Card> cards;
    private PlayerViewText playerView = new PlayerViewText();
    private String playerIDs = "!@#$%^&*";
    private int turnsTaken = 0;
    
    public GameManager(int numPlayers) {
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
    }
    
    //reads in data from the XML files
    //and creates all the players
    //and assebles the board
    //and puts all the players in the trailers
    public void setup(String boardFileName, String cardsFileName) throws ParserConfigurationException {
        FileReader fileReader = new FileReader();
        fileReader.parseBoardXML(boardFileName);
        locations = fileReader.getLocations();
        fileReader.parseCardsXML(cardsFileName);
        cards = fileReader.getCardDeck();
        Location trailer = locations[10];
        if (numPlayers <= 4) {
            makePlayers4OrLess(trailer);
        } else {
            makePlayers5OrMore(trailer);
        }
        board = new Board(locations, cards);
        for (Player p : players) {
            trailer.addPlayer(p);
        }
        board.dealCards();
    }
    
    //sets player data for small games
    private void makePlayers4OrLess(Location trailer) {
        for (int player = 0; player < numPlayers; player++) {
            players[player] = new Player(playerIDs.substring(player, player + 1), player + 1, trailer);
        }
    }
    
    //takes care of the different initial stats for larger games
    private void makePlayers5OrMore(Location trailer) {
        int credits = 0;
        if (numPlayers == 5) {
            credits = 2;
        } else if (numPlayers == 6) {
            credits = 4;
        }
        int rank = 1;
        if (numPlayers >= 7) {
            rank = 2;
        }
        for (int player = 0; player < numPlayers; player++) {
            players[player] = new Player(playerIDs.substring(player, player + 1), credits, rank, player + 1, trailer);
        }
    }
    
    //prints the board and then keeps calling players in order
    //to take turns until all but one scene is completed
    public void playDay() {
        int scenesCompleted = 0;       
        while (scenesCompleted < 9) {
            boardView.printBoard(board);
            System.out.println();
            
            int curPlayerIndex = turnsTaken % numPlayers;
            Player currentPlayer = players[curPlayerIndex];
            String ID = currentPlayer.getName();
            System.out.println("It is player " + (curPlayerIndex + 1) + "'s turn ('" + ID + "')");
            currentPlayer.takeTurn();
            scenesCompleted = checkCompletedScenes();
            turnsTaken++;
            System.out.println();
        }
    }
    
    //adds up the number of completed scenes and returns it
    private int checkCompletedScenes() {
        int completedScenes = 0;
        for (Location location : locations) {
            if (location.getSet() != null && location.getSet().getCard() == null) {
                completedScenes++;
            }
        }
        return completedScenes;
    }
    
    public void endDay() {
        board.endDay();  
        for (int i = 0; i < players.length; i++) {
            players[i].moveToTrailers(locations[10]);
        }     
    }
    
    //adds up the scores and declares a winner
    //or declares multiple winners if there is a tie
    public void decideWinner() {
        int topScore = 0;
        for (int ind = 0; ind < players.length; ind++) {
            int newScore = players[ind].getScore();
            if (newScore > topScore) {
                topScore = newScore;
            }
        }
        List<Player> winners = new ArrayList<Player>();
        for (int ind = 0; ind < players.length; ind++) {
            if (players[ind].getScore() == topScore) {
                winners.add(players[ind]);
            }
        }
        if (winners.size() == 1) {
            Player winner = winners.get(0);
            System.out.println("Player " + winner.getPlayerNum() + " won with a score of " + topScore + " points");
        } else {
            System.out.print("Players " + winners.get(0) + " ");
            for (int ind = 1; ind < winners.size(); ind++) {
                System.out.print("and " + winners.get(ind) + " ");
            }
            System.out.println("won with a score of " + topScore);
        }
    } 
}    