import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

//pops up the window that lets the player choose what rank they want to take
//saves their choice in public fields that can be accessed by the game manager later
public class TakeRoleController {

    private Stage stage;
    public Role roleToTake;
    public boolean isRoleOnCard;
    private Role[] offCardR;
    private Role[] onCardR;
    private ImageView[] offCardPics;
    private ImageView[] onCardPics;
    private Button[] offCardButtons;
    private Button[] onCardButtons;

    //used for setup
    public void addStage(Stage stage) {
        this.stage = stage;
        offCardPics = new ImageView[] {offCardPic1, offCardPic2, offCardPic3, offCardPic4};
        onCardPics = new ImageView[] {onCardPic1, onCardPic2, onCardPic3};
        offCardButtons = new Button[] {offCardButton1, offCardButton2, offCardButton3, offCardButton4};
        onCardButtons = new Button[] {onCardButton1, onCardButton2, onCardButton3};
    }

    //makes the window visible and waits for user input
    public void popup(Location loc, Player curPlayer) {
        updateGUI(loc, curPlayer);
        roleToTake = null;
        stage.showAndWait();
    }

    //updates GUI to only show roles that are unoccupied and of accessible rank
    public void updateGUI(Location loc, Player player) {
        //each role should have the correct image for the rank
        //can use playerImageCalculator.getBlankPlayerImage
        //and when there are fewer than 3 on card or 4 off card roles
        //the rest should be invisible
        Set curSet = loc.getSet();
        if(curSet != null) {
            Role[] offCardRoles = curSet.getOffCardRoles();
            Role[] onCardRoles = curSet.getCard().getRoles();
            int rank = player.getRank();
            offCardR = makeValidRoles(offCardRoles, rank);
            onCardR = makeValidRoles(onCardRoles, rank);
            PlayerImageCalculator pImage = new PlayerImageCalculator();

            //off card
            for (int i = 0; i < 4; i++) {
                if (i < offCardR.length) {
                    offCardPics[i].setImage(new Image(pImage.getBlankPlayerImage(offCardR[i].getRank())));
                    offCardPics[i].setVisible(true);
                    offCardButtons[i].setVisible(true);
                } else {
                    offCardPics[i].setVisible(false);
                    offCardButtons[i].setVisible(false);
                }
            }

            //on card
            for (int i = 0; i < 3; i++) {
                if (i < onCardR.length) {
                    onCardPics[i].setImage(new Image(pImage.getBlankPlayerImage(onCardR[i].getRank())));
                    onCardPics[i].setVisible(true);
                    onCardButtons[i].setVisible(true);
                } else {
                    onCardPics[i].setVisible(false);
                    onCardButtons[i].setVisible(false);
                }
            }


            //offCard roles
//            for (int i = 0; i < 4; i++) {
//
//
//                if (i == 0) {
//                    if (offCardR.length <= i || offCardR[0].getIsOccupied()) {
//                        offCardPic1.setVisible(false);
//                        offCardButton1.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        offCardPic1.setImage(updRole);
//                        if (rank >= offCardR[i].getRank()) {
//                            offCardButton1.setVisible(true);
//                        } else {
//                            offCardButton1.setDisable(false);
//                            offCardButton1.setVisible(false);
//                        }
//                    }
//                }
//                if (i == 1) {
//                    if (offCardR.length <= i || offCardR[1].getIsOccupied()) {
//                        offCardPic2.setVisible(false);
//                        offCardButton2.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        offCardPic2.setImage(updRole);
//                        if (rank >= offCardR[i].getRank()) {
//                            offCardButton2.setVisible(true);
//                        } else {
//                            offCardButton2.setDisable(false);
//                            offCardButton2.setVisible(false);
//                        }
//                    }
//                }
//                if (i == 2) {
//                    if (offCardR.length <= i || offCardR[2].getIsOccupied()) {
//                        offCardPic3.setVisible(false);
//                        offCardButton3.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        offCardPic3.setImage(updRole);
//                        if (rank >= offCardR[i].getRank()) {
//                            offCardButton3.setVisible(true);
//                        } else {
//                            offCardButton3.setDisable(false);
//                            offCardButton3.setVisible(false);
//                        }
//                    }
//                }
//                if (i == 3) {
//                    if (offCardR.length <= i || offCardR[3].getIsOccupied()) {
//                        offCardPic4.setVisible(false);
//                        offCardButton4.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(offCardR[i].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        offCardPic4.setImage(updRole);
//                        if (rank >= offCardR[i].getRank()) {
//                            offCardButton4.setVisible(true);
//                        } else {
//                            offCardButton4.setDisable(false);
//                            offCardButton4.setVisible(false);
//                        }
//                    }
//                }
//            }
//            for (int j = 0; j < 3; j++) {
//
//                if (j == 0) {
//                    if (onCardR.length <= j || onCardR[0].getIsOccupied()) {
//                        onCardPic1.setVisible(false);
//                        onCardButton1.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(onCardR[j].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        onCardPic1.setImage(updRole);
//                        if (rank >= onCardR[j].getRank()) {
//                            onCardButton1.setVisible(true);
//                        } else {
//                            onCardButton1.setDisable(false);
//                            onCardButton1.setVisible(false);
//                        }
//                    }
//                }
//                if (j == 1) {
//                    if (onCardR.length <= j || onCardR[1].getIsOccupied()) {
//                        onCardPic2.setVisible(false);
//                        onCardButton2.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(onCardR[j].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        onCardPic2.setImage(updRole);
//                        if (rank >= onCardR[j].getRank()) {
//                            onCardButton2.setVisible(true);
//                        } else {
//                            onCardButton2.setDisable(false);
//                            onCardButton2.setVisible(false);
//                        }
//                    }
//                }
//                if (j == 2) {
//                    if (onCardR.length <= j || onCardR[2].getIsOccupied()) {
//                        onCardPic3.setVisible(false);
//                        onCardButton3.setVisible(false);
//                    } else {
//                        String r = pImage.getBlankPlayerImage(onCardR[j].getRank());
//                        Image updRole = new Image(getClass().getResourceAsStream(r));
//                        onCardPic3.setImage(updRole);
//                        if (rank >= onCardR[j].getRank()) {
//                            onCardButton3.setVisible(true);
//                        } else {
//                            onCardButton3.setDisable(false);
//                            onCardButton3.setVisible(false);
//                        }
//                    }
//                }
//          }
        }
    }

