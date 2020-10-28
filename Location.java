public class Location{ 

 	//Attributes:
 	private Player[] visitingPlayers;
 	private Location[] neighbors;
 	private Set set;
 	private boolean upgradeOK;
 	private String name;


 	//Constructor:
 	public Location(Location[] neighbors, boolean upgradeOK){
 		this.neighbors = neighbors;
 		this.upgradeOK = upgradeOK;
 	}

 	//Methods:
 	public void assignRole(Player curPlayer, int roleRank){

 	}
 	public void reclaimPlayers(){

 	}
 }