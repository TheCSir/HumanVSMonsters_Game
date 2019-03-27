package boardgame.gameModel;

public class HumanPlayer implements IPlayer {

    public HumanPlayer(int playerID, String playerName) {
        this.playerID = playerID;
        this.playerName = playerName;
    }

    private int playerID;
    private String playerName;

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public int getPlayerID(){
        return playerID;
    }

    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }
}
