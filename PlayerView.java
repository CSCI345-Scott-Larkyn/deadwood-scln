import java.util.*;

public class PlayerView {
    private String[] actionList;
    private Scanner input = new Scanner(System.in);
    
    public int promptForActionInRole() {
        return 1;
    }
    
    public int promptForActionNotInRole() {
        return 1;
    }
    
    public UpgradeData promptForUpgrade(UpgradeData data) {
        return data;
    }
    
    public Location promptForMove(Location[] locations) {
        return null;
    }
    
    public String promptForAction() {
        return "";
    }
    
}