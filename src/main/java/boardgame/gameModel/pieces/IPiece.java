package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public interface IPiece {

    public void move(int direction);

    Location getLocation();

    void setLocation(Location location);

    void setLocationProperty(Location location);
}
