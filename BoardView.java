import java.util.*;

public class BoardView {   
    //treat as 1-indexed
    private String[] station = new String[13];
    private String[] jail = new String[7];
    private String[] street = new String[6];
    private String[] store = new String[6];
    private String[] saloon = new String[7];
    private String[] trailers = new String[7];
    private String[] office = new String[8];
    private String[] ranch = new String[8];
    private String[] bank = new String[6];
    private String[] hotel = new String[13];
    private String[] hideout = new String[5];
    private String[] church = new String[7];
    
    //highly dependant on the order of the XMl file parsing
    //but its a bonus feature so there are some shortcuts
    public void printBoard(Board board) {
        Location[] locs = board.getLocations();
        calculateStationStrings(locs[0]);
        calculateJailStrings(locs[5]);
        calculateStreetStrings(locs[4]);
        calculateStoreStrings(locs[6]);
        calculateSaloonStrings(locs[9]);
        calculateTrailersStrings(locs[10]);
        calculateOfficeStrings(locs[11]);
        calculateRanchStrings(locs[7]);
        calculateBankStrings(locs[8]);
        calculateHotelStrings(locs[3]);
        calculateHideoutStrings(locs[1]);
        calculateChurchStrings(locs[2]);
    }
    
    private String card(int num, Location loc) {
        if (loc.getSet().getCard() == null) {
            return "x";
        }
        Role[] roles = loc.getSet().getCard().getRoles();
        if (roles[num - 1].getPlayer() == null) {
            return".";
        } else {
            return roles[num - 1].getPlayer().getName();
        }
    }
    
    private String ex(int num, Location loc) {
        if (loc.getSet().getCard() == null) {
            return "x";
        }
        Role[] roles = loc.getSet().getOffCardRoles();
        if (roles[num - 1].getPlayer() == null) {
            return ".";
        } else {
            return roles[num - 1].getPlayer().getName();
        }
    }
    
    private String vis(int num, Location loc) {
        if (num > loc.getVisitingPlayers().size()) {
            return ".";
        } else {
            return loc.getVisitingPlayers().get(num - 1).getName();
        }
    }
    
    private void calculateStationStrings(Location loc) {
        station[1] = "Train:" + loc.getSet().getCurrentShotCount();
        station[2] = "card   ";
        station[3] = "+---+  ";
        station[4] = "|" + card(1, loc) + card(2, loc) + card(3, loc) + "|  ";
        station[5] = "+---+  ";
        station[6] = "e+    +";
        station[7] = "x|" + ex(1, loc) + ex(2, loc) + ex(3, loc) + ex(4, loc) + "|";
        station[8] = " +----+";
        station[9] = " +----+";
        station[10] = "v|" + vis(1, loc) + vis(2, loc) + vis(3, loc) + vis(4, loc) + "|";
        station[11] = "i|" + vis(5, loc) + vis(6, loc) + vis(7, loc) + vis(8, loc) + "|";
        station[12] = "s+----+";
    }
    
    private void calculateJailStrings(Location loc) {
    
    }
    
    private void calculateStreetStrings(Location loc) {
    
    }
    
    private void calculateStoreStrings(Location loc) {
    
    }
    
    private void calculateSaloonStrings(Location loc) {
    
    }
    
    private void calculateTrailersStrings(Location loc) {
    
    }
    
    private void calculateOfficeStrings(Location loc) {
    
    }
    
    private void calculateRanchStrings(Location loc) {
    
    }
    
    private void calculateBankStrings(Location loc) {
    
    }
    
    private void calculateHotelStrings(Location loc) {
    
    }
    
    private void calculateHideoutStrings(Location loc) {
    
    }
    
    private void calculateChurchStrings(Location loc) {
    
    }
}