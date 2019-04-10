package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;


public interface IPlayer {
    int getPlayerID();

    void setPlayerID(int id);

    String getPlayerName();

    void setPlayerName(String name);

    IntegerProperty healthProperty();

    void setHealthProperty(int value);

    void decreaseHealthProperty();

    ObservableList<IPiece> getPieces();

    void setPieces(ObservableList<IPiece> pieces);
}
