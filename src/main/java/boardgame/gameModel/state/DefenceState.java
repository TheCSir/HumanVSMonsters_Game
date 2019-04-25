package boardgame.gameModel.state;

public class DefenceState extends OwnPieceSelected {


    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Already in defence state");
    }


    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        //TODO implement defence.
        // Get active player and create shield
        gameContext.getBoardGrid().getSelectedTilePiece().getiPiece().createShield(gameContext.getGm().getTurn().getTurnNumber());
        System.out.println("Defending");
        // end turn
        gameContext.getGm().getTurn().nextTurn(gameContext.getGm().getPlayers());

        gameContext.setState(new IdleState());
    }

}
