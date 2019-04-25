package boardgame.gameModel.state;

public class DefenceState extends OwnPieceSelected {


    @Override
    public void onDefence(GameContext gameContext) {
        System.out.println("Already in defence state");
    }

    @Override
    public void notSelected(GameContext gameContext) {

    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {
        //TODO implement defence.
    }

    @Override
    public void onSelectTile(GameContext gameContext) {

    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

    }

    @Override
    public void attackPiece(GameContext gameContext) {

    }
}
