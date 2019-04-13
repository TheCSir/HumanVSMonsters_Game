package boardgame.gameModel.pieces;

import boardgame.util.Location;


public class Priest extends Human {
    Priest(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility(){
        System.out.println("Healing!");
    }
}
