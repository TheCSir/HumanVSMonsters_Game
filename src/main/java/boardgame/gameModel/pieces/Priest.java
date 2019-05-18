package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;


public class Priest extends Human {
    Priest(int moveSpeed, Location location, String abilityType) {
        super(moveSpeed, location, abilityType);
    }

    public void basicAttack(){}
    public void specialAbilityAttack(IPiece enemyPiece, IGameManager gm){}

    public void specialAbilityHeal(IGameManager gm){
        gm.getActivePlayer().increaseHealthProperty(3);
        System.out.println("Healing!");
    }
}
