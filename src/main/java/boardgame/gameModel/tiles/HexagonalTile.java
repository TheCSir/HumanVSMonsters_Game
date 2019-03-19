package gameModel.tiles;

public class HexagonalTile extends Tile {

    private int numSides;
    private boolean traversable;
    private int movementCost;

    public int getNumSides(){
        return numSides;
    }

    public boolean getTraversable(){
        return traversable;
    }

    public void returnOrientation(){}

}
