package boardgame.controller;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.pieces.IPiece;
import boardgame.view.BoardGrid;
import boardgame.view.HexagonTileViewPiece;

import java.util.List;

public class GameController {

    private final MainController mainController;
    private final BoardGrid boardGrid;
    private IGameManager gm;


    public GameController(IGameManager gm, BoardGrid boardGrid, MainController mainController) {
        this.gm = gm;
        this.boardGrid = boardGrid;
        this.mainController = mainController;
    }

    public void setUpGame() {
        addPieces(gm.getAllPieces());
    }

    public void removePiece(IPiece piece) {
        boardGrid.removePiece(piece);
    }

    //Add game pieces to the game board.
    private void addPieces(List<IPiece> pieceList) {
        for (IPiece piece : pieceList) {
            addPiece(piece);
        }
    }

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
