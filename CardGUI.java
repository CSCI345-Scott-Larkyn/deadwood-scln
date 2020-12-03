import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CardGUI {
    private Card card;
    AnchorPane pane;
    ImageView imageView;
    Image front;
    ImageView[] roles;
    PlayerImageCalculator calc = new PlayerImageCalculator();

    public CardGUI(String frontString, String backString, Card card, int roleNum) {
        front = new Image(frontString);
        imageView = new ImageView(backString);
        imageView.setFitWidth(110);
        imageView.setFitHeight(65);
        this.card = card;
        if (roleNum == 1) {
            makeRoles1();
        } else if (roleNum == 2){
            makeRoles2();
        } else {
            makeRoles3();
        }
    }

    public void update() {
        Role[] modelRoles = card.getRoles();
        for (int i = 0; i < roles.length; i++) {
            if (modelRoles[i].getIsOccupied()) {
                String fileName = calc.getPlayerImage(modelRoles[i].getPlayer());
                roles[i].setImage(new Image(fileName));
                roles[i].setVisible(true);
            } else {
                roles[i].setVisible(false);
            }
        }
        if (card.isFaceUp()) {
            imageView.setImage(front);
        }
    }

    private void makeRoles1() {
        roles = new ImageView[1];
        ImageView role1 = new ImageView();
        role1.setFitWidth(20);
        role1.setFitHeight(20);
        role1.setX(42);
        role1.setY(26);
        role1.setVisible(false);
        roles[0] = role1;

        pane = new AnchorPane(imageView, roles[0]);
    }

    private void makeRoles2() {
        roles = new ImageView[2];
        ImageView role1 = new ImageView();
        role1.setFitWidth(20);
        role1.setFitHeight(20);
        role1.setX(26);
        role1.setY(26);
        role1.setVisible(false);
        roles[0] = role1;

        ImageView role2 = new ImageView();
        role2.setFitWidth(20);
        role2.setFitHeight(20);
        role2.setX(60);
        role2.setY(26);
        role2.setVisible(false);
        roles[1] = role2;

        pane = new AnchorPane(imageView, roles[0], roles[1]);
    }

    private void makeRoles3() {
        roles = new ImageView[3];

        ImageView role1 = new ImageView();
        role1.setFitWidth(20);
        role1.setFitHeight(20);
        role1.setX(9);
        role1.setY(26);
        role1.setVisible(false);
        roles[0] = role1;

        ImageView role2 = new ImageView();
        role2.setFitWidth(20);
        role2.setFitHeight(20);
        role2.setX(42);
        role2.setY(26);
        role2.setVisible(false);
        roles[1] = role2;

        ImageView role3 = new ImageView();
        role3.setFitWidth(20);
        role3.setFitHeight(20);
        role3.setX(76);
        role3.setY(26);
        role3.setVisible(false);
        roles[2] = role3;

        pane = new AnchorPane(imageView, roles[0], roles[1], roles[2]);
    }

    public AnchorPane getCard() {
        return pane;
    }
}
