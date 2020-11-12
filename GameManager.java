import java.util.*;
import javax.xml.parsers.ParserConfigurationException;

public class GameManager {
    private int numPlayers;
    private Location[] locations;
    private Player[] players;
    private List<Card> cards;
    private PlayerView playerView = new PlayerView();
    private String playerIDs = "!@#$%^&*";
    private int turnsTaken = 0;
    
    public GameManager(int numPlayers) {
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
    }
    
    public void setup(String boardFileName, String cardsFileName) throws ParserConfigurationException {
        FileReader fileReader = new FileReader();
        fileReader.parseBoardXML(boardFileName);
        locations = fileReader.getLocations();
        fileReader.parseCardsXML(cardsFileName);
        cards = fileReader.getCardDeck();
        if (numPlayers <= 4) {
            makePlayers4OrLess();
        } else {
            makePlayers5OrMore();
        }
    }
    
    private void makePlayers4OrLess() {
        for (int player = 0; player < numPlayers; player++) {
            players[player] = new Player(playerIDs.substring(player, player + 1), playerView, player + 1);
        }
    }
    
    private void makePlayers5OrMore() {
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
            players[player] = new Player(playerIDs.substring(player, player + 1), playerView, credits, rank, player + 1);
        }
    }
    
    public void playDay() {
        int scenesCompleted = 0;       
        while (scenesCompleted < 9) {
            int curPlayerIndex = turnsTaken % numPlayers;
            Player currentPlayer = players[curPlayerIndex];
            String ID = currentPlayer.getName();
            System.out.println("It is player " + (curPlayerIndex + 1) + "'s turn ('" + ID + "')");
            currentPlayer.takeTurn();
            scenesCompleted = checkCompletedScenes();
            turnsTaken++;
        }
    }
    
    private int checkCompletedScenes() {
        int completedScenes = 0;
        for (int ind = 0; ind < locations.length; ind++) {
            if (locations[ind].getSet() != null && locations[ind].getSet().getCard() == null) {
                completedScenes++;
            }
        }
        return completedScenes;
    }
    
    public void endDay() {
        //board.endDay();
    }
    
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