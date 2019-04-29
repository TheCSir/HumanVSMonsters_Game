package boardgame.gameModel.state;

public class MoveState extends OwnPieceSelected {

    public MoveState() {
        System.out.println("In move state");
    }

    @Override
    public void onMove(GameContext gameContext) {
        System.out.println("Already in move state");
    }

    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Moving to attack state");
        gameContext.setState(new AttackState());
    }

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("changing to special state");
    }

    @Override
    public void onDefence(GameContext gameContext) {
        //TODO add once defence State added.
    }

    @Override
    public void onSwap(GameContext gameContext) {
        //TODO Add once Swap State added.
    }

    @Override
    public void notSelected(GameContext gameContext) {

    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

    }

    @Override
    public void onSelectTile(GameContext gameContext) {
        System.out.println("The piece should try to move now. If it can't move stay in this state or go to idle");

        gameContext.resetTileColours();

        gameContext.movePiece();

        gameContext.setState(new IdleState());
    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

    }

    @Override
    public void attackPiece(GameContext gameContext) {

    }
}
