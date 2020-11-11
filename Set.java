public class Set{

 	//Attributes:
 	private int shotCount;
 	private Role[] offCardRoles;
 	private Card card;

 	//Constructor:
 	public Set(int shotCount, Role[] offCardRoles){
 		this.shotCount = shotCount;
 		this.offCardRoles = offCardRoles;

 	}
 	

 	//Methods:
 	public void assignRole(Player curPlayer, int roleRank){

 	}
     
 	public void dealCard(Card curCard){

 	}
    
 	public Player[] wrapScene(){
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
    
 	private void payActors(){
        
 	}
    
 	private Player[] gatherPlayers(){
 		return null;
 	}
 }