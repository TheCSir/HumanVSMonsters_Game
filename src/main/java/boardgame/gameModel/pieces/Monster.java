package boardgame.gameModel.pieces;

import boardgame.util.Location;

public abstract class Monster extends Piece {

    public Monster(int moveSpeed, Location location, String abilityType) { super(moveSpeed, location, abilityType); }

}
