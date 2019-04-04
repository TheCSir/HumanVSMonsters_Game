package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public class Griffin extends Monster {
    public Griffin(int _health, int moveSpeed, Location location) {
        super(_health, moveSpeed, location);
    }

    public void basicAttack(){}
    public void summonHawks(){}
}
