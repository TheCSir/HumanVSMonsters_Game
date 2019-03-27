package boardgame.gameModel.board;

import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.tiles.HexagonalTile;
import boardgame.gameModel.tiles.ITile;
import boardgame.gameModel.Location;
import boardgame.util.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board2DHex extends Board {
    private List<HexagonalTile> hexagonalTiles;
    private ObservableMap<Location, ITile> boardGrid = FXCollections.observableHashMap();

    @Override
    public void insertPiece(IPiece piece) {

    }

    @Override
    public void setUpTiles() {
        Util util = new Util();
        String data = null;
        try {
            data = util.readFile("C:\\Users\\Mohamad\\Desktop\\Other stuff\\Uni\\Sem 1 2019\\OOSD\\A1\\OOSD-Assignment\\src\\main\\java\\boardgame\\gameModel\\maps\\map1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Location> locData = util.convertJsonToObjectArray(data);

        hexagonalTiles = new ArrayList<HexagonalTile>();


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
    public Map<Integer, IPiece> getPieces() {
        return null;
    }

    @Override
    public IPiece getPiece(int pieceID) {
        return null;
    }

    public List<HexagonalTile> getHexagonalTiles(){
        return this.hexagonalTiles;
    }

}
