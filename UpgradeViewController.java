import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpgradeViewController {

    private Stage stage;

    public void addStage(Stage stage) {
        this.stage = stage;
    }

    public void popup(Player player) {
        updateUI(player);
        stage.showAndWait();
    }

    public void updateUI(Player player) {
        //mostly just make sure the three elements at the bottom are correct
    }

    @FXML
    private Button rank2Dollars;

    @FXML
    private Button rank3Dollars;

    @FXML
    private Button rank4Dollars;

    @FXML
    private Button rank5Dollars;

    @FXML
    private Button rank6Dollars;

    @FXML
    private Button rank2Credits;

    @FXML
    private Button rank3Credits;

    @FXML
    private Button rank4Credits;

    @FXML
    private Button rank5Credits;

    @FXML
    private Button rank6Credits;

    @FXML
    private ImageView upgradingPlayerRank;

    @FXML
    private Text upgradingPlayerDollars;

    @FXML
    private Text upgradingPlayerCredits;

    @FXML
    void r2CreditClicked(ActionEvent event) {

    }

    @FXML
    void r2DollarsClicked(ActionEvent event) {

    }

    @FXML
    void r3CreditClicked(ActionEvent event) {

    }

    @FXML
    void r3DollarsClicked(ActionEvent event) {

    }

    @FXML
    void r4CreditClicked(ActionEvent event) {

    }

    @FXML
    void r4DollarsClicked(ActionEvent event) {

    }

    @FXML
    void r5CreditClicked(ActionEvent event) {

    }

    @FXML
    void r5DollarsClicked(ActionEvent event) {

    }

    @FXML
    void r6CreditClicked(ActionEvent event) {

    }

    @FXML
    void r6DollarsClicked(ActionEvent event) {

    }

}

