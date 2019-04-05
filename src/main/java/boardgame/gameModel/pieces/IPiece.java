package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;
import javafx.beans.property.ObjectProperty;

public interface IPiece {

    public void move(int direction);

    Location getLocation();

    void setLocation(Location location);

    ObjectProperty locationPropertyProperty();
}
