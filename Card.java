////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////

public class Card{

 	//Attributes:
 	private Role[] onCardRoles;
 	private int budget;
 	private boolean isFaceUp = false;

 	//Constructor:
 	public Card(int budget, Role[] onCardRoles){
        this.budget = budget;
        this.onCardRoles = onCardRoles;
 	}

 	//Methods:
 	
 	//getter for budget
    public int getBudget() {
        return budget;
    }
    
    //getter for onCard roles
    public Role[] getRoles() {
        return onCardRoles;
    }

    public void flip() {
 	    isFaceUp = true;
    }
 }