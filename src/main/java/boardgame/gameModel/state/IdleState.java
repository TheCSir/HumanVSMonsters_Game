package boardgame.gameModel.state;

public class IdleState implements State {

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Does nothing in idle state");
    }


    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        System.out.println("transitioning to piece selection");

        //Update View.
        gameContext.updatePieceDetails();

        //Set new State.
        gameContext.setState(new OwnPieceSelected());
    }

    @Override
    public void onUseSpecial(GameContext gameContext){ System.out.println("test");}

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("Print tile info?");
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.updateEnemyPieceDetails();

        gameContext.setState(new EnemyPieceSel());
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("Shouldn't be possible to get here");
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("How did you get here?");
    }
}
