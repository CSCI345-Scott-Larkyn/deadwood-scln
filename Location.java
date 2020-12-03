////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////

import java.util.*;
public class Location{ 

 	//Attributes:
 	private List<Player> visitingPlayers = new ArrayList<Player>();
 	private Location[] neighbors;
 	private Set set;
 	private boolean upgradeOK;
 	private String name;
 	private boolean hasBeenVisited;
 	private VisitorsGUI visitorsGUI;


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

    //void createSet(int, Role[]): called by file reader
    //  creates a set object for each location that is not trailer or 
    //  office including the roles and shot count
 	public void createSet(int shotCt, Role[] offCardRoles) {
 		
 		set = new Set(shotCt, offCardRoles);
 		
 	}
    
    //void wrapScene(): Calls payActors method in current set.
    //  calls reclaim players in current class

    public void wrapScene() {
        set.payActors();
        reclaimPlayers();
    }
    
    //List<Player> reclaimPlayers(): 
    //  puts the players that just wrapped a scene into a list of "visitors" that will be
    //      able to move out of current location on their next turn.
 	public List<Player> reclaimPlayers() {
        List<Player> returners = set.reclaimPlayers();
        for (Player p : returners) {
            visitingPlayers.add(p);
        }
        return returners;
 	}
    

    //Getter for neighbors of location; returns Location[]
    public Location[] getNeighbors() {
        return neighbors;
    }

    //void addPlayer(Player p)
    //  add a player to visiting player list
    public void addPlayer(Player p) {
        visitingPlayers.add(p);
        if (!hasBeenVisited) {
            if (set != null && set.getCard() != null) {
                set.getCard().flip();
            }
            hasBeenVisited = true;
        }

    }
    
    //void removePlayer(Player p)
    // remove a player from visiting player list
    public void removePlayer(Player p) {
        visitingPlayers.remove(p);
    }
    
    //getter for the visiting player list
    public List<Player> getVisitingPlayers() {
        return visitingPlayers;
    }
    
    //getter for the current set 
    public Set getSet() {
        return set;
    }
    
    //boolean getUpgradeOK():
    //  flag to see if the current location is the casting office
    public boolean getUpgradeOK() {
        return upgradeOK;
    }
    
    //getter for name of current location
    public String getName() {
        return name;
    }
    
    //setter for set name for weird occurrence with office from file reader
    public void setName(String name) {
        this.name = name;
    }

    public void resetVisitation() {
 	    hasBeenVisited = false;
    }

    public void addVisitorsGUI(VisitorsGUI vis){
 	    visitorsGUI = vis;
    }

    public VisitorsGUI getVisitorsGUI() {
 	    return visitorsGUI;
    }
 }