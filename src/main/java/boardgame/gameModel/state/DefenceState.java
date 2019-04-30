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

        gameContext.createShield();

        gameContext.setState(new IdleState());
    }

}
