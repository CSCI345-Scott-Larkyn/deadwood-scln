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
 	public Player returnPlayer(){
 		return null;
 	}
    
 	public void payPlayer(int payment){

 	}
    
    public int getRank() {
        return rank;
    }
 }