package gameModel.pieces;

import java.util.HashMap;

public abstract class Piece implements IPiece {

    private int health;
    private int attackStrength;
    private int moveSpeed;
    public HashMap<Integer, Integer> boardLocation;

    public void move(int direction){}
}
