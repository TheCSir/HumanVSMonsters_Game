package boardgame.gameModel.pieces;


public class Minotaur extends Monster {
    public Minotaur(int _health, int moveSpeed) {
        super(_health, moveSpeed);
    }

    public void basicAttack(){}

    //Should this be refactored to Special Action? Could make it easier.
    public void summonBulls(){}
}
