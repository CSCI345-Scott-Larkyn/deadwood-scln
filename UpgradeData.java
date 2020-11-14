////////////////////////////////////////////////////////////////////////////////
// 	Larkyn & Scott
//		Deadwood
//
////////////////////////////////////////////////////////////////
//this class is just plain old data
//simply a convenient way of transferring a player's financial info
public class UpgradeData {
    public int dollars;
    public int credits;
    public int rank;
    
    public UpgradeData(int dollars, int credits, int rank) {
        this.dollars = dollars;
        this.credits = credits;
        this.rank = rank;
    }
}