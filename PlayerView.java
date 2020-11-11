import java.util.*;

public class PlayerView {
    private Scanner input = new Scanner(System.in);
    private UpgradeView upgradeView = new UpgradeView();
    
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
    
    public String promptForAction(String options) {
        String possibleActions = "";
        if (options.contains("t"))
            possibleActions = possibleActions + "(t)ake role ";
        if (options.contains("u"))
            possibleActions = possibleActions + "(u)pgrade ";        
        if (options.contains("a"))
            possibleActions = possibleActions + "(a)ct ";
        if (options.contains("r"))
            possibleActions = possibleActions + "(r)ehearse ";
        if (options.contains("m"))
            possibleActions = possibleActions + "(m)ove ";
        if (options.contains("e"))
            possibleActions = possibleActions + "(e)nd turn ";
        System.out.println("Your possible actions are: " + possibleActions);   
              
        String action = "";
        while (action.equals("")) {
            System.out.print("What move would you like to make? ");
            action = input.next();
            if (options.contains(action) && action.length() == 1) {
            } else {
                System.out.println("Please type a valid action");
                action = "";
            }
        }          
        return action;
    }
    
    public int promptForRole(Role[] offCardRoles, Role[] onCardRoles, int playerRank) {
        System.out.print("The ranks of the available on-card roles: ");
        String onCardOptions = "";
        for (int indOn = 0; indOn < onCardRoles.length; indOn++) {
            if (!onCardRoles[indOn].getIsOccupied()) {
                String rank = onCardRoles[indOn].getRank() + "";
                System.out.print(rank + " ");
                onCardOptions = onCardOptions + rank;
            }
        }
        System.out.println();
        
        System.out.print("The ranks of the available off-card roles: ");
        String offCardOptions = "";
        for (int offInd = 0; offInd < offCardRoles.length; offInd++) {
            if (!offCardRoles[offInd].getIsOccupied()) {
                String rank = offCardRoles[offInd].getRank() + "";
                System.out.print(rank + " ");
                offCardOptions = offCardOptions + rank;
            }
        }
        System.out.println();
        
        if (offCardOptions.equals("") && onCardOptions.equals("")) {
            System.out.println("There are no available roles here");
            return 0;
        }
        
        String onOrOff = "";
        while (onOrOff.equals("")) {
            System.out.print("Would you like an o(n)-card role or of(f)-card role? ");
            onOrOff = input.next();
            if (!onOrOff.equals("n") || !onOrOff.equals("f")) {
                System.out.println("Please type 'n' or 'f' for an on- or off-card role");
                onOrOff = "";
            }
            if (onOrOff.equals("n") && onCardOptions.equals("")) {
                System.out.println("Sorry, there are no on-card roles");
                onOrOff = "";
            }
            if (onOrOff.equals("f") && offCardOptions.equals("")) {
                System.out.println("Sorry, there are no off-card roles");
                onOrOff = "";
            }
        }
        boolean isOnCard = onOrOff.equals("n");
        
        System.out.println("You are rank " + playerRank);
        String rank = "";
        while (rank.equals("")) {
            System.out.print("What rank of role would you like to take? ");
            rank = input.next();
            try {
                int rankNum = Integer.parseInt(rank);
                if (rankNum > playerRank) {
                    System.out.println("You are not a high enough rank");
                    rank = "";
                } else {
                    if (isOnCard && (!onCardOptions.contains(rank) || rank.length() != 1)) {
                        System.out.println("There are no on-card roles with that rank");
                        rank = "";
                    } else if (!isOnCard && (!offCardOptions.contains(rank) || rank.length() != 1)) {
                        System.out.println("There are no off-card roles with that rank");
                        rank = "";
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please type a number");
            } 
        }
        if (isOnCard) {
            return Integer.parseInt(rank);
        } else {
            return -Integer.parseInt(rank);
        }
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