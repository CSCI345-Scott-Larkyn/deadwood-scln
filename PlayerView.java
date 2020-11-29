public interface PlayerView {
    UpgradeData promptForUpgrade(UpgradeData data);
    Location promptForMove(Location[] neighbors);
    String promptForAction(String options, int budget, int practiceChips);
    int promptForRole(Role[] offCardRoles, Role[] onCardRoles, int playerRank);
    void printActingResults(int roll);
    void printTurnStartStats(int dollars, int credits, int rank, String locationName);
}
