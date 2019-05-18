package boardgame.gameModel.state;

public class AttackState extends OwnPieceSelected {


    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Already in attack state");
    }


    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.attackPiece();
        gameContext.setState(states.IDLE);
        System.out.println(gameContext.getState().getClass().getSimpleName());
    }

    @Override
    public void accept(HighlightVisitor v) {
        v.visit(this);
    }
}
