package boardgame.gameModel.pieces;

import boardgame.util.Location;

public abstract class Human extends Piece {

    public Human(int moveSpeed, Location location, String abilityType) { super(moveSpeed, location, abilityType); }
}
