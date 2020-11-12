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
 	
    public int getBudget() {
        return budget;
    }
    
    public Role[] getRoles() {
        return onCardRoles;
    }
 }