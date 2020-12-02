import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GameViewController {

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
    private ImageView trainStationCard;

    @FXML
    private AnchorPane jailPane;

    @FXML
    private ImageView jailCard;

    @FXML
    private AnchorPane generalStorePane;

    @FXML
    private ImageView generalStoreCard;

    @FXML
    private ImageView gsOff11;

    @FXML
    private ImageView gsOff12;

    @FXML
    private ImageView gsOff13;

    @FXML
    private AnchorPane mainStreetPane;

    @FXML
    private ImageView mainStreetCard;

    @FXML
    private ImageView sOff13;

    @FXML
    private AnchorPane ranchPane;

    @FXML
    private ImageView ranchCard;

    @FXML
    private AnchorPane bankPane;

    @FXML
    private ImageView bankCard;

    @FXML
    private AnchorPane saloonPane;

    @FXML
    private ImageView saloonCard;

    @FXML
    private ImageView sOff11;

    @FXML
    private ImageView sOff12;

    @FXML
    private AnchorPane secretHideoutPane;

    @FXML
    private ImageView secretHideoutCard;

    @FXML
    private AnchorPane churchPane;

    @FXML
    private ImageView churchCard;

    @FXML
    private AnchorPane hotelPane;

    @FXML
    private ImageView hotelCard;

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

    }

    @FXML
    void bankMoveClick(ActionEvent event) {

    }

    @FXML
    void coMoveClicked(ActionEvent event) {

    }

    @FXML
    void endTurnClicked(ActionEvent event) {

    }

    @FXML
    void gsMoveClicked(ActionEvent event) {

    }

    @FXML
    void hotelMoveClicked(ActionEvent event) {

    }

    @FXML
    void jailMoveClicked(ActionEvent event) {

    }

    @FXML
    void moveChurchClicked(ActionEvent event) {

    }

    @FXML
    void moveClicked(ActionEvent event) {

    }

    @FXML
    void msMoveClicked(ActionEvent event) {

    }

    @FXML
    void ranchMoveClicked(ActionEvent event) {

    }

    @FXML
    void rehearseClicked(ActionEvent event) {

    }

    @FXML
    void saloonMoveClicked(ActionEvent event) {

    }

    @FXML
    void shMoveClicked(ActionEvent event) {

    }

    @FXML
    void takeRoleClicked(ActionEvent event) {

    }

    @FXML
    void trailersMoveClicked(ActionEvent event) {

    }

    @FXML
    void tsMoveClicked(ActionEvent event) {

    }

    @FXML
    void upgradeClicked(ActionEvent event) {

    }

}

