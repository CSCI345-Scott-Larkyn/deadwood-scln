import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerSelectController implements Initializable{

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
        System.out.println("player button start selected...");
        int numPlayers = Integer.parseInt(String.valueOf(plNum.getValue()));
    }
}

