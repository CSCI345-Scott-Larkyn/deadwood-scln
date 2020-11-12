import java.util.*;

public class Set{

 	//Attributes:
 	private int shotCount;
 	private Role[] offCardRoles;
 	private Card card;
 	private Role[] onCardRoles;
 	private Player curPlayer;
 	private Random randy = new Random();

 	//Constructor:
 	public Set(int shotCount, Role[] offCardRoles){
 		this.shotCount = shotCount;
 		this.offCardRoles = offCardRoles;

 	}
 	

 	//Methods:
 	 
 	public void dealCard(Card curCard){
 		//location call set.dealcard to set card to be whatever it receives... setter
 	}
    
 	public Player[] wrapScene(){
 		Player[] playersOnSet;
 		if(onCardRoles.length > 0) {
 			
 		}else {
 			
 		}
 		//do this one!
 		//pay everyone.. on card and off card
 		//return them to whoever calls wrapScene
 		return null;
 	}
    
    public Card getCard() {
        return card;
    }
    
    public int successfulShot() {
        shotCount = shotCount - 1;
        return shotCount;
    }
    
    public Role[] getOffCardRoles() {
        return offCardRoles;
    }
    
    //location calls this one then location calls wrap
 	private void payActors(){
 		int numDie = card.getBudget();
 		int[] diceToSort = new int[numDie];
 		for(int i = 1; i <= numDie; i++) {
 			int roll = 1 + randy.nextInt(5);
 			diceToSort[i-1] = roll;
 		}
 		Arrays.sort(diceToSort);	//sorted from smallest to largest
 		//need to go through ranks of on card and sort them
 		int[] onCardRank = new int[onCardRoles.length];
 		int[] index = new int[onCardRoles.length];
 		for(int j = 0; j < onCardRoles.length; j++) {
 			onCardRank[j] = onCardRoles[j].getRank();
		}
 		
 	
 		for(int k = 0; k < numDie; k++) {
 			
 		}
 		
 		
        
 	}
    
 	private Player[] gatherPlayers(){
 		return null;
 	}
 }