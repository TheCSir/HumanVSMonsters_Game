package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public interface IPiece {

    public void move(int direction);

    Location getGridPosition();

    void setLocation(Location location);
}
