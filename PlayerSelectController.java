import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

//pops up the spinner window for selecting the number of players for the game
public class PlayerSelectController implements Initializable {

    private Stage stage;
    public int playerNum;

    public void addStage(Stage stage) {
        this.stage = stage;
    }

    public void popup() {
        stage.showAndWait();
    }

    @FXML
    private Spinner<Integer> playerNumSpinner;
    SpinnerValueFactory<Integer> plNum = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,8,2);

    @FXML
    private Button startButton;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        playerNumSpinner.setValueFactory(plNum);
    }

    @FXML
    void startButtonClicked(){
        playerNum = plNum.getValue();
        stage.close();
    }
}

