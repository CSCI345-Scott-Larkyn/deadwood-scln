import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

//the controller for the main game view, updates the board state after every action
public class GameViewController {

    private Board board;
    private Player[] players;
    private Location[] locs;
    private GameManagerFX manager;
    private List<RoleGUI> offCardRoleGUIS;
    private StatBoxGUI[] statBoxes = new StatBoxGUI[8];

    //essentially a second round constructor to instantiate more fields
    public void addFields(Board board, Player[] players, GameManagerFX manager, List<RoleGUI> offCardRoleGUIs) {
        this.board = board;
        this.players = players;
        this.locs = board.getLocations();
        this.manager = manager;
        this.offCardRoleGUIS = offCardRoleGUIs;
    }

    //makes sure every element of the GUI is accurate according to the game state
    public void updateGUI(Player curPlayer) {
        currentPlayerText.setText("Player " + Integer.toString(curPlayer.getPlayerNum()));

        String turnOptions = curPlayer.getTurnOptions();

        /////////////////////////////////
        //buttons in tool bar
        /////////////////////////////////
        takeRoleButton.setDisable(!turnOptions.contains("t"));
        upgradeButton.setDisable(!turnOptions.contains("u"));
        actButton.setDisable(!turnOptions.contains("a"));
        if(turnOptions.contains("a")){
            budget.setText("Budget: " + Integer.toString(curPlayer.getLocation().getSet().getCard().getBudget()));
            practiceChips.setText("Practice Chips: " + Integer.toString(curPlayer.getPracticeChips()));
            actingStatBox.setVisible(true);
        }else{
            actingStatBox.setVisible(false);
        }
        rehearseButton.setDisable(!turnOptions.contains("r"));
        endTurnButton.setVisible(turnOptions.contains("e"));
        if(!turnOptions.contains("m")){
            moveButton.setDisable(true);
        }else{
            moveButton.setDisable(false);
            Location[] neighbors = curPlayer.getNeighbors();

            moveTrailers.setVisible(findLocationByName("Trailer", neighbors));
            moveSH.setVisible(findLocationByName("Secret Hideout", neighbors));
            moveRanch.setVisible(findLocationByName("Ranch", neighbors));
            moveHotel.setVisible(findLocationByName("Hotel", neighbors));
            moveCO.setVisible(findLocationByName("Casting Office", neighbors));
            moveMS.setVisible(findLocationByName("Main Street", neighbors));
            moveBank.setVisible(findLocationByName("Bank", neighbors));
            moveSaloon.setVisible(findLocationByName("Saloon", neighbors));
            moveJail.setVisible(findLocationByName("Jail", neighbors));
            moveTS.setVisible(findLocationByName("Train Station", neighbors));
            churchMove.setVisible(findLocationByName("Church", neighbors));
            moveGS.setVisible(findLocationByName("General Store", neighbors));
        }

        //////////////////////////////
        //visiting players
        //////////////////////////////
        for(int i = 0; i < locs.length; i++){
            List<Player> visitors = locs[i].getVisitingPlayers();
            VisitorsGUI visitorsG = locs[i].getVisitorsGUI();
            visitorsG.update(visitors);
        }

        ////////////////////////////////
        //offCard roles
        ////////////////////////////////
        RoleGUI[] offCardR = hookUpExtras(locs);
        for(int i = 0; i < offCardR.length; i++){
            offCardR[i].updateGUI();
        }

        ////////////////////////////////
        //cardGUIs
        ////////////////////////////////
        for (Location loc : locs) {
            if (loc.getSet() != null && loc.getSet().getCard() != null) {
                loc.getSet().getCard().getCardGUI().update(true);
            }
        }




        //////////////////////////////////
        //Shot Counts
        //  make visible if shot count < number of shots completed
        ////////////////////////////////////////////////////////////

        trainStationShot3.setVisible(locs[0].getSet().getCurrentShotCount() < 3);
        trainStationShot2.setVisible(locs[0].getSet().getCurrentShotCount() < 2);
        trainStationShot1.setVisible(locs[0].getSet().getCurrentShotCount() < 1);
        secretHideoutShot3.setVisible(locs[1].getSet().getCurrentShotCount() < 3);
        secretHideoutShot2.setVisible(locs[1].getSet().getCurrentShotCount() < 2);
        secretHideoutShot1.setVisible(locs[1].getSet().getCurrentShotCount() < 1);
        churchShot2.setVisible(locs[2].getSet().getCurrentShotCount() < 2);
        churchShot1.setVisible(locs[2].getSet().getCurrentShotCount() < 1);
        hotelShot3.setVisible(locs[3].getSet().getCurrentShotCount() < 3);
        hotelShot2.setVisible(locs[3].getSet().getCurrentShotCount() < 2);
        hotelShot1.setVisible(locs[3].getSet().getCurrentShotCount() < 1);
        mainStreetShot3.setVisible(locs[4].getSet().getCurrentShotCount() < 3);
        mainStreetShot2.setVisible(locs[4].getSet().getCurrentShotCount() < 2);
        mainStreetShot1.setVisible(locs[4].getSet().getCurrentShotCount() < 1);
        jailShot1.setVisible(locs[5].getSet().getCurrentShotCount() < 1);
        generalStoreShot2.setVisible(locs[6].getSet().getCurrentShotCount() < 2);
        generalStoreShot1.setVisible(locs[6].getSet().getCurrentShotCount() < 1);
        ranchShot2.setVisible(locs[7].getSet().getCurrentShotCount() < 2);
        ranchShot1.setVisible(locs[7].getSet().getCurrentShotCount() < 1);
        bankShot1.setVisible(locs[8].getSet().getCurrentShotCount() < 1);
        saloonShot2.setVisible(locs[9].getSet().getCurrentShotCount() < 2);
        saloonShot1.setVisible(locs[9].getSet().getCurrentShotCount() < 1);

        /////////////////////////////////////////
        //stats bar at the bottom..
        //      pane invisible for fewer players
        //      correct rank, credits, dollars
        /////////////////////////////////////////
        int numPlayers = players.length;
        //hookUpStatBoxes();
        for(int i = 0; i < 8; i++){
            statBoxes[i].update();
        }

    }

