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
        //if player == null
            //make everything invisible
        //else
    }
}
