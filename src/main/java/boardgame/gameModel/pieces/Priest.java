package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;


public class Priest extends Human {
    Priest(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility(IPiece enemyPiece, IGameManager gm){
        gm.getActivePlayer().increaseHealthProperty(3);
        System.out.println("Healing!");
    }
}
