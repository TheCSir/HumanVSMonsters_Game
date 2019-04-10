package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Warrior extends Human {
    Warrior(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}
    public void specialAbility(){
        System.out.println("Bash");
    }
}
