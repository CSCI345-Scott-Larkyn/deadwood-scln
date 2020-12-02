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

public class PlayerSelectController implements Initializable{

    @FXML
    private Spinner<Integer> playerNumSpinner;
    SpinnerValueFactory<Integer> plNum = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,8,2);

    @FXML
    private Button startButton;
    private static boolean startBtnClick;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        playerNumSpinner.setValueFactory(plNum);
    }

    @FXML
    void startButtonClicked(){
        startBtnClick = true;
        int numPlayers = Integer.parseInt(String.valueOf(plNum.getValue()));
        System.out.println("player button start selected..." + numPlayers);

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameViewFX.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load game..... try again");
        }
    }

    public static boolean getStartBtnClick() {
        return startBtnClick;
    }
}

