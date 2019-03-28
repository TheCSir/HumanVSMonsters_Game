package boardgame.gameModel.board;

import boardgame.gameModel.Location;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board2DHex extends Board2d {
    private List<HexagonalTile> hexagonalTiles = new ArrayList<>();
    private ObservableMap<Location, ITile> boardGrid = FXCollections.observableHashMap();
    private ObservableMap<Integer, IPiece> pieceObservableMap = FXCollections.observableHashMap();

    @Override
    public void insertPiece(IPiece piece) {

    }



    @Override
    public void setUpTiles() {

        for (int x=0; x<Constants.DEFAULTBOARDROWS; x++) {
               for (int y=0; y<Constants.DEFAULTBOARDCOLUMNS; y++) {
                   Location location = new Location(x, y);
                   HexagonalTile hexagonalTile = new HexagonalTile(location);
                   hexagonalTiles.add(hexagonalTile);
                   boardGrid.put(location, hexagonalTile);
               }
        }

        //For each tile add the neighbouring tiles.
        addNeighbours();


    }

    @Override
    public void addTile(ITile tile) {
        hexagonalTiles.add((HexagonalTile) tile);
    }

    private boolean checkValidMove(IPiece piece, Location location, Board2DHex board2DHex) {
        List<ITile> neighbours = boardGrid.get(piece.getGridPosition()).getNeighbours();
        for (ITile tile: neighbours) {
            if (tile.getGridPosition().equals(location)){
                System.out.println("Valid move!");
                return true;
            }
        }
        System.out.println("Invalid move!");
        return false;
    }

    public void setUpTiles(int rows, int columns) {

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

    @Override
    public void movePiece(IPiece piece, Location location) {

        //First check that moving to a neighbouring position. If so change location.
        if (checkValidMove(piece, location, this)){
            piece.setLocation(location);
        }

    }

    public List<HexagonalTile> getHexagonalTiles(){
        return this.hexagonalTiles;
    }

    //For each tile store their neighbouring tiles.
    public void addNeighbours() {
        for (HexagonalTile t: hexagonalTiles) {
            int tGridX = t.getGridPosition().getX();
            int tGRidY = t.getGridPosition().getY();

            List<Location> neighbourLocations = t.getNeighbourPositions(t.getGridPosition());

            for (Location location: neighbourLocations) {
                if(checkMapLocation(location, Constants.DEFAULTBOARDROWS, Constants.DEFAULTBOARDCOLUMNS)){
                    t.addNeighbour(boardGrid.get(location));
                }
            }

        }
    }

}
