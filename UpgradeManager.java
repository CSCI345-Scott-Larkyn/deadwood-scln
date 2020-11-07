public class UpgradeManager {
    private int[] dollarCosts = new int[] {-1, -1, 4, 10, 18, 28, 40};
    private int[] creditCosts = new int[] {-1, -1, 5, 10, 15, 20, 25};
    
    public UpgradeData upgradeWithMoney(UpgradeData data, int upRank) {
        if (data.dollars < dollarCosts[upRank]) {
            return null;
        } else {
            data.dollars = data.dollars - dollarCosts[upRank];
            data.rank = upRank;
            return data;
        }
    }
    
    public UpgradeData upgradeWithCredits(UpgradeData data, int upRank) {
        if (data.credits < creditCosts[upRank]) {
            return null;
        } else {
            data.credits = data.credits - creditCosts[upRank];
            data.rank = upRank;
            return data;
        }
    }
    
    public int[] getMoneyCosts() {
        return dollarCosts;
    }
    
    public int[] getCreditCosts() {
        return creditCosts;
    }
}