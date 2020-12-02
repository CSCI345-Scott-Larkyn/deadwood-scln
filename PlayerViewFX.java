public class PlayerViewFX implements PlayerView {

    public UpgradeData promptForUpgrade(UpgradeData data) {
        //open the upgradeFX window and call the correct method on the UpgradeManager
        //based on the button chosen
        //return the UpgradeData the the UpgradeManager returns
        return null;
    }

    public Location promptForMove(Location[] neighbors) {
        //probably just open the drop down menu
        //return the location that corresponds to the option chosen
        return null;
    }

    public String promptForAction(String options, int budget, int practiceChips) {
        return null;
    }
    //enable and disable the proper buttons based on the options array
    //this will be part of a call to update the whole game
    //return, as a string, the initial of the action (m, t, u, a, r, e)

    public int promptForRole(Role[] offCardRoles, Role[] onCardRoles, int playerRank) {
        //open the takeRoleFX window and get a button press
        //if the window is xed out, return 0 since no role was taken
        //return the number of the rank chosen
        //and negative for off card, positive for on card
        return 0;
    }

    public void printActingResults(int roll) {
        //not sure if we'll end up implementing this or not, but not for now
    }

    public void printTurnStartStats(int dollars, int credits, int rank, String locationName) {
        //taken care of by the playerStat bar at the bottom
        //and updated in the prompt for action method
    }
}
