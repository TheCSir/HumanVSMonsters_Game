package boardgame.gameModel.board;

public class Location {
    private int xAxis;
    private int yAxis;

    public int getYAxis(){
        return this.yAxis;
    }

    public int getXAxis(){
        return this.xAxis;
    }

    public void setYAxis(int yAxis){
        this.yAxis = yAxis;
    }

    public void setXAxis(int xAxis){
        this.xAxis = xAxis;
    }
}
