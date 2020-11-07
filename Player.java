import java.util.*;

public class Player {
    private String name;
    private Location location;
    private int rank;
    private int practiceChips;
    private int dollars;
    private int credits;
    private boolean hasRole;
    private boolean isOnCard;
    private PlayerView playerView;
    private Random randy;
    
    public Player(String name, PlayerView playerView) {
        this.name = name;
        this.playerView = playerView;
        rank = 1;
        practiceChips = 0;
        dollars = 0;
        credits = 0;
        hasRole = false;
        isOnCard = false;
        randy = new Random();
    }
    
    //in case of larger games with different starts
    public Player(String name, PlayerView playerView, int credits, int rank) {
        this.name = name;
        this.playerView = playerView;
        this.rank = rank;
        dollars = 0;
        this.credits = credits;
        hasRole = false;
        isOnCard = false;
        randy = new Random();
    }
    
    public void takeTurn() {
        String turnOptions = getTurnOptions();
        while (!turnOptions.equals("")) {
            if (chooseAction(turnOptions)) {
                turnOptions = "";
            } else {
                turnOptions = getTurnOptions();
            }
        }
    }
    
    public String getTurnOptions() {
        return "";
    }
    
    //returns isDone boolean
    public boolean chooseAction(String allowableActions) {
        String action = playerView.promptForAction();
        if (action.equals("t")) {
            takeRole();
        } else if (action.equals("u")) {
            upgrade();
        } else if (action.equals("a")) {
            act();
        } else if (action.equals("r")) {
            rehearse();
        } else if (action.equals("m")) {
            move();
        } else if (action.equals("d")) {
            return true;
        } else {
            System.out.println("Invalid input for action");
        }
        return false;
    }
    
    public void takeRole() {
    
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
    }
    
    public int rehearse() {
        practiceChips = practiceChips + 1;
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
}