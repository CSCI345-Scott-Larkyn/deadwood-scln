import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

//the main controller of the whole program
//has access to all the viewControllers and the model
public class GameManagerFX {
    private GameViewController gameController;
    private UpgradeViewController upgradeController;
    private PlayerSelectController playerController;
    private TakeRoleController roleController;
    private ShowWinnersController winnersController;
    private Stage primaryStage;
    private Scene gameScene;

    private Board board;
    private Location[] locations;
    private List<Card> cards;
    private String playerIDs = "!@#$%^&*";
    private int turnsTaken = 0;
    private int numPlayers;
    private Player[] players;
    private Player curPlayer;
    private List<RoleGUI> offCardRoleGUIS;
    private int maxDays;
    private int daysPlayed;

    public GameManagerFX(GameViewController gCon, UpgradeViewController uCon, PlayerSelectController pCon, TakeRoleController rCon, ShowWinnersController wCon, Stage primaryStage, Scene gameScene) {
        gameController = gCon;
        upgradeController = uCon;
        playerController = pCon;
        roleController = rCon;
        winnersController = wCon;
        this.primaryStage = primaryStage;
        this.gameScene = gameScene;
    }

    //begins the game
    public void playGame() throws ParserConfigurationException {
        playerController.popup();
        numPlayers = playerController.playerNum;
        modelSetup();
        curPlayer = players[0];
        gameController.updateGUI(curPlayer);
        primaryStage.show();
    }

    //tells the player to move and updates the gui
    public void move(Location location) {
        curPlayer.move(location);
        gameController.updateGUI(curPlayer);
    }

    //tells the player to take a role and calls up a selection window
    public void promptTakeRole() {
        roleController.popup(curPlayer.getLocation(), curPlayer);
        if (roleController.roleToTake != null) {
            int signedRole = roleController.roleToTake.getRank();
            if (!roleController.isRoleOnCard) {
                signedRole = -1 * signedRole;
            }
            curPlayer.takeRole(signedRole);
            gameController.updateGUI(curPlayer);
        }
    }

    //tells the player to upgrade and calls up a selection window
    public void promptUpgrade() {
        upgradeController.popup(curPlayer);
        curPlayer.upgrade(upgradeController.intendedRank, upgradeController.payWithDollars);
        gameController.updateGUI(curPlayer);
    }

    //tells the player to act and updates the gui
    public void act() {
        curPlayer.act();
        gameController.updateGUI(curPlayer);
    }

    //tells the player to rehearse and updates the gui
    public void rehearse() {
        curPlayer.rehearse();
        gameController.updateGUI(curPlayer);
    }

    //ends the current players turn and advances to the next one
    public void endTurn() {
        turnsTaken++;
        curPlayer = players[turnsTaken % numPlayers];
        if (checkCompletedScenes() >= 9) {
            if (daysPlayed + 1 >= maxDays) {
                endGame();
            } else {
                endDay();
            }
        }
        curPlayer.setTurnBooleans();
        gameController.updateGUI(curPlayer);
    }

    //takes care of ending the day and moving everyone back to the trailers
    public void endDay() {
        board.endDay();
        daysPlayed++;
        for (int i = 0; i < players.length; i++) {
            players[i].moveToTrailers(locations[10]);
        }
    }

    //pops up the scoreboard
    public void endGame() {
        winnersController.popup(players);
    }

    //returns the number of completed scenes on the board
    private int checkCompletedScenes() {
        int completedScenes = 0;
        for (Location location : locations) {
            if (location.getSet() != null && location.getSet().getCard() == null) {
                completedScenes++;
            }
        }
        return completedScenes;
    }

    //calls methods to set up everything from the xml and fxml files
    //and runs some "hookup" methods which binds some data together
    //also creates the board and player array
    private void modelSetup() throws ParserConfigurationException {
        String boardFileName = "board.xml";
        String cardsFileName = "cards.xml";
        FileReader fileReader = new FileReader();
        fileReader.parseBoardXML(boardFileName);
        offCardRoleGUIS = fileReader.getOffCardRoleGUIs();
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
        board = new Board(locations, cards, gameController);
        for (Player p : players) {
            trailer.addPlayer(p);
        }

        gameController.hookUpExtras(locations);
        gameController.hookUpVisitors(locations);
        gameController.addFields(board, players, this, offCardRoleGUIS);
        gameController.hookUpLocationPanes();
        board.dealCards();
        maxDays = (numPlayers <= 3) ? 3 : 4;
        daysPlayed = 0;

        gameController.hookUpStatBoxes();

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
