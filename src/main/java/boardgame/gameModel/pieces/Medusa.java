package boardgame.gameModel.pieces;

import boardgame.util.Location;

public class Medusa extends Monster {
    Medusa(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility(){
        System.out.println("Summoning Snakes!");
    }

    public void createShield() {
    }
}
