package boardgame.gameModel.pieces;


import boardgame.util.Location;

public class Minotaur extends Monster {
    Minotaur(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility(){
        System.out.println("Summoning Bulls!");
    }
}
