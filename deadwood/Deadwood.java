////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//the main class to be run to play the game
//requires a single int argument for the number of players
//runs the game for the proper number of days 
//ands prompts the GameManager to show the winner when the game is over
package deadwood;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Deadwood extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("playerSelectFX.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
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