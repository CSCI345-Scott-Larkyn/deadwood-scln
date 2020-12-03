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
import javafx.stage.Stage;

import java.io.IOException;

public class Deadwood extends Application {


    Stage stage;
    Scene scene1, scene2, scene3, scene4;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        primaryStage.setTitle("Deadwood- Implementation by Scott Carlson & Larkyn Nelson");
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("gameViewFX.fxml"));
        Parent root = gameLoader.load();
        GameViewController gvController = gameLoader.getController();

        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("playerSelectFX.fxml"));
        Parent root2 = playerLoader.load();
        PlayerSelectController psController = playerLoader.getController();

        FXMLLoader upgradeLoader = new FXMLLoader(getClass().getResource("upgradeViewFX.fxml"));
        Parent root3 = upgradeLoader.load();
        UpgradeViewController upgradeController = upgradeLoader.getController();

        FXMLLoader roleLoader = new FXMLLoader(getClass().getResource("takeRoleFX.fxml"));
        Parent root4 = roleLoader.load();
        TakeRoleController roleController = roleLoader.getController();

        Scene scene1 = new Scene(root);
        Scene scene2 = new Scene(root2);
        Scene scene3 = new Scene(root3);
        Scene scene4 = new Scene(root4);



        primaryStage.show();




    }


//    public static void main(String[] args) throws NullPointerException, ParserConfigurationException {
//
//        int numPlayers = Integer.parseInt(args[0]);
//        int r = 8;
//        if (numPlayers < 2 || numPlayers > 8) {
//            System.out.println("That is not a valid number of players");
//        } else {
//            GameManager manager = new GameManager(numPlayers);
//            manager.setup("board.xml", "cards.xml");
//            int numDays = 4;
//            if (numPlayers < 4)
//                numDays = 3;
//            for (int day = 1; day <= numDays; day++) {
//                System.out.println("Day " + day);
//                manager.playDay();
//                if (day < numDays) {
//                    manager.endDay();
//                }
//            }
//            manager.decideWinner();
//        }
//    }


}