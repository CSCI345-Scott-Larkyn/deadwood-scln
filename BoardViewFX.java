public class BoardViewFX implements BoardView {
    private Location[] locs;
    private Player[] players;

    public BoardViewFX(Location[] locs, Player[] players) {
        this.locs = locs;
        this.players = players;
    }

    public void update(String options) {

    }

    public void printBoard(Board board) {
        update("");
    }

}
