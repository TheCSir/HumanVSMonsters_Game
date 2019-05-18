package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;

public class Warrior extends Human {
    Warrior(int moveSpeed, Location location, String abilityType) {
        super(moveSpeed, location, abilityType);
    }

    public void basicAttack(){}

    public void specialAbilityAttack(IPiece enemyPiece, IGameManager gm){
        gm.getAttackedPlayer(enemyPiece).decreaseHealthProperty(enemyPiece);
        System.out.println("Bash");
    }

    public void specialAbilityHeal(IGameManager gm){}
}
