package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;

import java.util.List;

/**
 * Class is responsible for registering listeners and instructing the view to draw the initial board.
 * Some of these methods are called in response to actions made to the model (add piece and remove piece.)
 * These methods were part of the Main Controller but were removed to increase cohesion at the expense of
 * increasing coupling.
 */
public class GameController {

    private final MainController mainController;
    private final BoardGrid boardGrid;
    private IGameManager gm;


    /**
     * Instantiates a new Game controller.
     *
     * @param gm             the game manager
     * @param boardGrid      the board grid view class
     * @param mainController the main controller
     */
    public GameController(IGameManager gm, BoardGrid boardGrid, MainController mainController) {
        this.gm = gm;
        this.boardGrid = boardGrid;
        this.mainController = mainController;
    }

    /**
     * Requests the pieces from the model so the pieces can be added to the drawn board.
     */
    public void setUpGame() {
        addPieces(gm.getAllPieces());
    }

    /**
     * Remove a piece from the view.
     *
     * @param piece the piece
     */
    public void removePiece(IPiece piece) {
        boardGrid.removePiece(piece);
    }

    //Add game pieces to the game board.
    private void addPieces(List<IPiece> pieceList) {
        for (IPiece piece : pieceList) {
            addPiece(piece);
        }
    }

    /**
     * Add a piece to the board in response to a change in the model.
     *
     * @param piece the piece
     */
    void addPiece(IPiece piece) {
        //Register move listener
        mainController.getRegisterListeners().registerPieceListener(piece);

        boardGrid.addPiece(piece);

        //set view piece handler.
        for (HexagonTileViewPiece hexagonTileViewPiece : boardGrid.getPieceObservableList()) {
            if (hexagonTileViewPiece.getiPiece().equals(piece)) {
                hexagonTileViewPiece.setOnMouseClicked(event -> mainController.handlePieceClicked(hexagonTileViewPiece));
            }
        }
    }
}
