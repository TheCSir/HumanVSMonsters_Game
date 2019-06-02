package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.Location;
import boardgame.view.TileView;

import java.util.List;

/**
 * Turn facade is used to simplify some long calls being made.
 * For example gm.getTurn.getActivePlayer.get.....
 * Intended to hide implementation details from calling package.
 * Classes being called know nothing of Facade.
 */
public class TurnFacade {

    private IGameManager gm;

    public TurnFacade(IGameManager gm) {
        this.gm = gm;
    }

    public void setGameManager(IGameManager gm) {
        this.gm = gm;
    }

    public void nextTurn() {
        gm.getTurn().nextTurn(gm.getPlayers());
    }

    //This was moved to facade. This logic was being called in every different Command in Command Pattern.
    // Instead refactored to facade.
    public void goBackOneTurn() {
        //Roll back turn.
        int previousTurn = gm.getTurn().getTurnNumber() - 1;
        gm.getTurn().setTurnNumberProperty(previousTurn);

        // This should handle having multiple players on the board
        int nextPlayerIndex = gm.getTurn().getTurnNumber() % gm.getPlayers().size();
        gm.getTurn().setActivePlayer(gm.getPlayers().get(nextPlayerIndex));
    }

    public int getTurnNumber() {
        return gm.getTurn().getTurnNumber();
    }

    public List<IPiece> getActivePlayerPieces() {
        return gm.getTurn().getActivePlayer().getPieces();
    }

    public void addPiece(IPiece piece) {
        gm.getTurn().getActivePlayer().getPieces().add(piece);
    }

    public void removePiece(IPiece piece) {
        gm.getAttackedPlayer(piece).getPieces().remove(piece);
    }

    // Checks if selected piece belongs to the active player
    public boolean isActivePlayerPiece(IPiece ipiece) {

        for (IPiece piece : getActivePlayerPieces()) {
            if (piece.getClass().getSuperclass().equals(ipiece.getClass().getSuperclass())) {
                return true;
            }
        }
        return false;
    }

    public double calculateEnemyDamage(double damage, IPiece defendingPiece) {
        return defendingPiece.calculateDamage(damage);
    }

    public void applyEnemyDamage(IPiece enemyPiece, double damage) {
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(damage);
    }
//
//    public void applyEnemyDamage(Minion minion, double damage) {
//        System.out.println("hidyho");
//        minion.setHealth(minion.getHealth() - damage);
//    }

    public IPlayer getActivePlayer() {
        return gm.getActivePlayer();
    }

    public int turnNumber() {
        return gm.getTurn().getTurnNumber();
    }

    public IPlayer getAttackedPlayer(IPiece enemyPiece) {
        return gm.getAttackedPlayer(enemyPiece);
    }

    public void resetAbilityUsed() {
        gm.getPlayers().get(getOpponentPlayerID(gm.getActivePlayer())).resetIsAbilityUsed();
    }

    private int getOpponentPlayerID(IPlayer activePlayer) {
        if (gm.getPlayers().get(0).equals(activePlayer)) {
            return 1;
        } else
            return 0;
    }

    public void movePiece(IPiece selectedPiece, Location destination) {
        gm.getiBoard().movePiece(selectedPiece, destination);
    }

    public void clickTile(TileView target2) {
        gm.getGameContext().clickTile(target2);
    }

    public boolean abilityUsedStatus() {
        return gm.getActivePlayer().getIsAbilityUsed();
    }

    public void setEnemyHealth(IPiece selectedPiece, double finalDamage) {
        IPlayer player = gm.getAttackedPlayer(selectedPiece);
        gm.getAttackedPlayer(selectedPiece).healthProperty().setValue(player.healthProperty().get() - finalDamage);
    }

    public double getPlayerHealth(IPiece selectedPiece) {
        return gm.getAttackedPlayer(selectedPiece).healthProperty().get();
    }

    public void setAbilityUsed() {
        gm.getActivePlayer().setIsAbilityUsed(gm.getTurn().getTurnNumber());
    }

    public void increaseEnemyHealth(IPiece selectedPiece, double finalDamage) {
        gm.getAttackedPlayer(selectedPiece).increaseHealthProperty(finalDamage);
    }


}
