package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.state.GameContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class SwapController {


    public static void handleSwapAction(Pane SwapPane, IGameManager gm, Button Opt_one, Button Opt_two, GameContext gc) {

        gc.pressSwapButton(SwapPane, Opt_one, Opt_two);

    }

    public static void handleSwapOne(IGameManager gm, Pane swapPane, Button opt_one, GameContext gc) {
        gc.pressSwapOne(swapPane, opt_one);

    }

    public static void handleSwapTwo(IGameManager gm, Pane swapPane, Button opt_two, GameContext gc) {
        gc.pressSwapTwo(swapPane, opt_two);
    }
//
//        //Switch the disabled status
//        SwapPane.setVisible(!SwapPane.isVisible());
//
//        //get current piece class
//        String oldPieceName = gm.getTurn().getActivePlayer().getPieces().get(0).getClass().getName();
//
//        // Button label store location;
//        String OptOne = null;
//        String OptTwo = null;
//
//
//        // Check and populate Gui according to current situation
//        if (oldPieceName.equals(Warrior.class.getName())) {
//            OptOne = Archer.class.getSimpleName();
//            OptTwo = Priest.class.getSimpleName();
//
//        } else if (oldPieceName.equals(Priest.class.getName())) {
//            OptOne = Warrior.class.getSimpleName();
//            OptTwo = Archer.class.getSimpleName();
//
//        } else if (oldPieceName.equals(Archer.class.getName())) {
//            OptOne = Warrior.class.getSimpleName();
//            OptTwo = Priest.class.getSimpleName();
//
//        } else if (oldPieceName.equals(Medusa.class.getName())) {
//            OptOne = Griffin.class.getSimpleName();
//            OptTwo = Minotaur.class.getSimpleName();
//
//        } else if (oldPieceName.equals(Griffin.class.getName())) {
//            OptOne = Medusa.class.getSimpleName();
//            OptTwo = Minotaur.class.getSimpleName();
//
//        } else if (oldPieceName.equals(Minotaur.class.getName())) {
//            OptOne = Griffin.class.getSimpleName();
//            OptTwo = Medusa.class.getSimpleName();
//
//        }
//
//        // Set button labels
//        Opt_one.setText(OptOne);
//        Opt_two.setText(OptTwo);
//
//    }
//
//    public static void doSwap(IGameManager gm, Pane SwapPane, Button selection) {
//
//        //Get current piece and it's location
//        IPiece oldPiece = gm.getTurn().getActivePlayer().getPieces().get(0);
//        int x = oldPiece.getLocation().getX();
//        int y = oldPiece.getLocation().getY();
//
//        //Remove current piece
//        gm.getTurn().getActivePlayer().getPieces().remove(oldPiece);
//
//        //Get piece full name
//        String Piece = getClassFullName(selection.getText());
//
//        //Create new piece and add to board
//        IPiece newPiece = PieceFactory.createPiece(Piece, 5, LocationFactory.createLocation(x, y));
//        gm.getTurn().getActivePlayer().getPieces().add(newPiece);
//
//
//        //Handle GUI validations
//        SwapPane.setVisible(false);
//
//        //End turn
//        gm.getTurn().nextTurn(gm.getPlayers());
//    }
//
//    //Return full name of the piece
//    public static String getClassFullName(String piece) {
//        if (piece.equals(Warrior.class.getSimpleName())) {
//            return Warrior.class.getName();
//        } else if (piece.equals(Priest.class.getSimpleName())) {
//            return Priest.class.getName();
//        } else if (piece.equals(Archer.class.getSimpleName())) {
//            return Archer.class.getName();
//        } else if (piece.equals(Medusa.class.getSimpleName())) {
//            return Medusa.class.getName();
//        } else if (piece.equals(Griffin.class.getSimpleName())) {
//            return Griffin.class.getName();
//        } else if (piece.equals(Minotaur.class.getSimpleName())) {
//            return Minotaur.class.getName();
//        } else
//            return null;
//    }
}
