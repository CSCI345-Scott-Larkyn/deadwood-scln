package deadwood;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;


public class Wiring {


    public void hookUpExtras(Location[] locs, Scene board) {
        RoleGUI[] roleGUIS = new RoleGUI[29];
        roleGUIS[0] = new RoleGUI((ImageView) board.lookup("tsOff0"), locs[0].getSet().getOffCardRoles()[0]);
        roleGUIS[1] = new RoleGUI((ImageView) board.lookup("tsOff1"), locs[0].getSet().getOffCardRoles()[1]);
        roleGUIS[2] = new RoleGUI((ImageView) board.lookup("tsOff2"), locs[0].getSet().getOffCardRoles()[2]);
        roleGUIS[3] = new RoleGUI((ImageView) board.lookup("tsOff3"), locs[0].getSet().getOffCardRoles()[3]);
        roleGUIS[4] = new RoleGUI((ImageView) board.lookup("shOff0"), locs[1].getSet().getOffCardRoles()[0]);
        roleGUIS[5] = new RoleGUI((ImageView) board.lookup("shOff1"), locs[1].getSet().getOffCardRoles()[1]);
        roleGUIS[6] = new RoleGUI((ImageView) board.lookup("shOff2"), locs[1].getSet().getOffCardRoles()[2]);
        roleGUIS[7] = new RoleGUI((ImageView) board.lookup("shOff3"), locs[1].getSet().getOffCardRoles()[3]);
        roleGUIS[8] = new RoleGUI((ImageView) board.lookup("cOff0"), locs[2].getSet().getOffCardRoles()[0]);
        roleGUIS[9] = new RoleGUI((ImageView) board.lookup("cOff1"), locs[2].getSet().getOffCardRoles()[1]);
        roleGUIS[10] = new RoleGUI((ImageView) board.lookup("hOff0"), locs[3].getSet().getOffCardRoles()[0]);
        roleGUIS[11] = new RoleGUI((ImageView) board.lookup("hOff1"), locs[3].getSet().getOffCardRoles()[1]);
        roleGUIS[12] = new RoleGUI((ImageView) board.lookup("hOff2"), locs[3].getSet().getOffCardRoles()[2]);
        roleGUIS[13] = new RoleGUI((ImageView) board.lookup("hOff3"), locs[3].getSet().getOffCardRoles()[3]);
        roleGUIS[14] = new RoleGUI((ImageView) board.lookup("msOff0"), locs[4].getSet().getOffCardRoles()[0]);
        roleGUIS[15] = new RoleGUI((ImageView) board.lookup("msOff1"), locs[4].getSet().getOffCardRoles()[1]);
        roleGUIS[16] = new RoleGUI((ImageView) board.lookup("msOff2"), locs[4].getSet().getOffCardRoles()[2]);
        roleGUIS[17] = new RoleGUI((ImageView) board.lookup("msOff3"), locs[4].getSet().getOffCardRoles()[3]);
        roleGUIS[18] = new RoleGUI((ImageView) board.lookup("jOff0"), locs[5].getSet().getOffCardRoles()[0]);
        roleGUIS[19] = new RoleGUI((ImageView) board.lookup("jOff1"), locs[5].getSet().getOffCardRoles()[1]);
        roleGUIS[20] = new RoleGUI((ImageView) board.lookup("gsOff0"), locs[6].getSet().getOffCardRoles()[0]);
        roleGUIS[21] = new RoleGUI((ImageView) board.lookup("gsOff1"), locs[6].getSet().getOffCardRoles()[1]);
        roleGUIS[22] = new RoleGUI((ImageView) board.lookup("rOff1"), locs[7].getSet().getOffCardRoles()[0]);
        roleGUIS[23] = new RoleGUI((ImageView) board.lookup("rOff1"), locs[7].getSet().getOffCardRoles()[1]);
        roleGUIS[24] = new RoleGUI((ImageView) board.lookup("rOff2"), locs[7].getSet().getOffCardRoles()[2]);
        roleGUIS[25] = new RoleGUI((ImageView) board.lookup("bOff0"), locs[8].getSet().getOffCardRoles()[0]);
        roleGUIS[26] = new RoleGUI((ImageView) board.lookup("bOff1"), locs[8].getSet().getOffCardRoles()[1]);
        roleGUIS[27] = new RoleGUI((ImageView) board.lookup("sOff0"), locs[9].getSet().getOffCardRoles()[0]);
        roleGUIS[28] = new RoleGUI((ImageView) board.lookup("sOff1"), locs[9].getSet().getOffCardRoles()[1]);
    }
}
