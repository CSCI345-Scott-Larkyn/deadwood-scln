////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//deals with all the printing to console and user input
//associated with a player taking their turn
package deadwood;

import java.util.*;

public class PlayerViewText implements PlayerView {
    private Scanner input = new Scanner(System.in);
    private UpgradeViewText upgradeView = new UpgradeViewText();
    
    public UpgradeData promptForUpgrade(UpgradeData data) {
        return upgradeView.promptUpgrade(data);
    }
    
    //takes an array of all the player's neighboring locations
    //and returns one of them that the player chooses
    //or null if they end up not moving
    public Location promptForMove(Location[] neighbors) {
        System.out.println("Which of these locations would you like to go to: ");
        for (Location loc : neighbors) {
            System.out.print("(" + loc.getName() + ") ");
        }
        System.out.print(": ");
        String destination = "";
        input.nextLine();
        while (destination.equals("")) {
            destination = input.nextLine().toLowerCase().trim();
            if (destination.equals("q")) {
                return null;
            }
            for (Location loc : neighbors) {
                if (destination.equals(loc.getName().toLowerCase())) {
                    return loc;
                }
            }
            System.out.print("Please type one of the locations or (q)uit ");
            destination = "";
        }
        return null;
    }
    
    //takes a string of the initials of all the allowable actions
    //and returns a string of length 1 with one of the actions
    //or "e" for ending the player's turn
    public String promptForAction(String options, int budget, int practiceChips) {
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
        if (options.contains("a")) {
            String chipString = "chip" + (practiceChips == 1 ? "" : "s");
            System.out.println("You have " + practiceChips + " " + chipString + " and the budget is " + budget); 
        }
              
        String action = "";
        while (action.equals("")) {
            System.out.print("What action would you like to take? ");
            action = input.next();
            if (options.contains(action) && action.length() == 1) {
            } else {
                System.out.println("Please type a valid action");
                action = "";
            }
        }          
        return action;
    }
    
    //prints all the available roles that the player can take in their location
    //and does not allow them to take a role above their rank
    //returns 0 if no role was taken
    //returns the rank of the role taken if one was taken
    //it will be a positive number if that role was on-card
    //and negative if off-card
    public int promptForRole(Role[] offCardRoles, Role[] onCardRoles, int playerRank) {
        System.out.print("The ranks of the available on-card roles: ");
        String onCardOptions = "";
        for (Role onCardRole : onCardRoles) {
            if (!onCardRole.getIsOccupied()) {
                String rank = onCardRole.getRank() + "";
                System.out.print(rank + " ");
                onCardOptions = onCardOptions + rank;
            }
        }
        System.out.println();
        
        System.out.print("The ranks of the available off-card roles: ");
        String offCardOptions = "";
        for (Role offCardRole : offCardRoles) {
            if (!offCardRole.getIsOccupied()) {
                String rank = offCardRole.getRank() + "";
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
            if (onOrOff.equals("q")) {
                return 0;
            }
            if (!onOrOff.equals("n") && !onOrOff.equals("f")) {
                System.out.println("Please type 'n' or 'f' for an on- or off-card role, (q)uit");
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
            System.out.print("What rank of role would you like to take? (q)uit ");
            rank = input.next();
            if (rank.equals("q")) {
                return 0;
            } else {
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
                    rank = "";
                } 
            }    
        }
        if (isOnCard) {
            return Integer.parseInt(rank);
        } else {
            return -Integer.parseInt(rank);
        }
    }
    
    //lets player know how acting went
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
    
    //prints helpful information for a player to know at the start of their turn
    public void printTurnStartStats(int dollars, int credits, int rank, String locationName) {
        String dollarString = "dollar" + (dollars == 1 ? "" : "s");
        String creditString = "credit" + (credits == 1 ? "" : "s");
        System.out.println("You have " + dollars + " " + dollarString + " and " + credits + " " + creditString); 
        System.out.println("You are rank " + rank + " and you are in the " + locationName);
    }
}