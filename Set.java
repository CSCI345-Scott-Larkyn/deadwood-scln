////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////

import java.util.*;

public class Set{

 	//Attributes:
 	private int setShotCount;
    private int currentShotCount;
 	private Role[] offCardRoles;
 	private Card card;
 	private Random randy = new Random();

 	//Constructor:
 	public Set(int shotCount, Role[] offCardRoles){
 		setShotCount = shotCount;
        currentShotCount = shotCount;
 		this.offCardRoles = offCardRoles;

 	}
 	

 	//Methods:
 	
    //void dealCard(Card card):
    //  sets card in play 
 	public void dealCard(Card card){
 		this.card = card;
 	}
    
    //Card getCard():
    //  getter for card
    public Card getCard() {
        return card;
    }
    
    // int successfulShot():
    //  reduces current shot count if a scene is acted successfully
    //  returns new shot count
    public int successfulShot() {
        currentShotCount = currentShotCount - 1;
        return currentShotCount;
    }
    
    //getter for off card roles
    public Role[] getOffCardRoles() {
        return offCardRoles;
    }
    
    //getter for current shot count
    public int getCurrentShotCount() {
        return currentShotCount;
    }

    public int getSetShotCount() { return setShotCount; }
    
    //void payActors():
    //  location calls this method after a scene is wrapped
    //  then location calls reclaimPlayers
    //  
    //  random number generator is used to create dice,
    //      number of dice is dependent on budget. 
    //      these are stored in an array of type int then sorted
    //      role ranks are compared then sorted and then reversed so they
    //      go from largest to smallest, then according to dice, players are paid
    //      shot counter is adjusted
 	public void payActors() {
 		int numDie = card.getBudget();
 		int[] diceRolls = new int[numDie];
 		for(int i = 0; i < numDie; i++) {
 			int roll = 1 + randy.nextInt(6);
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
    
    //Role[] sortOnCardRoles():
    //      sorts onCardRoles according to rank of the roles to be compared to the dice
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
    
    //void reverseArrays(int[], Role[]):
    //  reverse the arrays for dice and card roles so that when
    //      the dice are distributed, they go to the correct recipient.
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
    
    //List<Player> reclaimPlayers():
    //  takes all players off of their roles and adds to a list of people being moved
    //      card is set to null since the scene is finished
    //   list of players to be sent back trailer is returned
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
        card.getCardGUI().update(false);
        card = null;
        return returners;
 	}
 }