    //returns true if an array of locations contains a particular location
    private boolean findLocationByName(String name, Location[] locs) {
        for (Location loc : locs) {
            if (loc.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //data binds imageViews to offCard roles through RoleGUIs
    public RoleGUI[] hookUpExtras(Location[] locs) {
        RoleGUI[] roleGUIS = new RoleGUI[29];
        roleGUIS[0] = new RoleGUI(tsOff0, locs[0].getSet().getOffCardRoles()[0]);
        roleGUIS[1] = new RoleGUI(tsOff1, locs[0].getSet().getOffCardRoles()[1]);
        roleGUIS[2] = new RoleGUI(tsOff2, locs[0].getSet().getOffCardRoles()[2]);
        roleGUIS[3] = new RoleGUI(tsOff3, locs[0].getSet().getOffCardRoles()[3]);
        roleGUIS[4] = new RoleGUI(shOff0, locs[1].getSet().getOffCardRoles()[0]);
        roleGUIS[5] = new RoleGUI(shOff1, locs[1].getSet().getOffCardRoles()[1]);
        roleGUIS[6] = new RoleGUI(shOff2, locs[1].getSet().getOffCardRoles()[2]);
        roleGUIS[7] = new RoleGUI(shOff3, locs[1].getSet().getOffCardRoles()[3]);
        roleGUIS[8] = new RoleGUI(cOff0, locs[2].getSet().getOffCardRoles()[0]);
        roleGUIS[9] = new RoleGUI(cOff1, locs[2].getSet().getOffCardRoles()[1]);
        roleGUIS[10] = new RoleGUI(hOff0, locs[3].getSet().getOffCardRoles()[0]);
        roleGUIS[11] = new RoleGUI(hOff1, locs[3].getSet().getOffCardRoles()[1]);
        roleGUIS[12] = new RoleGUI(hOff2, locs[3].getSet().getOffCardRoles()[2]);
        roleGUIS[13] = new RoleGUI(hOff3, locs[3].getSet().getOffCardRoles()[3]);
        roleGUIS[14] = new RoleGUI(msOff0, locs[4].getSet().getOffCardRoles()[0]);
        roleGUIS[15] = new RoleGUI(msOff1, locs[4].getSet().getOffCardRoles()[1]);
        roleGUIS[16] = new RoleGUI(msOff2, locs[4].getSet().getOffCardRoles()[2]);
        roleGUIS[17] = new RoleGUI(msOff3, locs[4].getSet().getOffCardRoles()[3]);
        roleGUIS[18] = new RoleGUI(jOff0, locs[5].getSet().getOffCardRoles()[0]);
        roleGUIS[19] = new RoleGUI(jOff1, locs[5].getSet().getOffCardRoles()[1]);
        roleGUIS[20] = new RoleGUI(gsOff0, locs[6].getSet().getOffCardRoles()[0]);
        roleGUIS[21] = new RoleGUI(gsOff1, locs[6].getSet().getOffCardRoles()[1]);
        roleGUIS[22] = new RoleGUI(rOff0, locs[7].getSet().getOffCardRoles()[0]);
        roleGUIS[23] = new RoleGUI(rOff1, locs[7].getSet().getOffCardRoles()[1]);
        roleGUIS[24] = new RoleGUI(rOff2, locs[7].getSet().getOffCardRoles()[2]);
        roleGUIS[25] = new RoleGUI(bOff0, locs[8].getSet().getOffCardRoles()[0]);
        roleGUIS[26] = new RoleGUI(bOff1, locs[8].getSet().getOffCardRoles()[1]);
        roleGUIS[27] = new RoleGUI(sOff0, locs[9].getSet().getOffCardRoles()[0]);
        roleGUIS[28] = new RoleGUI(sOff1, locs[9].getSet().getOffCardRoles()[1]);
        return roleGUIS;
    }

    //aids in data binding visitors and visitor imageViews
    public void hookUpVisitors(Location[] locs) {
        VisitorsGUI tsV = new VisitorsGUI(tsV0, tsV1, tsV2, tsV3, tsV4, tsV5, tsV6, tsV7);
        locs[0].addVisitorsGUI(tsV);

        VisitorsGUI shV = new VisitorsGUI(shV0, shV1, shV2, shV3, shV4, shV5, shV6, shV7);
        locs[1].addVisitorsGUI(shV);

        VisitorsGUI cV = new VisitorsGUI(cV0, cV1, cV2, cV3, cV4, cV5, cV6, cV7);
        locs[2].addVisitorsGUI(cV);

        VisitorsGUI hV = new VisitorsGUI(hV0, hV1, hV2, hV3, hV4, hV5, hV6, hV7);
        locs[3].addVisitorsGUI(hV);

        VisitorsGUI msV = new VisitorsGUI(msV0, msV1, msV2, msV3, msV4, msV5, msV6, msV7);
        locs[4].addVisitorsGUI(msV);

        VisitorsGUI jV = new VisitorsGUI(jV0, jV1, jV2, jV3, jV4, jV5, jV6, jV7);
        locs[5].addVisitorsGUI(jV);

        VisitorsGUI gsV = new VisitorsGUI(gsV0, gsV1, gsV2, gsV3, gsV4, gsV5, gsV6, gsV7);
        locs[6].addVisitorsGUI(gsV);

        VisitorsGUI rV = new VisitorsGUI(rV0, rV1, rV2, rV3, rV4, rV5, rV6, rV7);
        locs[7].addVisitorsGUI(rV);

        VisitorsGUI bV = new VisitorsGUI(bV0, bV1, bV2, bV3, bV4, bV5, bV6, bV7);
        locs[8].addVisitorsGUI(bV);

        VisitorsGUI sV = new VisitorsGUI(sV0, sV1, sV2, sV3, sV4, sV5, sV6, sV7);
        locs[9].addVisitorsGUI(sV);

        VisitorsGUI tV = new VisitorsGUI(tV0, tV1, tV2, tV3, tV4, tV5, tV6, tV7);
        locs[10].addVisitorsGUI(tV);

        VisitorsGUI coV = new VisitorsGUI(coV0, coV1, coV2, coV3, coV4, coV5, coV6, coV7);
        locs[11].addVisitorsGUI(coV);
    }

    //assembles classes for the stat boxes at the bottom of the screen
    public void hookUpStatBoxes() {
        statBoxes[0] = new StatBoxGUI(p1RightLine, p1StatBox, p1DollarsString, p1CreditsString, p1RankPic, getPlayer(0));
        statBoxes[1] = new StatBoxGUI(p2RightLine, p2StatBox, p2DollarsString, p2CreditsString, p2RankPic, getPlayer(1));
        statBoxes[2] = new StatBoxGUI(p3RightLine, p3StatBox, p3DollarsString, p3CreditsString, p3RankPic, getPlayer(2));
        statBoxes[3] = new StatBoxGUI(p4RightLine, p4StatBox, p4DollarsString, p4CreditsString, p4RankPic, getPlayer(3));
        statBoxes[4] = new StatBoxGUI(p5RightLine, p5StatBox, p5DollarsString, p5CreditsString, p5RankPic, getPlayer(4));
        statBoxes[5] = new StatBoxGUI(p6RightLine, p6StatBox, p6DollarsString, p6CreditsString, p6RankPic, getPlayer(5));
        statBoxes[6] = new StatBoxGUI(p7RightLine, p7StatBox, p7DollarsString, p7CreditsString, p7RankPic, getPlayer(6));
        statBoxes[7] = new StatBoxGUI(p8RightLine, p8StatBox, p8DollarsString, p8CreditsString, p8RankPic, getPlayer(7));
    }

    //gives each location access to the anchor pane which holds cards
    public void hookUpLocationPanes() {
        locs[0].addCardPane(trainStationPane);
        locs[1].addCardPane(secretHideoutPane);
        locs[2].addCardPane(churchPane);
        locs[3].addCardPane(hotelPane);
        locs[4].addCardPane(mainStreetPane);
        locs[5].addCardPane(jailPane);
        locs[6].addCardPane(generalStorePane);
        locs[7].addCardPane(ranchPane);
        locs[8].addCardPane(bankPane);
        locs[9].addCardPane(saloonPane);
    }


    private Player getPlayer(int index) {
        if (index >= players.length) {
            return null;
        } else {
            return players[index];
        }
    }

    @FXML
    private Text currentPlayerText;
    @FXML
    private MenuButton moveButton;

    @FXML
    private MenuItem moveBank;

    @FXML
    private MenuItem moveCO;

    @FXML
    private MenuItem churchMove;

    @FXML
    private MenuItem moveGS;

    @FXML
    private MenuItem moveHotel;

    @FXML
    private MenuItem moveJail;

    @FXML
    private MenuItem moveMS;

    @FXML
    private MenuItem moveRanch;

    @FXML
    private MenuItem moveSaloon;

    @FXML
    private MenuItem moveSH;

    @FXML
    private MenuItem moveTrailers;

    @FXML
    private MenuItem moveTS;

    @FXML
    private Button takeRoleButton;

    @FXML
    private Button upgradeButton;

    @FXML
    private Button actButton;

    @FXML
    private Button rehearseButton;

    @FXML
    private VBox actingStatBox;

    @FXML
    private Text budget;

    @FXML
    private Text practiceChips;

    @FXML
    private Button endTurnButton;

    @FXML
    private ImageView boardPic;

    @FXML
    private ImageView jailShot1;

    @FXML
    private ImageView trainStationShot1;

    @FXML
    private ImageView trainStationShot2;

    @FXML
    private ImageView trainStationShot3;

    @FXML
    private ImageView generalStoreShot1;

    @FXML
    private ImageView generalStoreShot2;

    @FXML
    private ImageView mainStreetShot1;

    @FXML
    private ImageView mainStreetShot2;

    @FXML
    private ImageView mainStreetShot3;

    @FXML
    private ImageView saloonShot2;

    @FXML
    private ImageView saloonShot1;

    @FXML
    private ImageView bankShot1;

    @FXML
    private ImageView churchShot2;

    @FXML
    private ImageView churchShot1;

    @FXML
    private ImageView hotelShot1;

    @FXML
    private ImageView hotelShot2;

    @FXML
    private ImageView hotelShot3;

    @FXML
    private ImageView ranchShot2;

    @FXML
    private ImageView ranchShot1;

    @FXML
    private ImageView secretHideoutShot3;

    @FXML
    private ImageView secretHideoutShot2;

    @FXML
    private ImageView secretHideoutShot1;

    @FXML
    private AnchorPane trainStationPane;

    @FXML
    private AnchorPane jailPane;

    @FXML
    private AnchorPane generalStorePane;

    @FXML
    private AnchorPane mainStreetPane;

    @FXML
    private AnchorPane ranchPane;

    @FXML
    private AnchorPane bankPane;

    @FXML
    private AnchorPane saloonPane;

    @FXML
    private AnchorPane secretHideoutPane;

    @FXML
    private AnchorPane churchPane;

    @FXML
    private AnchorPane hotelPane;

    @FXML
    private ImageView gsOff1;

    @FXML
    private ImageView msOff3;

    @FXML
    private ImageView msOff2;

    @FXML
    private ImageView msOff1;

    @FXML
    private ImageView msOff0;

    @FXML
    private ImageView shOff3;

    @FXML
    private ImageView shOff2;

    @FXML
    private ImageView shOff1;

    @FXML
    private ImageView shOff0;

    @FXML
    private ImageView rOff1;

    @FXML
    private ImageView rOff0;

    @FXML
    private ImageView rOff2;

    @FXML
    private ImageView tsOff3;

    @FXML
    private ImageView tsOff2;

    @FXML
    private ImageView tsOff0;

    @FXML
    private ImageView tsOff1;

    @FXML
    private ImageView gsOff0;

    @FXML
    private ImageView jOff1;

    @FXML
    private ImageView jOff0;

    @FXML
    private ImageView hOff3;

    @FXML
    private ImageView hOff2;

    @FXML
    private ImageView hOff1;

    @FXML
    private ImageView hOff0;

    @FXML
    private ImageView cOff1;

    @FXML
    private ImageView cOff0;

    @FXML
    private ImageView bOff0;

    @FXML
    private ImageView bOff1;

    @FXML
    private ImageView sOff0;

    @FXML
    private ImageView sOff1;

    @FXML
    private ImageView jV0;

    @FXML
    private ImageView jV1;

    @FXML
    private ImageView jV2;

    @FXML
    private ImageView jV3;

    @FXML
    private ImageView jV4;

    @FXML
    private ImageView jV5;

    @FXML
    private ImageView jV6;

    @FXML
    private ImageView jV7;

    @FXML
    private ImageView hV0;

    @FXML
    private ImageView hV1;

    @FXML
    private ImageView hV2;

    @FXML
    private ImageView hV3;

    @FXML
    private ImageView tV0;

    @FXML
    private ImageView tV1;

    @FXML
    private ImageView tV2;

    @FXML
    private ImageView tV3;

    @FXML
    private ImageView tV4;

    @FXML
    private ImageView tV5;

    @FXML
    private ImageView tV6;

    @FXML
    private ImageView tV7;

    @FXML
    private ImageView msV0;

    @FXML
    private ImageView msV1;

    @FXML
    private ImageView msV2;

    @FXML
    private ImageView msV3;

    @FXML
    private ImageView msV4;

    @FXML
    private ImageView msV5;

    @FXML
    private ImageView msV6;

    @FXML
    private ImageView msV7;

    @FXML
    private ImageView cV0;

    @FXML
    private ImageView cV1;

    @FXML
    private ImageView cV2;

    @FXML
    private ImageView cV3;

    @FXML
    private ImageView cV4;

    @FXML
    private ImageView cV5;

    @FXML
    private ImageView cV6;

    @FXML
    private ImageView cV7;

    @FXML
    private ImageView rV0;

    @FXML
    private ImageView rV1;

    @FXML
    private ImageView rV2;

    @FXML
    private ImageView rV3;

    @FXML
    private ImageView rV4;

    @FXML
    private ImageView rV5;

    @FXML
    private ImageView rV6;

    @FXML
    private ImageView rV7;

    @FXML
    private ImageView shV0;

    @FXML
    private ImageView shV1;

    @FXML
    private ImageView shV2;

    @FXML
    private ImageView shV3;

    @FXML
    private ImageView shV4;

    @FXML
    private ImageView shV5;

    @FXML
    private ImageView shV6;

    @FXML
    private ImageView shV7;

    @FXML
    private ImageView coV0;

    @FXML
    private ImageView coV1;

    @FXML
    private ImageView coV2;

    @FXML
    private ImageView coV3;

    @FXML
    private ImageView coV4;

    @FXML
    private ImageView coV5;

    @FXML
    private ImageView coV6;

    @FXML
    private ImageView coV7;

    @FXML
    private ImageView gsV0;

    @FXML
    private ImageView gsV1;

    @FXML
    private ImageView gsV2;

    @FXML
    private ImageView gsV3;

    @FXML
    private ImageView gsV4;

    @FXML
    private ImageView gsV5;

    @FXML
    private ImageView gsV6;

    @FXML
    private ImageView gsV7;

    @FXML
    private ImageView bV0;

    @FXML
    private ImageView bV1;

    @FXML
    private ImageView bV2;

    @FXML
    private ImageView bV3;

    @FXML
    private ImageView bV4;

    @FXML
    private ImageView bV5;

    @FXML
    private ImageView bV6;

    @FXML
    private ImageView bV7;

    @FXML
    private ImageView sV0;

    @FXML
    private ImageView sV1;

    @FXML
    private ImageView sV2;

    @FXML
    private ImageView sV3;

    @FXML
    private ImageView sV4;

    @FXML
    private ImageView sV5;

    @FXML
    private ImageView sV6;

    @FXML
    private ImageView sV7;

    @FXML
    private ImageView tsV4;

    @FXML
    private ImageView tsV5;

    @FXML
    private ImageView tsV6;

    @FXML
    private ImageView tsV7;

    @FXML
    private ImageView hV4;

    @FXML
    private ImageView hV5;

    @FXML
    private ImageView hV6;

    @FXML
    private ImageView hV7;

    @FXML
    private ImageView tsV0;

    @FXML
    private ImageView tsV1;

    @FXML
    private ImageView tsV2;

    @FXML
    private ImageView tsV3;

    @FXML
    private VBox p1StatBox;

    @FXML
    private Text p1DollarsString;

    @FXML
    private Text p1CreditsString;

    @FXML
    private ImageView p1RankPic;

    @FXML
    private Line p1RightLine;

    @FXML
    private VBox p2StatBox;

    @FXML
    private Text p2DollarsString;

    @FXML
    private Text p2CreditsString;

    @FXML
    private ImageView p2RankPic;

    @FXML
    private Line p2RightLine;

    @FXML
    private VBox p3StatBox;

    @FXML
    private Text p3DollarsString;

    @FXML
    private Text p3CreditsString;

    @FXML
    private ImageView p3RankPic;

    @FXML
    private Line p3RightLine;

    @FXML
    private VBox p4StatBox;

    @FXML
    private Text p4DollarsString;

    @FXML
    private Text p4CreditsString;

    @FXML
    private ImageView p4RankPic;

    @FXML
    private Line p4RightLine;

    @FXML
    private VBox p5StatBox;

    @FXML
    private Text p5DollarsString;

    @FXML
    private Text p5CreditsString;

    @FXML
    private ImageView p5RankPic;

    @FXML
    private Line p5RightLine;

    @FXML
    private VBox p6StatBox;

    @FXML
    private Text p6DollarsString;

    @FXML
    private Text p6CreditsString;

    @FXML
    private ImageView p6RankPic;

    @FXML
    private Line p6RightLine;

    @FXML
    private VBox p7StatBox;

    @FXML
    private Text p7DollarsString;

    @FXML
    private Text p7CreditsString;

    @FXML
    private ImageView p7RankPic;

    @FXML
    private Line p7RightLine;

    @FXML
    private VBox p8StatBox;

    @FXML
    private Text p8DollarsString;

    @FXML
    private Text p8CreditsString;

    @FXML
    private ImageView p8RankPic;

    @FXML
    private Line p8RightLine;

    @FXML
    void actClicked(ActionEvent event) {
        manager.act();
    }

    @FXML
    void bankMoveClick(ActionEvent event) {
        manager.move(locs[8]);
    }

    @FXML
    void coMoveClicked(ActionEvent event) {
        manager.move(locs[11]);
    }

    @FXML
    void endTurnClicked(ActionEvent event) {
        manager.endTurn();
    }

    @FXML
    void gsMoveClicked(ActionEvent event) {
        manager.move(locs[6]);
    }

    @FXML
    void hotelMoveClicked(ActionEvent event) {
        manager.move(locs[3]);
    }

    @FXML
    void jailMoveClicked(ActionEvent event) {
        manager.move(locs[5]);
    }

    @FXML
    void moveChurchClicked(ActionEvent event) {
        manager.move(locs[2]);
    }

    @FXML
    void moveClicked(ActionEvent event) {

    }

    @FXML
    void msMoveClicked(ActionEvent event) {
        manager.move(locs[4]);
    }

    @FXML
    void ranchMoveClicked(ActionEvent event) {
        manager.move(locs[7]);
    }

    @FXML
    void rehearseClicked(ActionEvent event) {
        manager.rehearse();
    }

    @FXML
    void saloonMoveClicked(ActionEvent event) {
        manager.move(locs[9]);
    }

    @FXML
    void shMoveClicked(ActionEvent event) {
        manager.move(locs[1]);
    }

    @FXML
    void takeRoleClicked(ActionEvent event) {
        manager.promptTakeRole();
    }

    @FXML
    void trailersMoveClicked(ActionEvent event) {
        manager.move(locs[10]);
    }

    @FXML
    void tsMoveClicked(ActionEvent event) {
        manager.move(locs[0]);
    }

    @FXML
    void upgradeClicked(ActionEvent event) {
        manager.promptUpgrade();
    }

}
