package boardgame.gameModel.state;

public class SpecialState extends OwnPieceSelected {

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Already in special state");
    }


    @Override
    public void notSelected(GameContext gameContext) {

    }

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

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
