////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
import java.util.*;
//import javafx.scene.Scene;

public class Board{
	
	//Attributes:
	private Location[] locations;
	private int scenesCompleted;
	private List<Card> cards;
	private static int dayNum = 0;


	//Constructor:
	public Board(Location[] locations, List<Card> cards){
		this.locations = locations;
		this.cards = cards;
		
		
	}

	//Methods:	

    //void dealCards(): called by GameManager in setup method
    // 
    //  creates object random number for picking random card from deck  
    //     goes through locations and puts cards at each set (locations not including office and trailer)
    //     If there is not a card, one is added to the set and taken out of card list.
	public void dealCards(){
        Random randy = new Random();
		for(Location loc : locations) {
            if (loc.getSet() != null) {
                if (loc.getSet().getCard() != null) {
                    loc.reclaimPlayers();
                }
			    int index = randy.nextInt(cards.size());
                Card toAdd = cards.remove(index);
                loc.getSet().dealCard(toAdd);
            }
		}
	}


    //void endDay(): called by GameManager endDay method
    //
    //  calls deal cards() method above and moveAllToTrailers()
	public void endDay() {
		dealCards();
        moveAllToTrailers();
	}
    
    //getter for location array
    public Location[] getLocations() {
        return locations;
    }
    
    //void moveAllToTrailers(): called by endDay()
    //
    //  after players are gathered they are taken back to the trailer for the next day to begin
    //      this is done by going through each location that is not the trailer and gathering all players
    //      and adding them to the trailer location and removing them from the location that is not trailer
    private void moveAllToTrailers() {
        Location trailer = locations[10];
        for (Location loc : locations) {
            if (!loc.getName().equals("Trailer")) {
                List<Player> visitors = loc.getVisitingPlayers();
                for (int i = 0; i < visitors.size(); i++) {
                    trailer.addPlayer(visitors.get(i));
                    loc.removePlayer(visitors.get(i));
                }
            }    
        }
    }
}



 

 

 

