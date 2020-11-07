public class Location{ 

 	//Attributes:
 	private Player[] visitingPlayers;
 	private Location[] neighbors;
 	private Set set;
 	private boolean upgradeOK;
 	private String name;


 	//Constructor:
 	public Location(Location[] neighbors, boolean upgradeOK, String name){
 		this.neighbors = neighbors;
 		this.upgradeOK = upgradeOK;
 		this.name = name;
 	}
 	public Location(String name) {
 		this.name = name;
 	}

 	//Methods:
 	public void assignRole(Player curPlayer, int roleRank){

 	}
 	public void reclaimPlayers(){

 	}
    
    public Location[] getNeighbors() {
        return neighbors;
    }
    
    public Set getSet() {
        return set;
    }
    
    public boolean getUpgradeOK() {
        return upgradeOK;
    }
    
    public String getName() {
        return name;
    }
 }