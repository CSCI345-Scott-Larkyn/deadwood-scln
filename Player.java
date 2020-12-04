////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//contains all the data and methods that a player needs
//including methods for each possible action
//and processing flags from PlayerView on which action to take

import java.util.*;

public class Player {
    private String name;
    private int playerNum;
    private Location location;
    private int rank;
    private int practiceChips = 0;
    private int dollars = 0;
    private int credits;
    private boolean hasRole = false;
    private boolean isOnCard = false;
    private boolean hasMoved = false;
    private boolean canAct = false;
    private Random randy = new Random();
    private UpgradeManager upgrader = new UpgradeManager();
    
    public Player(String name, int playerNum, Location trailer) {
        this.name = name;
        rank = 1;
        credits = 0;
        this.playerNum = playerNum;
        location = trailer;
    }
    
    //in case of larger games with different starts
    public Player(String name, int credits, int rank, int playerNum, Location trailer) {
        this.name = name;
        this.rank = rank;
        this.credits = credits;
        this.playerNum = playerNum;
        location = trailer;
    }
    
    //what GameManager calls
    //keeps running as long as the player has viable actions
//    public void takeTurn() {
//        //playerView.printTurnStartStats(dollars, credits, rank, location.getName());
//        hasMoved = false;
//        canAct = hasRole;
//        String turnOptions = getTurnOptions();
//        while (!turnOptions.equals("")) {
//            boolean isDone = chooseAction(turnOptions);
//            if (isDone) {
//                turnOptions = "";
//            } else {
//                turnOptions = getTurnOptions();
//            }
//        }
//    }
    
    //returns a string of the initials of all allowed actions to be passed to PlayerView
    public String getTurnOptions() {
        String actions = "";
        if (isOKToTakeRole()) {
            actions = actions + "t"; //take role
        }
        if (location.getUpgradeOK()) {
            actions = actions + "u"; //upgrade
        }
        if (hasRole && canAct) {
            actions = actions + "a"; //act
        }
        if (hasRole && canAct && practiceChips + 1 < location.getSet().getCard().getBudget()) {
            actions = actions + "r"; //rehearse
        }
        if (!hasRole && !hasMoved) {
            actions = actions + "m"; //move
        }
        if (!canAct) {
            actions = actions + "e"; //end turn
        }
        return actions;
    }
    
    //gets an action from PlayerView and calls the appropriate method
    //returns isDone boolean, true if player chose to end the turn
//    public boolean chooseAction(String allowableActions) {
//        int budget = -1;
//        if (hasRole) {
//            budget = location.getSet().getCard().getBudget();
//        }
//        //String action = playerView.promptForAction(allowableActions, budget, practiceChips);
//        String action = "";
//        switch (action) {
//            case "t":  //take role
//                takeRole();
//                break;
//            case "u":  //upgrade
//                upgrade();
//                break;
//            case "a":  //act
//                act();
//                break;
//            case "r":  //rehearse
//                rehearse();
//                break;
//            case "m":  //move
//                move();
//                break;
//            case "e":  //end turn
//                return true;
//            default:
//                System.out.println("Invalid input for action");
//                break;
//        }
//        return false;
//    }
    
    //places player in the proper role in its location
    //and updates fields like hasRole and isOnCard accordingly
    //positive int for signedRole means on card, negative means off card
    public void takeRole(int signedRole) {
        Role[] offCardRoles = location.getSet().getOffCardRoles();
        Role[] onCardRoles = location.getSet().getCard().getRoles();
        //int signedRole = playerView.promptForRole(offCardRoles, onCardRoles, rank);
        if (signedRole < 0) {
            for (int index = 0; index < offCardRoles.length; index++) {
                if (!hasRole && !offCardRoles[index].getIsOccupied()) {
                    if (-signedRole == offCardRoles[index].getRank()) {
                        offCardRoles[index].addPlayer(this);
                        location.removePlayer(this);
                        hasRole = true;
                    }
                }
            }
        } else if (signedRole > 0) {
            for (int index = 0; index < onCardRoles.length; index++) {
                if (!hasRole && !onCardRoles[index].getIsOccupied()) {
                    if (signedRole == onCardRoles[index].getRank()) {
                        onCardRoles[index].addPlayer(this);
                        location.removePlayer(this);
                        hasRole = true;
                        isOnCard = true;
                    }
                }
            }
        }
        practiceChips = 0;
    }
    
