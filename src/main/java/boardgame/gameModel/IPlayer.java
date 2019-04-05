package boardgame.gameModel;

import boardgame.gameModel.pieces.IPiece;
import javafx.beans.property.IntegerProperty;

import java.util.List;

public interface IPlayer {
    public int getPlayerID();
    public void setPlayerID(int id);
    public String getPlayerName();
    public void setPlayerName(String name);
    public IntegerProperty healthProperty();
    public void setHealthProperty(int value);
    public void decreaseHealthProperty();
    public List<IPiece> getPieces();
    public void setPieces(List<IPiece> pieces);
}
