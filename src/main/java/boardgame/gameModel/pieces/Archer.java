package boardgame.gameModel.pieces;

import boardgame.gameModel.IGameManager;
import boardgame.util.Location;

public class Archer extends Human {
    Archer(int moveSpeed, Location location, String abilityType) {
        super(moveSpeed, location, abilityType);
    }

    public void basicAttack(){}

    public void specialAbilityHeal(IGameManager gm){}

    public void specialAbilityAttack(IPiece enemyPiece, IGameManager gm) {
        System.out.println("Ranged Attack!");
    }
}
