import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class StatBoxGUI {
    private Line rightLine;
    private VBox box;
    private Text dollars;
    private Text credits;
    private ImageView rank;
    private Player player;

    public StatBoxGUI(Line rightLine, VBox box, Text dollars, Text credits, ImageView rank, Player player) {
        this.rightLine = rightLine;
        this.box = box;
        this.dollars = dollars;
        this.credits = credits;
        this.rank = rank;
        this.player = player;
    }

    public void update() {

        if(player == null){
            rightLine.setVisible(false);
            box.setVisible(false);
        }else{
            rightLine.setVisible(true);
            box.setVisible(true);
            dollars.setText("Dollars: " + Integer.toString(player.getDollars()));
            credits.setText("Credits: " + Integer.toString(player.getCredits()));
            PlayerImageCalculator pImage = new PlayerImageCalculator();
            String r = pImage.getPlayerImage(player);
            Image pRank = new Image(getClass().getResourceAsStream(r));
            rank.setImage(pRank);
        }
    }
}
