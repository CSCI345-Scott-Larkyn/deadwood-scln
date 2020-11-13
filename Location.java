import java.util.*;
public class Location{ 

 	//Attributes:
 	private List<Player> visitingPlayers = new ArrayList<Player>();
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
    
 	public void createSet(int shotCt, Role[] offCardRoles) {
 		
 		set = new Set(shotCt, offCardRoles);
 		
 	}
    
    public void wrapScene() {
        set.payActors();
        reclaimPlayers();
    }
    
 	public List<Player> reclaimPlayers() {
        List<Player> returners = set.reclaimPlayers();
        for (Player p : returners) {
            visitingPlayers.add(p);
        }
        return returners;
 	}
    
    public Location[] getNeighbors() {
        return neighbors;
    }
    
    public void addPlayer(Player p) {
        visitingPlayers.add(p);
    }
    
    public void removePlayer(Player p) {
        visitingPlayers.remove(p);
    }
    
    public List<Player> getVisitingPlayers() {
        return visitingPlayers;
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