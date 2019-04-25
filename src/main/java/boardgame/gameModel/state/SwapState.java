package boardgame.gameModel.state;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.*;
import boardgame.util.LocationFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SwapState extends OwnPieceSelected {


    public static void doSwap(IGameManager gm, Pane SwapPane, Button selection) {

        //Get current piece and it's location
        IPiece oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);
        int x = oldPiece.getLocation().getX();
        int y = oldPiece.getLocation().getY();

        //Remove current piece
        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);

        //Get piece full name
        String Piece = getClassFullName(selection.getText());

        //Create new piece and add to board
        IPiece newPiece = PieceFactory.createPiece(Piece, 5, LocationFactory.createLocation(x, y));
        gm.getTurn().getActivePlayer().getPieces().add(newPiece);


        //Handle GUI validations
        SwapPane.setVisible(false);

        //End turn
        gm.getTurn().nextTurn(gm.getPlayers());

    }

    //Return full name of the piece
    public static String getClassFullName(String piece) {
        if (piece.equals(Warrior.class.getSimpleName())) {
            return Warrior.class.getName();
        } else if (piece.equals(Priest.class.getSimpleName())) {
            return Priest.class.getName();
        } else if (piece.equals(Archer.class.getSimpleName())) {
            return Archer.class.getName();
        } else if (piece.equals(Medusa.class.getSimpleName())) {
            return Medusa.class.getName();
        } else if (piece.equals(Griffin.class.getSimpleName())) {
            return Griffin.class.getName();
        } else if (piece.equals(Minotaur.class.getSimpleName())) {
            return Minotaur.class.getName();
        } else
            return null;
    }

    @Override
    public void onSwap(GameContext gameContext) {
        System.out.println("Already in swap state");
    }

    @Override
    public void onSwapOne(GameContext gameContext) {
        System.out.println("Selecting first option");
        doSwap(gameContext.getGm(), gameContext.getSwapPane(), gameContext.getOpt_one());
        gameContext.setState(new IdleState());
    }

    @Override
    public void onSwapTwo(GameContext gameContext) {
        System.out.println("Selecting second option.");
        doSwap(gameContext.getGm(), gameContext.getSwapPane(), gameContext.getOpt_two());
        gameContext.setState(new IdleState());
    }

}
