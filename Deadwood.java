////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//the main class to be run to play the game
//requires a single int argument for the number of players
//runs the game for the proper number of days 
//ands prompts the GameManager to show the winner when the game is over

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

//where the program starts
//initializes all of the GUI controllers then passes off control to the GameManagerFX
public class Deadwood extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Deadwood- Implementation by Scott Carlson & Larkyn Nelson");
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("gameViewFX.fxml"));
        Scene gameScene = new Scene(gameLoader.load());
        primaryStage.setScene(gameScene);
        GameViewController gameController = gameLoader.getController();

        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("playerSelectFX.fxml"));
        Stage playerStage = new Stage();
        playerStage.setScene(new Scene(playerLoader.load()));
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.initOwner(primaryStage);
        PlayerSelectController playerController = playerLoader.getController();
        playerController.addStage(playerStage);

        FXMLLoader upgradeLoader = new FXMLLoader(getClass().getResource("upgradeViewFX.fxml"));
        Stage upgradeStage = new Stage();
        upgradeStage.setScene(new Scene(upgradeLoader.load()));
        upgradeStage.initModality(Modality.APPLICATION_MODAL);
        upgradeStage.initOwner(primaryStage);
        UpgradeViewController upgradeController = upgradeLoader.getController();
        upgradeController.addStage(upgradeStage);

        FXMLLoader roleLoader = new FXMLLoader(getClass().getResource("takeRoleFX.fxml"));
        Stage roleStage = new Stage();
        roleStage.setScene(new Scene(roleLoader.load()));
        roleStage.initModality(Modality.APPLICATION_MODAL);
        roleStage.initOwner(primaryStage);
        TakeRoleController roleController = roleLoader.getController();
        roleController.addStage(roleStage);

        FXMLLoader winnerLoader = new FXMLLoader(getClass().getResource("showWinnersFX.fxml"));
        Stage winnerStage = new Stage();
        winnerStage.setScene(new Scene(winnerLoader.load()));
        winnerStage.initModality(Modality.APPLICATION_MODAL);
        ShowWinnersController winnersController = winnerLoader.getController();
        winnersController.addStage(winnerStage);

        GameManagerFX managerFX = new GameManagerFX(gameController, upgradeController, playerController, roleController, winnersController, primaryStage, gameScene);
        managerFX.playGame();
    }
}