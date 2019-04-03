package boardgame.gameModel.pieces;


import boardgame.gameModel.Location;

public class Minotaur extends Monster {
    public Minotaur(int _health, int moveSpeed, Location location) {
        super(_health, moveSpeed, location);
    }

    public void basicAttack(){}

    //Should this be refactored to Special Action? Could make it easier.
    public void summonBulls(){}
}
