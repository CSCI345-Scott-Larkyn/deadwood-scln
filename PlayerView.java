public class PlayerView {
    private String[] actionList;
    private int[] upgradeList;
    
    public int promptForActionInRole() {
        return 1;
    }
    
    public int promptForActionNotInRole() {
        return 1;
    }
    
    public int[] promptUpgrade(int[] currentStats) {
        return null;
    }
    
    public Location promptForMove(Location[] locations) {
        return null;
    }
}