////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////


//Note: this class has a natural ordering that is inconsistent with equals 
public class Role implements Comparable<Role> {

 	//Attributes:
 	private int rank;
 	private Player player;
 	private boolean isOccupied;
 	private PixelData pixelData;

 	//Constructor:
 	public Role(int rank, PixelData pixelData){
 	    this.pixelData = pixelData;
 		this.rank = rank;
        isOccupied = false;
 	}

 	//Methods:
 	
    //void addPlayer(Player):
    //  puts player in spot, changes this spot flag to occupied
    public void addPlayer(Player player) {
        this.player = player;
        isOccupied = true;
    }
    
    //getter for rank
    public int getRank() {
        return rank;
    }
    
    //getter for checking if space is occupied
    public boolean getIsOccupied() {
        return isOccupied;
    }

    //getter for current player
    public Player getPlayer() {
    	return player;
    }

    //Player removePlayer():
    //      removes player from role
    public Player removePlayer() {
        Player p = player;
        player = null;
        isOccupied = false;
        return p;
    }
    
    //int compareTo(Role):
    //  returns a negative number if comparing rank is larger, returns positive number if role is greater
    //a role is greater than another if it is of higher rank
    public int compareTo(Role r) {
        return rank - r.getRank();
    }

    public PixelData getPixelData() {
 	    return pixelData;
    }
 }