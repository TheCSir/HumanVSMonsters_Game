package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;

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
        gm.getTurn().getActivePlayer().getPieces().remove(piece);
    }
}
