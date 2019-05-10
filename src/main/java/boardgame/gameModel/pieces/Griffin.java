package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;

public class Griffin extends Monster {
    Griffin(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility(IPiece enemyPiece, IGameManager gm){
        System.out.println("Summon Hawks!");
    }
}
