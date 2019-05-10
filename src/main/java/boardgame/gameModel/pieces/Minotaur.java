package boardgame.gameModel.pieces;


import boardgame.gameModel.IGameManager;
import boardgame.util.Location;

public class Minotaur extends Monster {
    Minotaur(int moveSpeed, Location location) {
        super(moveSpeed, location);
    }

    public void basicAttack(){}

    @Override
    public void specialAbility(IPiece enemyPiece, IGameManager gm){
        System.out.println("Summoning Bulls!");
    }
}
