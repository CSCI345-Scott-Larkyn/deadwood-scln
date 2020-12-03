import javafx.stage.Stage;

public class GameManagerFX {
    private GameViewController gameController;
    private UpgradeViewController upgradeController;
    private PlayerSelectController playerController;
    private TakeRoleController roleController;
    private Stage primaryStage;

    public GameManagerFX(GameViewController gCon, UpgradeViewController uCon, PlayerSelectController pCon, TakeRoleController rCon, Stage primaryStage) {
        gameController = gCon;
        upgradeController = uCon;
        playerController = pCon;
        roleController = rCon;
        this.primaryStage = primaryStage;
    }

    public void playGame() {
        
    }
}
