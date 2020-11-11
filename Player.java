import java.util.*;

public class Player {
    private String name;
    private Location location;
    private int rank;
    private int practiceChips = 0;
    private int dollars = 0;
    private int credits;
    private boolean hasRole = false;
    private boolean isOnCard = false;
    private boolean hasMoved = false;
    private boolean canAct = false;
    private PlayerView playerView;
    private Random randy = new Random();
    
    public Player(String name, PlayerView playerView) {
        this.name = name;
        this.playerView = playerView;
        rank = 1;
        credits = 0;
    }
    
    //in case of larger games with different starts
    public Player(String name, PlayerView playerView, int credits, int rank) {
        this.name = name;
        this.playerView = playerView;
        this.rank = rank;
        this.credits = credits;
    }
    
    public void takeTurn() {
        hasMoved = false;
        canAct = hasRole;
        String turnOptions = getTurnOptions();
        while (!turnOptions.equals("")) {
            boolean isDone = chooseAction(turnOptions);
            if (isDone) {
                turnOptions = "";
            } else {
                turnOptions = getTurnOptions();
            }
        }
    }
    
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
        if (!hasRole) {
            actions = actions + "e"; //end turn
        }
        return actions;
    }
    
    //returns isDone boolean
    public boolean chooseAction(String allowableActions) {
        String action = playerView.promptForAction(allowableActions);
        if (action.equals("t")) { //take role
            takeRole();
        } else if (action.equals("u")) { //upgrade
            upgrade();
        } else if (action.equals("a")) { //act
            act();
        } else if (action.equals("r")) { //rehearse
            rehearse();
        } else if (action.equals("m")) { //move
            move();
        } else if (action.equals("e")) { //end turn
            return true;
        } else {
            System.out.println("Invalid input for action");
        }
        return false;
    }
    
    //positive int means on card, negative means off card
    //kinda relies on a lot of things going right/other checks working
    public void takeRole() {
        Role[] offCardRoles = location.getSet().getOffCardRoles();
        Role[] onCardRoles = location.getSet().getCard().getRoles();
        int signedRole = playerView.promptForRole(offCardRoles, onCardRoles, rank);
        if (signedRole < 0) {
            for (int index = 0; index < offCardRoles.length; index++) {
                if (!hasRole && !offCardRoles[index].getIsOccupied()) {
                    if (-signedRole == offCardRoles[index].getRank()) {
                        offCardRoles[index].addPlayer(this);
                        hasRole = true;
                    }
                }
            }
        } else if (signedRole > 0) {
            for (int index = 0; index < onCardRoles.length; index++) {
                if (!hasRole && !onCardRoles[index].getIsOccupied()) {
                    if (signedRole == onCardRoles[index].getRank()) {
                        onCardRoles[index].addPlayer(this);
                        hasRole = true;
                        isOnCard = true;
                    }
                }
            }
        } else {
            System.out.println("No role take for none were free");
        }
        
        if (!hasRole) {
            System.out.println("Didn't take a role when should have");
        }
    }
    
    public void upgrade() {
        UpgradeData data = new UpgradeData(dollars, credits, rank);
        UpgradeData newData = playerView.promptForUpgrade(data);
        dollars = newData.dollars;
        credits = newData.credits;
        rank = newData.rank;
    }
    
    public void act() {
        if (location.getSet() == null) {
            playerView.printActingResults(0);
        } else {
            int budget = location.getSet().getCard().getBudget();
            int roll = 1 + randy.nextInt(5);
            if (roll + practiceChips >= budget) {
                int shotsLeft = location.getSet().successfulShot();
                getPaid("success");
                playerView.printActingResults(roll);
                if (shotsLeft == 0) {
                    //gotta wrap scene and pay everyone
                    //and reset practice chips
                }
            } else {
                getPaid("failure");
                playerView.printActingResults((-roll));
            }
        }   
        canAct = false;     
    }
    
    public int rehearse() {
        practiceChips = practiceChips + 1;
        canAct = false;
        return practiceChips;
    }
    
    //returns a boolean telling whether the player actually moved
    public boolean move() {
        if (hasRole == true) {
            System.out.println("You just moved while in a role.");
        }
        Location destination = playerView.promptForMove(location.getNeighbors());
        if (destination == null) {
            return false;
        } else {
            location = destination;
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
                credits = credits +2;
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
}