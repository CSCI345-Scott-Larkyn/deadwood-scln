import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoleGUI {
    private ImageView imageView;
    private Role role;
    private PlayerImageCalculator calc = new PlayerImageCalculator();

    public RoleGUI(ImageView imageView, Role role) {
        this.imageView = imageView;
        this.imageView.setVisible(false);
        this.role = role;
    }

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
