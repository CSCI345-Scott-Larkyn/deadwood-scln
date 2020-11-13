import java.util.*;

public class Set{

 	//Attributes:
 	private int setShotCount;
    private int currentShotCount;
 	private Role[] offCardRoles;
 	private Card card;
 	//private Role[] onCardRoles;
 	private Random randy = new Random();

 	//Constructor:
 	public Set(int shotCount, Role[] offCardRoles){
 		setShotCount = shotCount;
        currentShotCount = shotCount;
 		this.offCardRoles = offCardRoles;

 	}
 	

 	//Methods:
 	 
 	public void dealCard(Card card){
 		this.card = card;
 	}
    
    public Card getCard() {
        return card;
    }
    
    public int successfulShot() {
        currentShotCount = currentShotCount - 1;
        return currentShotCount;
    }
    
    public Role[] getOffCardRoles() {
        return offCardRoles;
    }
    
    public int getCurrentShotCount() {
        return currentShotCount;
    }
    
    //location calls this one then location calls reclaim
 	public void payActors() {
 		int numDie = card.getBudget();
 		int[] diceRolls = new int[numDie];
 		for(int i = 0; i < numDie; i++) {
 			int roll = 1 + randy.nextInt(5);
 			diceRolls[i] = roll;
 		}
 		Arrays.sort(diceRolls);	//sorted from smallest to largest       
        Role[] onCardRoles = sortOnCardRoles();
        reverseArrays(diceRolls, onCardRoles);
        
        int numOnCard = onCardRoles.length;
 	    if (numOnCard > 0) {
 		    for(int die = 0; die < numDie; die++) {
 	            onCardRoles[die % numOnCard].getPlayer().addDollars(diceRolls[die]);
 		    }
            for (int off = 0; off < offCardRoles.length; off++) {
                if (offCardRoles[off].getPlayer() != null) {
                    offCardRoles[off].getPlayer().addDollars(offCardRoles[off].getRank());
                }
            }
        }   
        currentShotCount = setShotCount;  
 	}
    
    private Role[] sortOnCardRoles() {
        int numActors = 0;
        for (Role r : card.getRoles()) {
            if (r.getPlayer() != null) {
                numActors++;
            }
        }
        Role[] onCardRoles = new Role[numActors];
        int index = 0;
        for (Role r : card.getRoles()) {
            if (r.getPlayer() != null) {
                onCardRoles[index] = r;
                index++;
            }
        }
 		Arrays.sort(onCardRoles);
        return onCardRoles;
    }
    
    private void reverseArrays(int[] diceRolls, Role[] onCardRoles) {
        int diceLength = diceRolls.length;
        int rolesLength = onCardRoles.length;
        int[] newRolls = new int[diceLength];
        Role[] newOnCards = new Role[rolesLength];
        for (int i = 0; i < diceLength; i++) {
            newRolls[i] = diceRolls[diceLength - i - 1];
        }
        for (int i = 0; i < rolesLength; i++) {
            newOnCards[i] = onCardRoles[rolesLength - i - 1];
        }
        diceRolls = newRolls;
        onCardRoles = newOnCards;
    }
    
 	public List<Player> reclaimPlayers() {
 		List<Player> returners = new ArrayList<Player>();
        for (int ind = 0; ind < offCardRoles.length; ind++) {
            Player beingMoved = offCardRoles[ind].removePlayer();
            if (beingMoved != null) {
                beingMoved.takeOutOfRole();
                returners.add(beingMoved);
            }    
        }
        Role[] onCardRoles = card.getRoles();
        for (int ind = 0; ind < onCardRoles.length; ind++) {
            Player beingMoved = onCardRoles[ind].removePlayer();
            if (beingMoved != null) {
                beingMoved.takeOutOfRole();
                returners.add(beingMoved);
            }
        }
        card = null;
        return returners;
 	}
 }