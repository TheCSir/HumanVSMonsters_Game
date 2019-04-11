package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import boardgame.util.HexGridUtil;
import boardgame.util.Location;
import boardgame.util.LocationFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.List;
import java.util.Map;

public class Board2DHex extends Board2d {

    private ObservableList<IPiece> pieceObservableList = FXCollections.observableArrayList();


    //TODO implement insert piece
    @Override
    public void insertPiece(IPiece piece) {
        pieceObservableList.add(piece);
    }

    //Variable size
    public void setUpTiles(int rows, int columns) {

        for (int x=0; x<rows; x++) {
            for (int y=0; y<columns; y++) {
                Location location = LocationFactory.createLocation(x, y);
                HexagonalTile hexagonalTile = new HexagonalTile(location);
                // hexagonalTiles.add(hexagonalTile);
                boardGrid.put(location, hexagonalTile);
            }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours();
    }

    //Default size
    @Override
    public void setUpTiles() {

        for (int x=0; x<Constants.DEFAULTBOARDROWS; x++) {
               for (int y=0; y<Constants.DEFAULTBOARDCOLUMNS; y++) {
                   Location location = LocationFactory.createLocation(x, y);
                   HexagonalTile hexagonalTile = new HexagonalTile(location);
                  // hexagonalTiles.add(hexagonalTile);
                   boardGrid.put(location, hexagonalTile);
               }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours();
    }

    @Override
    public void addTile(ITile tile) {
        boardGrid.put(tile.getLocation(), tile);
    }

    @Override
    public void addTile(Location location, ITile tile) {
        boardGrid.put(location, tile);
    }

    @Override
    public void deleteTile(Location location, ITile tile) {
        boardGrid.put(location, tile);
    }

    @Override
    public Map<Location, ITile> getTiles() {
        return this.boardGrid;
    }

    @Override
    public boolean checkMapLocation(Location location, int rows, int columns) {
        return super.checkMapLocation(location, rows, columns);
    }

    @Override
    public Map<Integer, IPiece> getPieces() {
        return null;
    }

    @Override
    public IPiece getPiece(int pieceID) {
        return null;
    }

    public ObservableMap<Location, ITile> getBoardGrid() {
        return boardGrid;
    }

    public void setBoardGrid(ObservableMap<Location, ITile> boardGrid) {
        this.boardGrid = boardGrid;
    }

    public ObservableList<IPiece> getPieceObservableList() {
        return pieceObservableList;
    }

    public void setPieceObservableList(ObservableList<IPiece> pieceObservableList) {
        this.pieceObservableList = pieceObservableList;
    }

    //For each tile store their neighbouring tiles.
    private void addNeighbours() {
        for (ITile t: boardGrid.values()) {
            List<Location> neighbourLocations = HexGridUtil.getNeighbourPositions(t.getLocation());

            for (Location location: neighbourLocations) {
                if(checkMapLocation(location, Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS)){
                    t.addNeighbour(boardGrid.get(location));
                }
            }
        }
    }

}
