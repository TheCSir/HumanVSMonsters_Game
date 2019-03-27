package boardgame.gameModel;

public class MonsterPlayer implements IPlayer {

    private int playerID;
    private String playerName;

    public MonsterPlayer(int playerID, String playerName){
        this.playerID=playerID;
        this.playerName=playerName;
    }

    @Override
    public int getPlayerID() {
        return this.playerID;
    }

    @Override
    public void setPlayerID(int id) {
        this.playerID=id;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String name) {
        this.playerName=name;
    }


}
