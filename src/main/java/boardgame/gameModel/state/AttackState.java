package boardgame.gameModel.state;

public class AttackState extends OwnPieceSelected {


    @Override
    public void onAttack(GameContext gameContext) {
        System.out.println("Already in attack state");
    }


    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

        gameContext.attackPiece();

        gameContext.setState(new IdleState());
    }

}
