////////////////////////////////////////////////////////////////
//  Larkyn & Scott
//      Deadwood
//
////////////////////////////////////////////////////////////////
package deadwood;

public class Card{

 	//Attributes:
 	private Role[] onCardRoles;
 	private int budget;
 	private boolean isFaceUp = false;
 	private CardGUI cardGUI;

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

    public boolean isFaceUp() { return isFaceUp; }

    public void addCardGUI(CardGUI c) {
 	    cardGUI = c;
    }

    public CardGUI getCardGUI() {
 	    return cardGUI;
    }
 }