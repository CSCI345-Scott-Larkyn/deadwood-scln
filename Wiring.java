import javafx.scene.Scene;
import javafx.scene.image.ImageView;


public class Wiring {


    public RoleGUI[] hookUpExtras(Location[] locs, Scene board) {
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
        return roleGUIS;
    }

    public void hookUpVisitors(Location[] locs, Scene root) {
        VisitorsGUI tsV = new VisitorsGUI((ImageView) root.lookup("tsV0"), (ImageView) root.lookup("tsV1"),
                (ImageView) root.lookup("tsV2"), (ImageView) root.lookup("tsV3"), (ImageView) root.lookup("tsV4"),
                (ImageView) root.lookup("tsV5"), (ImageView) root.lookup("tsV6"), (ImageView) root.lookup("tsV7"));
        locs[0].addVisitorsGUI(tsV);

        VisitorsGUI shV = new VisitorsGUI((ImageView) root.lookup("shV0"), (ImageView) root.lookup("shV1"),
                (ImageView) root.lookup("shV2"), (ImageView) root.lookup("shV3"), (ImageView) root.lookup("shV4"),
                (ImageView) root.lookup("shV5"), (ImageView) root.lookup("shV6"), (ImageView) root.lookup("shV7"));
        locs[1].addVisitorsGUI(shV);

        VisitorsGUI cV = new VisitorsGUI((ImageView) root.lookup("cV0"), (ImageView) root.lookup("cV1"),
                (ImageView) root.lookup("cV2"), (ImageView) root.lookup("cV3"), (ImageView) root.lookup("cV4"),
                (ImageView) root.lookup("cV5"), (ImageView) root.lookup("cV6"), (ImageView) root.lookup("cV7"));
        locs[2].addVisitorsGUI(cV);

        VisitorsGUI hV = new VisitorsGUI((ImageView) root.lookup("hV0"), (ImageView) root.lookup("hV1"),
                (ImageView) root.lookup("hV2"), (ImageView) root.lookup("hV3"), (ImageView) root.lookup("hV4"),
                (ImageView) root.lookup("hV5"), (ImageView) root.lookup("hV6"), (ImageView) root.lookup("hV7"));
        locs[3].addVisitorsGUI(hV);

        VisitorsGUI msV = new VisitorsGUI((ImageView) root.lookup("msV0"), (ImageView) root.lookup("msV1"),
                (ImageView) root.lookup("msV2"), (ImageView) root.lookup("msV3"), (ImageView) root.lookup("msV4"),
                (ImageView) root.lookup("msV5"), (ImageView) root.lookup("msV6"), (ImageView) root.lookup("msV7"));
        locs[4].addVisitorsGUI(msV);

        VisitorsGUI jV = new VisitorsGUI((ImageView) root.lookup("jV0"), (ImageView) root.lookup("jV1"),
                (ImageView) root.lookup("jV2"), (ImageView) root.lookup("jV3"), (ImageView) root.lookup("jV4"),
                (ImageView) root.lookup("jV5"), (ImageView) root.lookup("jV6"), (ImageView) root.lookup("jV7"));
        locs[5].addVisitorsGUI(jV);

        VisitorsGUI gsV = new VisitorsGUI((ImageView) root.lookup("gsV0"), (ImageView) root.lookup("gsV1"),
                (ImageView) root.lookup("gsV2"), (ImageView) root.lookup("gsV3"), (ImageView) root.lookup("gsV4"),
                (ImageView) root.lookup("gsV5"), (ImageView) root.lookup("gsV6"), (ImageView) root.lookup("gsV7"));
        locs[6].addVisitorsGUI(gsV);

        VisitorsGUI rV = new VisitorsGUI((ImageView) root.lookup("rV0"), (ImageView) root.lookup("rV1"),
                (ImageView) root.lookup("rV2"), (ImageView) root.lookup("rV3"), (ImageView) root.lookup("rV4"),
                (ImageView) root.lookup("rV5"), (ImageView) root.lookup("rV6"), (ImageView) root.lookup("rV7"));
        locs[7].addVisitorsGUI(rV);

        VisitorsGUI bV = new VisitorsGUI((ImageView) root.lookup("bV0"), (ImageView) root.lookup("bV1"),
                (ImageView) root.lookup("bV2"), (ImageView) root.lookup("bV3"), (ImageView) root.lookup("bV4"),
                (ImageView) root.lookup("bV5"), (ImageView) root.lookup("bV6"), (ImageView) root.lookup("bV7"));
        locs[8].addVisitorsGUI(bV);

        VisitorsGUI sV = new VisitorsGUI((ImageView) root.lookup("sV0"), (ImageView) root.lookup("sV1"),
                (ImageView) root.lookup("sV2"), (ImageView) root.lookup("sV3"), (ImageView) root.lookup("sV4"),
                (ImageView) root.lookup("sV5"), (ImageView) root.lookup("sV6"), (ImageView) root.lookup("sV7"));
        locs[9].addVisitorsGUI(sV);
    }
}
