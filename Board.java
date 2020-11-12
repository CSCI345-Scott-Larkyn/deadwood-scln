////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood- SKELETON CODE
//
//	Larkyn- board, location, set, role, scene
//
////////////////////////////////////////////////////////////////////////////////


public class Board{
	
	//Attributes:
	private Location[] locations;
	private int scenesCompleted;
	private Card[] cardDeck;
	private static int dayNum = 0;


	//Constructor:
	public Board(Location[] locations, Card[] cardDeck){
		this.locations = locations;
		this.cardDeck = cardDeck;
		
		
	}

	//Methods:
	//cards are array list.. still have index. go through each location that has a set, get a random card and put it in
	// the location
	// put it in the location
	//then set that card in array list to null
	
	public void dealCards(){
		for(int i = 0; i <= 9; i++) {
			//get card from array list, set to null after
		}
	}
	public void setup(){
		if(dayNum == 0) {
			
		}
	}
	public void endOfDay() {
		
	}

}



 

 

 

