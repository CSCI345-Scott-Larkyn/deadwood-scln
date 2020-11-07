import java.util.*;

public class PlayerView {
    private String[] actionList;
    private Scanner input = new Scanner(System.in);
    private UpgradeView upgradeView = new UpgradeView();
    
    public int promptForActionInRole() {
        return 1;
    }
    
    public int promptForActionNotInRole() {
        return 1;
    }
    
    public UpgradeData promptForUpgrade(UpgradeData data) {
        return upgradeView.promptUpgrade(data, input);
    }
    
    public Location promptForMove(Location[] neighbors) {
        System.out.print("Which of these locations would you like to go to: ");
        for (Location loc : neighbors) {
            System.out.print(loc.getName() + " ");
        }
        System.out.println();
        String destination = "";
        while (destination.equals("")) {
            destination = input.next().trim().toLowerCase();
            if (destination.equals("q")) {
                return null;
            }
            for (Location loc : neighbors) {
                if (destination.equals(loc.getName())) {
                    return loc;
                }
            }
            System.out.print("Please type one of the locations or q to quit");
            destination = "";
        }
        return null;
    }
    
    public String promptForAction() {
        return "";
    }
    
    public void printActingResults(int roll) {
        if (roll == 0) {
            System.out.println("You cannot act in your location");
        } else {
            System.out.print("You rolled a " + Math.abs(roll));
            if (roll > 0) {
                System.out.println(", it was a success!");
            } else {
                System.out.println(", you did not complete the shot.");
            }
        }    
    }
}