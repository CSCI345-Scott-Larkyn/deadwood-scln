import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VisitorsGUI {

    private PlayerImageCalculator calc = new PlayerImageCalculator();
    private ImageView[] players = new ImageView[8];
    public VisitorsGUI(ImageView p1, ImageView p2, ImageView p3, ImageView p4, ImageView p5, ImageView p6, ImageView p7, ImageView p8) {
        players[0] = p1;
        players[1] = p2;
        players[2] = p3;
        players[3] = p4;
        players[4] = p5;
        players[5] = p6;
        players[6] = p7;
        players[7] = p8;
    }

    public void update(List<Player> visitors) {
        int num = visitors.size();
        for (int i = 0; i < num; i++) {
            players[i].setImage(new Image(calc.getPlayerImage(visitors.get(i))));
            players[i].setVisible(true);
        }
        for (int i = num; i < players.length; i++) {
            players[i].setVisible(false);
        }
    }
    public ImageView[] getPlayer(){
        return players;
    }
}
