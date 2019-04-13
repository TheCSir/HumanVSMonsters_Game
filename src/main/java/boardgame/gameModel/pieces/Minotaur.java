package boardgame.gameModel.pieces;


import boardgame.util.Location;

public class Minotaur extends Monster {
    Minotaur(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    //Should this be refactored to Special Action? Could make it easier.
    public void specialAbility(){
        System.out.println("Summoning Bulls!");
    }
}
