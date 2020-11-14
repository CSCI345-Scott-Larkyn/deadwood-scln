////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
import java.util.*;

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

	public void endDay() {
		dealCards();
        moveAllToTrailers();
	}
    
    public Location[] getLocations() {
        return locations;
    }
    
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



 

 

 

