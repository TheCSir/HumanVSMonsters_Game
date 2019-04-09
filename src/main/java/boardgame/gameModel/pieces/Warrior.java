package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public class Warrior extends Human {
    public Warrior(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}
    public void specialAbility(){
        System.out.println("Bash");
    }
}
