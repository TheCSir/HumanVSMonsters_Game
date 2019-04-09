package boardgame.gameModel.pieces;

import boardgame.gameModel.Location;

public class Griffin extends Monster {
    Griffin(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}
    public void specialAbility(){
        System.out.println("Summon Hawks!");
    }
}
