import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//just used to link off card roles in the model to imageviews in the GUI
public class RoleGUI {
    private ImageView imageView;
    private Role role;
    private PlayerImageCalculator calc = new PlayerImageCalculator();

    public RoleGUI(ImageView imageView, Role role) {
        this.imageView = imageView;
        this.imageView.setVisible(false);
        this.role = role;
    }

    //will make the GUI have the proper visibility and player image
    public void updateGUI() {
        if (role.getIsOccupied()) {
            Player player = role.getPlayer();
            imageView.setImage(new Image(calc.getPlayerImage(player)));
            imageView.setVisible(true);
        } else {
            imageView.setVisible(false);
        }
    }
}
