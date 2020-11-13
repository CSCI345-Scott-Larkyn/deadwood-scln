//Note: this class has a natural ordering that is inconsistent with equals 
public class Role implements Comparable<Role> {

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
    public Player removePlayer() {
        Player p = player;
        player = null;
        return p;
    }
    
    //a role is greater than another if it is of higher rank
    public int compareTo(Role r) {
        return rank - r.getRank();
    }
 }