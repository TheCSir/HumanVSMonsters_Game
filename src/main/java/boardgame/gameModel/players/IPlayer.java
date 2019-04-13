package boardgame.gameModel.players;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;


public interface IPlayer {
    int getPlayerID();

    void setPlayerID(int id);

    String getPlayerName();

    void setPlayerName(String name);

    DoubleProperty healthProperty();

    void setHealthProperty(double value);

    void decreaseHealthProperty();

    boolean getIsShielded();

    void setIsShielded(boolean isShielded);

    void createShield();

    ObservableList<IPiece> getPieces();

    void setPieces(ObservableList<IPiece> pieces);
}
