public class Role{

 	//Attributes:
 	private int rank;
 	private Player player;
 	private boolean isOccupied;

 	//Constructor:
 	public Role(int rank){
 		this.rank = rank;
        isOccupied = false;
 	}

 	//Methods:
 	
    public void addPlayer(Player player) {
        this.player = player;
        isOccupied = true;
    }
    
    public int getRank() {
        return rank;
    }
    
    public boolean getIsOccupied() {
        return isOccupied;
    }
    public Player getPlayer() {
    	return player;
    }
 }