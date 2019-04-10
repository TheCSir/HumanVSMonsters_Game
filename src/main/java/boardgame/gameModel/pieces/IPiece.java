package boardgame.gameModel.pieces;

import boardgame.util.Location;
import javafx.beans.property.ObjectProperty;

public interface IPiece {

    void move(int direction);

    Location getLocation();

    void setLocation(Location location);

    ObjectProperty locationPropertyProperty();
}
