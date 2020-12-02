////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//deals with the user input and prompting for upgrading the player
package deadwood;

import java.util.*;
public class UpgradeViewText implements UpgradeView {
    private Scanner input = new Scanner(System.in);
    //what is called to upgrade a player
    //returns an UpgradeData of updated dollars, credits, and ranks upon successful upgrade
    //returns null upon unsuccessful upgrade
    public UpgradeData promptUpgrade(UpgradeData data) throws InputMismatchException {
        int dollars = data.dollars;
        int credits = data.credits;
        int rank = data.rank;
        if (rank >= 6) {
            System.out.println("You are already max rank");
            return null;
        }
        String dollarsString = (dollars == 1) ? "dollar" : "dollars";
        String creditsString = (credits == 1) ? "credit" : "credits";
        System.out.println("You have " + dollars + " " + dollarsString + " and " + credits + " " + creditsString);
        System.out.println("You are rank " + rank);
        
        UpgradeManager upgrader = new UpgradeManager();
        printUpgradePrices();
        int upRank = getIntendedRank(rank);
        if (upRank < 0) {
            return null;
        }
        
        UpgradeData newData;
        String paymentType = getPaymentType();
        if (paymentType.equals("m")) {
            newData = upgrader.upgradeWithMoney(data, upRank);
        } else {
            newData = upgrader.upgradeWithCredits(data, upRank);
        }
        if (newData == null) {
            System.out.println("You do not have sufficient funds");
        }
        return newData;
    }
    
    //returns the rank the player wishes to upgrade to
    private int getIntendedRank(int rank) {
        int upRank = -1;      
        System.out.println("The ranks you can upgrade to are: " + makePossibleRanksString(rank) + ", (0 to quit)");
        while (upRank < 0) {
            System.out.print("What rank would you like to upgrade to? ");
            try {
                upRank = input.nextInt();
                if (upRank == 0) {
                    return -1;
                } else if (upRank <= rank || upRank > 6) {
                    System.out.println("Please type a valid rank");
                    upRank = -1;
                }
            } catch (InputMismatchException ime) {
                input.nextLine();
                System.out.println("The rank needs to be a number");   
            }
        }
        return upRank;
    }
    
    //returns whether the player wants to upgrade with credits or dollars
    private String getPaymentType() {
        String paymentType = "";
        while (paymentType.equals("")) {
            System.out.print("Would you like to spend (m)oney or (c)redits? ");
            paymentType = input.next();
            if (!paymentType.equals("m") && !paymentType.equals("c")) {
                System.out.println("That is not a valid payment type");
                paymentType = "";
            }
        }
        return paymentType;    
    }
    
    //lists the available ranks to upgrade to
    private String makePossibleRanksString(int rank) {
        String possibleRanks = "6";
        for (int i = 5; i > rank; i--) {
            possibleRanks = i + " " + possibleRanks;
        }
        return possibleRanks;
    }
    
    private void printUpgradePrices() {
        System.out.println("Rank | Dollars | Credits");
        System.out.println(" 2   |    4    |   5    ");
        System.out.println(" 3   |    10   |   10   ");
        System.out.println(" 4   |    18   |   15   ");
        System.out.println(" 5   |    28   |   20   ");
        System.out.println(" 6   |    40   |   25   ");
    }
}