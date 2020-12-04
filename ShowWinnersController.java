import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

public class ShowWinnersController {

    private Stage stage;

    public void addStage(Stage stage) {
        this.stage = stage;
    }

    public void popup(Player[] players) {
        Player[] sortedPlayers = sortPlayers(players);
        updateGUI(sortedPlayers);
        stage.showAndWait();
    }

    private Player[] sortPlayers(Player[] players) {
        Player[] sortedPlayers = new Player[players.length];
        for (int i = 0; i < players.length; i++) {
            sortedPlayers[i] = players[i];
        }
        Arrays.sort(sortedPlayers, Collections.reverseOrder());
        return sortedPlayers;
    }

    private void updateGUI(Player[] sortedPlayers) {
        Text[] places = new Text[] {r1Place, r2Place, r3Place, r4Place, r5Place, r6Place, r7Place, r8Place};
        Text[] players = new Text[] {r1Player, r2Player, r3Player, r4Player, r5Player, r6Player, r7Player, r8Player};
        Text[] points = new Text[] {r1Points, r2Points, r3Points, r4Points, r5Points, r6Points, r7Points, r8Points};
        String[] placeStrings = new String[] {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};

        int numPlayers = sortedPlayers.length;
        for (int i = 0; i < numPlayers; i++) {
            if (i != 0 && sortedPlayers[i].getScore() == sortedPlayers[i-1].getScore()) {
                places[i].setText(places[i-1].getText());
            } else {
                places[i].setText(placeStrings[i]);
            }
            players[i].setText("Player " + sortedPlayers[i].getPlayerNum());
            points[i].setText("" + sortedPlayers[i].getScore());
        }

        for (int i = numPlayers; i < 8; i++) {
            places[i].setVisible(false);
            players[i].setVisible(false);
            points[i].setVisible(false);
        }
    }

    @FXML
    private Text r1Place;

    @FXML
    private Text r1Player;

    @FXML
    private Text r1Points;

    @FXML
    private Text r2Place;

    @FXML
    private Text r2Points;

    @FXML
    private Text r2Player;

    @FXML
    private Text r8Points;

    @FXML
    private Text r8Player;

    @FXML
    private Text r8Place;

    @FXML
    private Text r7Points;

    @FXML
    private Text r7Player;

    @FXML
    private Text r7Place;

    @FXML
    private Text r6Points;

    @FXML
    private Text r6Player;

    @FXML
    private Text r6Place;

    @FXML
    private Text r5Points;

    @FXML
    private Text r5Player;

    @FXML
    private Text r5Place;

    @FXML
    private Text r4Points;

    @FXML
    private Text r4Player;

    @FXML
    private Text r4Place;

    @FXML
    private Text r3Points;

    @FXML
    private Text r3Player;

    @FXML
    private Text r3Place;

}