    private Role[] makeValidRoles(Role[] roles, int rank) {
         int numValid = 0;
         for (int i = 0; i < roles.length; i++) {
             Role role = roles[i];
             if (!role.getIsOccupied() && role.getRank() <= rank) {
                 numValid++;
             }
         }
         Role[] validRoles = new Role[numValid];
         int index = 0;
        for (int i = 0; i < roles.length; i++) {
            Role role = roles[i];
            if (!role.getIsOccupied() && role.getRank() <= rank) {
                validRoles[index] = role;
                index++;
            }
        }
        return validRoles;
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
        roleToTake = offCardR[0];
        isRoleOnCard = false;
        stage.close();
    }

    @FXML
    void offC2Clicked(ActionEvent event) {
        roleToTake = offCardR[1];
        isRoleOnCard = false;
        stage.close();
    }

    @FXML
    void offC3Clicked(ActionEvent event) {
        roleToTake = offCardR[2];
        isRoleOnCard = false;
        stage.close();
    }

    @FXML
    void offC4Clicked(ActionEvent event) {
        roleToTake = offCardR[3];
        isRoleOnCard = false;
        stage.close();
    }

    @FXML
    void onC1Clicked(ActionEvent event) {
        roleToTake = onCardR[0];
        isRoleOnCard = true;
        stage.close();
    }

    @FXML
    void onC2Clicked(ActionEvent event) {
        roleToTake = onCardR[1];
        isRoleOnCard = true;
        stage.close();
    }

    @FXML
    void onC3Clicked(ActionEvent event) {
        roleToTake = onCardR[2];
        isRoleOnCard = true;
        stage.close();
    }

}

