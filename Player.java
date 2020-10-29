public class Player {
    private String name;
    private Location location;
    private int rank;
    private int practiceChips;
    private int money;
    private int credits;
    private boolean hasRole;
    private boolean isOnCard;
    private PlayerView playerView;
    
    public Player(String name, PlayerView playerView) {
        this.name = name;
        this.playerView = playerView;
        rank = 1;
        practiceChips = 0;
        money = 0;
        credits = 0;
        hasRole = false;
        isOnCard = false;
    }
    
    //in case of larger games with different starts
    public Player(String name, PlayerView playerView, int credits, int rank) {
        this.name = name;
        this.playerView = playerView;
        this.rank = rank;
        money = 0;
        this.credits = credits;
        hasRole = false;
        isOnCard = false;
    }
    
    public void takeTurn() {
    
    }
}