    //calls on other classes for a new UpgradeData and upgrades financial fields accordingly
    public void upgrade(int intendedRank, boolean payWithDollars) {
        UpgradeData data = new UpgradeData(dollars, credits, rank);
        //UpgradeData newData = up.promptForUpgrade(data);
        UpgradeData newData;
        if (payWithDollars) {
            newData = upgrader.upgradeWithMoney(data, intendedRank);
        } else {
            newData = upgrader.upgradeWithCredits(data, intendedRank);
        }
        if (newData != null) {
            dollars = newData.dollars;
            credits = newData.credits;
            rank = newData.rank;
        }
    }
    
    //rolls a die and counts practice chips and updates financial info according to the results
    public void act() {
        if (location.getSet() == null) {
            //playerView.printActingResults(0);
        } else {
            int budget = location.getSet().getCard().getBudget();
            int roll = 1 + randy.nextInt(6);
            if (roll + practiceChips >= budget) {
                int shotsLeft = location.getSet().successfulShot();
                getPaid("success");
                //playerView.printActingResults(roll);
                if (shotsLeft == 0) {
                    location.wrapScene();
                    practiceChips = 0;
                }
            } else {
                getPaid("failure");
                //playerView.printActingResults((-roll));
            }
        }   
        canAct = false;     
    }
    
    //simple stuff
    public int rehearse() {
        practiceChips = practiceChips + 1;
        canAct = false;
        return practiceChips;
    }
    
    //returns a boolean telling whether the player actually moved
    //ensures players can only move once per turn
    //precondition: player is not in role
    public boolean move(Location destination) {
        if (hasRole) {
            System.out.println("You just moved while in a role.");
        }
        //Location destination = playerView.promptForMove(location.getNeighbors());
        if (destination == null) {
            return false;
        } else {
            location.removePlayer(this);
            location = destination;
            location.addPlayer(this);
            hasMoved = true;
            return true;
        }
    }
    
    public Location[] getNeighbors() {
        return location.getNeighbors();
    }
    
    private void getPaid(String result) {
        if (result.equals("success")) {
            if (isOnCard) {
                credits = credits + 2;
            } else {
                credits = credits + 1;
                dollars = dollars + 1;
            }
        } else {
            if (!isOnCard) {
                dollars = dollars + 1;
            }
        }
    }
    
    //returns true if the player is not currently in a role
    //and also are in a place with at least 1 free role
    private boolean isOKToTakeRole() {
        if (hasRole || location.getSet() == null || location.getSet().getCard() == null) {
            return false;
        }
        Role[] offCardRoles = location.getSet().getOffCardRoles();
        Role[] onCardRoles = location.getSet().getCard().getRoles();
        for (int index = 0; index < offCardRoles.length; index++) {
            if (!offCardRoles[index].getIsOccupied()) {
                return true;
            }
        }
        for (int index = 0; index < onCardRoles.length; index++) {
            if (!onCardRoles[index].getIsOccupied()) {
                return true;
            }
        }
        return false;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return dollars + credits + 5 * rank;
    }
    
    public int getPlayerNum() {
        return playerNum;
    }

    public int getRank() { return rank; }

    public int getPracticeChips() { return practiceChips; }

    public int getDollars() { return dollars; }

    public int getCredits() { return credits; }

    public Location getLocation() {
        return location;
    }
    
    public void addDollars(int income) {
        dollars = dollars + income;
    }
    
    //called when wrapping scene and reclaiming players
    public void takeOutOfRole() {
        hasRole = false;
        isOnCard = false;
    }
    
    public void moveToTrailers(Location trailers) {
        location = trailers;
    }
}