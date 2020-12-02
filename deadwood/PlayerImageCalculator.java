package deadwood;

public class PlayerImageCalculator {

    private String[] player1Images = new String[6];
    private String[] player2Images = new String[6];
    private String[] player3Images = new String[6];
    private String[] player4Images = new String[6];
    private String[] player5Images = new String[6];
    private String[] player6Images = new String[6];
    private String[] player7Images = new String[6];
    private String[] player8Images = new String[6];
    private String[] playerWImages = new String[6];
    private String[][] players = new String[9][6];

    public PlayerImageCalculator() {
        populateArrays();
    }


    public String getPlayerImage(Player player) {
        return players[player.getPlayerNum() - 1][player.getRank() - 1];
    }

    public String getBlankPlayerImage(int rank) {
        return players[8][rank - 1];
    }

    private void populateArrays() {
        player1Images[0] = "dice/b1.png";
        player1Images[1] = "dice/b2.png";
        player1Images[2] = "dice/b3.png";
        player1Images[3] = "dice/b4.png";
        player1Images[4] = "dice/b5.png";
        player1Images[5] = "dice/b6.png";

        player2Images[0] = "dice/c1.png";
        player2Images[1] = "dice/c2.png";
        player2Images[2] = "dice/c3.png";
        player2Images[3] = "dice/c4.png";
        player2Images[4] = "dice/c5.png";
        player2Images[5] = "dice/c6.png";

        player3Images[0] = "dice/g1.png";
        player3Images[1] = "dice/g2.png";
        player3Images[2] = "dice/g3.png";
        player3Images[3] = "dice/g4.png";
        player3Images[4] = "dice/g5.png";
        player3Images[5] = "dice/g6.png";

        player4Images[0] = "dice/o1.png";
        player4Images[1] = "dice/o2.png";
        player4Images[2] = "dice/o3.png";
        player4Images[3] = "dice/o4.png";
        player4Images[4] = "dice/o5.png";
        player4Images[5] = "dice/o6.png";

        player5Images[0] = "dice/p1.png";
        player5Images[1] = "dice/p2.png";
        player5Images[2] = "dice/p3.png";
        player5Images[3] = "dice/p4.png";
        player5Images[4] = "dice/p5.png";
        player5Images[5] = "dice/p6.png";

        player6Images[0] = "dice/r1.png";
        player6Images[1] = "dice/r2.png";
        player6Images[2] = "dice/r3.png";
        player6Images[3] = "dice/r4.png";
        player6Images[4] = "dice/r5.png";
        player6Images[5] = "dice/r6.png";

        player7Images[0] = "dice/v1.png";
        player7Images[1] = "dice/v2.png";
        player7Images[2] = "dice/v3.png";
        player7Images[3] = "dice/v4.png";
        player7Images[4] = "dice/v5.png";
        player7Images[5] = "dice/v6.png";

        player8Images[0] = "dice/y1.png";
        player8Images[1] = "dice/y2.png";
        player8Images[2] = "dice/y3.png";
        player8Images[3] = "dice/y4.png";
        player8Images[4] = "dice/y5.png";
        player8Images[5] = "dice/y6.png";

        playerWImages[0] = "dice/w1.png";
        playerWImages[1] = "dice/w2.png";
        playerWImages[2] = "dice/w3.png";
        playerWImages[3] = "dice/w4.png";
        playerWImages[4] = "dice/w5.png";
        playerWImages[5] = "dice/w6.png";

        players[0] = player1Images;
        players[1] = player2Images;
        players[2] = player3Images;
        players[3] = player4Images;
        players[4] = player5Images;
        players[5] = player6Images;
        players[6] = player7Images;
        players[7] = player8Images;
        players[8] = playerWImages;
    }
}
