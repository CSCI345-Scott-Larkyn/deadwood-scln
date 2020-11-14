////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////

public class Card{

 	//Attributes:
 	private Role[] onCardRoles;
 	private int budget;

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
    
    //getter for oncard roles
    public Role[] getRoles() {
        return onCardRoles;
    }
 }