package boardgame.gameModel.state;

public class SpecialState extends OwnPieceSelected {

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Already in special state");
    }


    //TODO implement state changes.

    @Override
    public void onSelectOwnPiece(GameContext gameContext) {

    }

    @Override
    public void onSelectTile(GameContext gameContext) {

    }

    @Override
    public void onSelectEnemyPiece(GameContext gameContext) {

    }

}
