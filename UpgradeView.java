import java.util.*;
public class UpgradeView {
    public UpgradeData promptUpgrade(UpgradeData data, Scanner input) throws InputMismatchException {
        int money = data.money;
        int credits = data.credits;
        int rank = data.rank;
        if (rank >= 6) {
            System.out.println("You are already max rank");
            return null;
        }
        String moneyString = (money == 1) ? "dollar" : "dollars";
        String creditsString = (credits == 1) ? "credit" : "credits";
        System.out.println("You have " + money + " " + moneyString + " and " + credits + " " + creditsString);
        System.out.println("You are rank " + rank);
        
        UpgradeManager upgrader = new UpgradeManager();
        printUpgradePrices();
        int upRank = getIntendedRank(rank, input);
        if (upRank < 0) {
            return null;
        }
        
        UpgradeData newData;
        String paymentType = getPaymentType(input);
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
    
    private int getIntendedRank(int rank, Scanner input) {
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
    
    private String getPaymentType(Scanner input) {
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