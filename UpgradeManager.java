public class UpgradeManager {
    private int[] moneyCosts = new int[] {-1, -1, 4, 10, 18, 28, 40};
    private int[] creditCosts = new int[] {-1, -1, 5, 10, 15, 20, 25};
    
    public UpgradeData upgradeWithMoney(UpgradeData data, int upRank) {
        if (data.money < moneyCosts[upRank]) {
            return null;
        } else {
            data.money = data.money - moneyCosts[upRank];
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
        return moneyCosts;
    }
    
    public int[] getCreditCosts() {
        return creditCosts;
    }
}