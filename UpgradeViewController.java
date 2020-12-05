import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

//shows the view for selecting the upgrade rank the player wants
//changes its public fields that are available to be read by the game manager
//reflecting the users choice
public class UpgradeViewController {

    private Stage stage;
    public int intendedRank;
    public boolean payWithDollars;

    public void addStage(Stage stage) {
        this.stage = stage;
    }

    public void popup(Player player) {
        updateGUI(player);
        intendedRank = -1;
        stage.showAndWait();
    }

    //sets up the bottom row of the gui to make it accurate to the players stats
    public void updateGUI(Player player) {
        //mostly just make sure the three elements at the bottom are correct
        upgradingPlayerCredits.setText(Integer.toString(player.getCredits()));
        upgradingPlayerDollars.setText(Integer.toString(player.getDollars()));
        PlayerImageCalculator pRank = new PlayerImageCalculator();
        String playerImage = pRank.getPlayerImage(player);
        Image updRole = new Image(getClass().getResourceAsStream(playerImage));
        upgradingPlayerRank.setImage(updRole);
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
        intendedRank = 2;
        payWithDollars = false;
        stage.close();
    }

    @FXML
    void r2DollarsClicked(ActionEvent event) {
        intendedRank = 2;
        payWithDollars = true;
        stage.close();
    }

    @FXML
    void r3CreditClicked(ActionEvent event) {
        intendedRank = 3;
        payWithDollars = false;
        stage.close();
    }

    @FXML
    void r3DollarsClicked(ActionEvent event) {
        intendedRank = 3;
        payWithDollars = true;
        stage.close();
    }

    @FXML
    void r4CreditClicked(ActionEvent event) {
        intendedRank = 4;
        payWithDollars = false;
        stage.close();
    }

    @FXML
    void r4DollarsClicked(ActionEvent event) {
        intendedRank = 4;
        payWithDollars = true;
        stage.close();
    }

    @FXML
    void r5CreditClicked(ActionEvent event) {
        intendedRank = 5;
        payWithDollars = false;
        stage.close();
    }

    @FXML
    void r5DollarsClicked(ActionEvent event) {
        intendedRank = 5;
        payWithDollars = true;
        stage.close();
    }

    @FXML
    void r6CreditClicked(ActionEvent event) {
        intendedRank = 6;
        payWithDollars = false;
        stage.close();
    }

    @FXML
    void r6DollarsClicked(ActionEvent event) {
        intendedRank = 6;
        payWithDollars = true;
        stage.close();
    }

}

