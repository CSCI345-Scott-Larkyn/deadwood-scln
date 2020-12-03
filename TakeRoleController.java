import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class TakeRoleController {

    private Stage stage;

    public void addStage(Stage stage) {
        this.stage = stage;
    }

    public void popup(Location loc) {
        updateGUI(loc);
        stage.showAndWait();
    }

    public void updateGUI(Location loc) {
        //each role should have the correct image for the rank
        //can use playerImageCalculator.getBlankPlayerImage
        //and when there are fewer than 3 on card or 4 off card roles
        //the rest should be invisible
        Set curSet = loc.getSet();
        Role[] offCardR = curSet.getOffCardRoles();
        Role[] onCardR = curSet.getCard().getRoles();
        PlayerImageCalculator pImage = new PlayerImageCalculator();

        //offCard roles
        for (int i = 0; i < 4; i++) {


            if (i == 0) {
                if(offCardR.length <= i ||offCardR[0].getIsOccupied()){
                    offCardPic1.setVisible(false);
                    offCardButton1.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    offCardPic1.setImage(updRole);
                    offCardButton1.setVisible(true);
                }
            }
            if (i == 1) {
                if(offCardR.length <= i||offCardR[1].getIsOccupied()){
                    offCardPic2.setVisible(false);
                    offCardButton2.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    offCardPic2.setImage(updRole);
                    offCardButton2.setVisible(true);
                }
            }
            if (i == 2 ) {
                if(offCardR.length <= i||offCardR[2].getIsOccupied()){
                    offCardPic3.setVisible(false);
                    offCardButton3.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    offCardPic3.setImage(updRole);
                    offCardButton3.setVisible(true);
                }
            }
            if (i == 3) {
                if(offCardR.length <= i||offCardR[3].getIsOccupied()){
                    offCardPic4.setVisible(false);
                    offCardButton4.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    offCardPic4.setImage(updRole);
                    offCardButton4.setVisible(true);
                }
            }
        }
        for (int j = 0; j < 2; j++) {


            if (j == 0) {
                if(onCardR.length <= j ||onCardR[0].getIsOccupied()){
                    onCardPic1.setVisible(false);
                    onCardButton1.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(onCardR[j].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    onCardPic1.setImage(updRole);
                    onCardButton1.setVisible(true);
                }
            }
            if (j == 1) {
                if(onCardR.length <= j||onCardR[1].getIsOccupied()){
                    onCardPic2.setVisible(false);
                    onCardButton2.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(onCardR[j].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    onCardPic2.setImage(updRole);
                    onCardButton2.setVisible(true);
                }
            }
            if (j == 2 ) {
                if(onCardR.length <= j||onCardR[2].getIsOccupied()){
                    onCardPic3.setVisible(false);
                    onCardButton3.setVisible(false);
                }else{
                    String r = pImage.getBlankPlayerImage(onCardR[j].getRank());
                    Image updRole = new Image(getClass().getResourceAsStream(r));
                    onCardPic3.setImage(updRole);
                    onCardButton3.setVisible(true);
                }
            }
        }
    }

    @FXML
    private Button onCardButton1;

    @FXML
    private ImageView onCardPic1;

    @FXML
    private Button onCardButton2;

    @FXML
    private ImageView onCardPic2;

    @FXML
    private Button onCardButton3;

    @FXML
    private ImageView onCardPic3;

    @FXML
    private Button offCardButton1;

    @FXML
    private ImageView offCardPic1;

    @FXML
    private Button offCardButton2;

    @FXML
    private ImageView offCardPic2;

    @FXML
    private Button offCardButton3;

    @FXML
    private ImageView offCardPic3;

    @FXML
    private Button offCardButton4;

    @FXML
    private ImageView offCardPic4;

    @FXML
    void offC1CLicked(ActionEvent event) {

    }

    @FXML
    void offC2Clicked(ActionEvent event) {

    }

    @FXML
    void offC3Clicked(ActionEvent event) {

    }

    @FXML
    void offC5Clicked(ActionEvent event) {

    }

    @FXML
    void onC1Clicked(ActionEvent event) {

    }

    @FXML
    void onC2Clicked(ActionEvent event) {

    }

    @FXML
    void onC3Clicked(ActionEvent event) {

    }

}

