package boardgame.gameModel.state;

public class SpecialState extends OwnPieceSelected {

    @Override
    public void onSpecial(GameContext gameContext) {
        System.out.println("Already in special state");
    }


}
