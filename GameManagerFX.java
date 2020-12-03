import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public class GameManagerFX {
    private GameViewController gameController;
    private UpgradeViewController upgradeController;
    private PlayerSelectController playerController;
    private TakeRoleController roleController;
    private Stage primaryStage;

    private Board board;
    private Location[] locations;
    private List<Card> cards;
    private String playerIDs = "!@#$%^&*";
    private int turnsTaken = 0;
    private int numPlayers;
    private Player[] players;
    private Player curPlayer;

    public GameManagerFX(GameViewController gCon, UpgradeViewController uCon, PlayerSelectController pCon, TakeRoleController rCon, Stage primaryStage) {
        gameController = gCon;
        upgradeController = uCon;
        playerController = pCon;
        roleController = rCon;
        this.primaryStage = primaryStage;
    }

    public void playGame() throws ParserConfigurationException {
        playerController.popup();
        numPlayers = playerController.playerNum;
        modelSetup();
        curPlayer = players[0];
        primaryStage.show();
        roleController.popup(locations[2]);
    }

    public void move(Location location) {

    }

    public void promptTakeRole() {
        roleController.popup(curPlayer.getLocation());
    }

    public void promptUpgrade() {
        upgradeController.popup(curPlayer);
    }

    public void act() {

    }

    public void rehearse() {

    }

    public void endTurn() {

    }

    private void modelSetup() throws ParserConfigurationException {
        String boardFileName = "board.xml";
        String cardsFileName = "cards.xml";
        FileReader fileReader = new FileReader();
        fileReader.parseBoardXML(boardFileName);
        locations = fileReader.getLocations();
        fileReader.parseCardsXML(cardsFileName);
        cards = fileReader.getCardDeck();
        Location trailer = locations[10];
        players = new Player[numPlayers];

        if (numPlayers <= 4) {
            makePlayers4OrLess(trailer);
        } else {
            makePlayers5OrMore(trailer);
        }
        board = new Board(locations, cards);
        for (Player p : players) {
            trailer.addPlayer(p);
        }
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
}
