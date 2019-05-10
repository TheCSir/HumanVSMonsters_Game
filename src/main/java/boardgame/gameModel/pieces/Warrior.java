package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;

public class Warrior extends Human {
    Warrior(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    public void specialAbility(IPiece enemyPiece, IGameManager gm){
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);
        System.out.println("Bash");
    }
}
