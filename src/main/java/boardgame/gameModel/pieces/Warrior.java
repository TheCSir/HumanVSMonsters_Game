package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public class Warrior extends Human {
    public Warrior(int _health, int moveSpeed, Location location) {
        super(_health, moveSpeed, location);
    }

    public void basicAttack(){}
    public void bash(){}
}
