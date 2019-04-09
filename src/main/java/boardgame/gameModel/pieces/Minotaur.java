package boardgame.gameModel.pieces;


import boardgame.gameModel.Location;

public class Minotaur extends Monster {
    public Minotaur(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    //Should this be refactored to Special Action? Could make it easier.
    public void specialAbility(){
        System.out.println("Summoning Bulls!");
    }
}
