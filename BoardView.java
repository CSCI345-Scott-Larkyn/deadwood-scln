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
    
    
    public void printBoard(Board board) {
        Location[] locs = board.getLocations();
        calculateStrings(locs);
        printRows();
    }
    
    //highly dependant on the order of the XMl file parsing
    //but its a bonus feature so there are some shortcuts
    private void calculateStrings(Location[] locs) {
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
    
    private void printRows() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        
        System.out.println("X" + station[1] + "X" + jail[1] + "X" + street[1] + "X");
        System.out.println("X" + station[2] + "X" + jail[2] + "X" + street[2] + "X");
        System.out.println("X" + station[3] + "X" + jail[3] + "X" + street[3] + "X");
        System.out.println("X" + station[4] + "X" + jail[4] + "X" + street[4] + "X");
        System.out.println("X" + station[5] + "X" + jail[5] + "X" + street[5] + "X");
        System.out.println("X" + station[6] + "X" + jail[6] + "XXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("X" + station[7] + "XXXXXXXXXXXXXXXXX" + saloon[1] + "X" + trailers[1] + "X");
        System.out.println("X" + station[8] + "X" + store[1] + "X" + saloon[2] + "X" + trailers[2] + "X");
        System.out.println("X" + station[9] + "X" + store[2] + "X" + saloon[3] + "X" + trailers[3] + "X");
        System.out.println("X" + station[10] + "X" + store[3] + "X" + saloon[4] + "X" + trailers[4] + "X");
        System.out.println("X" + station[11] + "X" + store[4] + "X" + saloon[5] + "X" + trailers[5] + "X");
        System.out.println("X" + station[12] + "X" + store[5] + "X" + saloon[6] + "X" + trailers[6] + "X");
        
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        
        System.out.println("X" + office[1] + "X" + ranch[1] + "X" + bank[1] + "X" + hotel[1] + "X");
        System.out.println("X" + office[2] + "X" + ranch[2] + "X" + bank[2] + "X" + hotel[2] + "X");
        System.out.println("X" + office[3] + "X" + ranch[3] + "X" + bank[3] + "X" + hotel[3] + "X");
        System.out.println("X" + office[4] + "X" + ranch[4] + "X" + bank[4] + "X" + hotel[4] + "X");
        System.out.println("X" + office[5] + "X" + ranch[5] + "X" + bank[5] + "X" + hotel[5] + "X");
        System.out.println("X" + office[6] + "X" + ranch[6] + "XXXXXXXXXXXXXXXXX" + hotel[6] + "X");
        System.out.println("X" + office[7] + "X" + ranch[7] + "X" + church[1] + "X" + hotel[7] + "X");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX" + church[2] + "X" + hotel[8] + "X");
        System.out.println("X" + hideout[1] + "X" + church[3] + "X" + hotel[9] + "X");
        System.out.println("X" + hideout[2] + "X" + church[4] + "X" + hotel[10] + "X");
        System.out.println("X" + hideout[3] + "X" + church[5] + "X" + hotel[11] + "X");
        System.out.println("X" + hideout[4] + "X" + church[6] + "X" + hotel[12] + "X");
        
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
    
    private String card(int num, Location loc) {
        if (loc.getSet().getCard() == null) {
            return "x";
        }
        Role[] roles = loc.getSet().getCard().getRoles();
        if (num - 1 >= roles.length) {
            return "x";
        }
        if (roles[num - 1].getPlayer() == null) {
            return "" + roles[num - 1].getRank();
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
            return "" + roles[num - 1].getRank();
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
    
    private String shot(Location loc) {
        return "" + loc.getSet().getCurrentShotCount();
    }
    
    private String card123(Location loc) {
        return card(1, loc) + card(2, loc) + card(3, loc);
    }
    
    private String vis1234(Location loc) {
        return vis(1, loc) + vis(2, loc) + vis(3, loc) + vis(4, loc);
    }
    
    private String vis5678(Location loc) {
        return vis(5, loc) + vis(6, loc) + vis(7, loc) + vis(8, loc);
    }
    
    private void calculateStationStrings(Location loc) {
        station[1] = "Train:" + shot(loc);
        station[2] = "card   ";
        station[3] = "+---+  ";
        station[4] = "|" + card123(loc) + "|  ";
        station[5] = "+---+  ";
        station[6] = "e+    +";
        station[7] = "x|" + ex(1, loc) + ex(2, loc) + ex(3, loc) + ex(4, loc) + "|";
        station[8] = " +----+";
        station[9] = " +----+";
        station[10] = "v|" + vis1234(loc) + "|";
        station[11] = "i|" + vis5678(loc) + "|";
        station[12] = "s+----+";
    }
    
    private void calculateJailStrings(Location loc) {
        jail[1] = " Jail:" + shot(loc) + "   vis  ";
        jail[2] = " card ex +----+";
        jail[3] = " +---++-+|" + vis1234(loc) + "|";
        jail[4] = " |" + card123(loc) + "||" +  ex(1, loc) + "||" + vis5678(loc) + "|";
        jail[5] = " +---+|" + ex(2, loc) + "|+----+";
        jail[6] = "      +-+      ";
    }
    
    private void calculateStreetStrings(Location loc) {
        street[1] = "  Main Street:" + shot(loc) + "        ";
        street[2] = "card  ex     +----+    ";
        street[3] = "+---++----+ v|" + vis1234(loc) + "|    ";
        street[4] = "|" + card123(loc) + "||" + ex(1, loc) + ex(2, loc) + ex(3, loc) + ex(4, loc) + "| i|" + vis5678(loc) + "|    ";
        street[5] = "+---++----+ s+----+    ";
    }
    
    private void calculateStoreStrings(Location loc) {
        store[1] = "Store:" + shot(loc) + "   vis  ";
        store[2] = "card  ex +----+";
        store[3] = "+---++--+|" + vis1234(loc) + "|";
        store[4] = "|" + card123(loc) + "||" + ex(1, loc) + ex(2, loc) + "||" + vis5678(loc) + "|";
        store[5] = "+---++--++----+";
    }
    
    private void calculateSaloonStrings(Location loc) {
        saloon[1] = "Saloon:" + shot(loc) + "       ";
        saloon[2] = "          vis  ";
        saloon[3] = "card  ex +----+";
        saloon[4] = "+---++--+|" + vis1234(loc) + "|";
        saloon[5] = "|" + card123(loc) + "||" + ex(1, loc) + ex(2, loc) + "||" + vis5678(loc) + "|";
        saloon[6] = "+---++--++----+";
    }
    
    private void calculateTrailersStrings(Location loc) {
        trailers[1] = "Trailer";
        trailers[2] = " +----+";
        trailers[3] = "v|" + vis1234(loc) + "|";
        trailers[4] = "i|" + vis5678(loc) + "|";
        trailers[5] = "s+----+";
        trailers[6] = "       ";
    }
    
    private void calculateOfficeStrings(Location loc) {
        office[1] = "Office";
        office[2] = " vis  ";
        office[3] = "+----+";
        office[4] = "|" + vis1234(loc) + "|";
        office[5] = "|" + vis5678(loc) + "|";
        office[6] = "+----+";
        office[7] = "      ";
    }
    
    private void calculateRanchStrings(Location loc) {
        ranch[1] = "  Ranch:" + shot(loc) + "       ";
        ranch[2] = "card  es vis    ";
        ranch[3] = "+---++-+ +----+ ";
        ranch[4] = "|" + card123(loc) + "||" + ex(1, loc) + "| |" + vis5678(loc) + "| ";
        ranch[5] = "+---+|" + ex(2, loc) + "| |" + vis5678(loc) + "| ";
        ranch[6] = "     |" + ex(3, loc) + "| +----+ ";
        ranch[7] = "     +-+        "; 
    }
    
    private void calculateBankStrings(Location loc) {
        bank[1] = " Bank:" + shot(loc) + "   vis  ";
        bank[2] = "card  ex +----+";
        bank[3] = "+---++--+|" + vis1234(loc) + "|";
        bank[4] = "|" + card123(loc) + "||" + ex(1, loc) + ex(2, loc) + "||" + vis5678(loc) + "|";
        bank[5] = "+---++--++----+";
    }
    
    private void calculateHotelStrings(Location loc) {
        hotel[1] = "Hotel:" + shot(loc);
        hotel[2] = " card  ";
        hotel[3] = " +---+ ";
        hotel[4] = " |" + card123(loc) + "| ";
        hotel[5] = " +---+ ";
        hotel[6] = "e+----+";
        hotel[7] = "x|" + ex(1, loc) + ex(2, loc) + ex(3, loc) + ex(4, loc) + "|";
        hotel[8] = " +----+";
        hotel[9] = "v+----+";
        hotel[10] = "i|" + vis1234(loc) + "|";
        hotel[11] = "s|" + vis5678(loc) + "|";
        hotel[12] = " +----+";
    }
    
    private void calculateHideoutStrings(Location loc) {
        hideout[1] = " +----+Hideout:" + shot(loc) + " card  ";
        hideout[2] = "v|" + vis1234(loc) + "|  +----+  +---+ ";
        hideout[3] = "i|" + vis5678(loc) + "| e|" + ex(1, loc) + ex(2, loc) + ex(3, loc) + ex(4, loc) + "|  |" + card123(loc) + "| ";
        hideout[4] = "s+----+ x+----+  +---+ ";
    }
    
    private void calculateChurchStrings(Location loc) {
        church[1] = " Church:" + shot(loc) + "      ";
        church[2] = "          vis  ";
        church[3] = "card  ex +----+";
        church[4] = "+---++--+|" + vis1234(loc) + "|";
        church[5] = "|" + card123(loc) + "||" + ex(1, loc) + ex(2, loc) + "||" + vis5678(loc) + "|";
        church[6] = "+---++--++----+";
    }
}