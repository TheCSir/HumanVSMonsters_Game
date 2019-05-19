package boardgame.gameModel.pieces;


import boardgame.gameModel.SpecialVisitor;
import boardgame.util.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minotaur extends Monster implements IPiece {

    private int moveSpeed = 3;
    private int attack = 2;
    private final StringProperty pieceName = new SimpleStringProperty("Minotaur");


    public Minotaur(Location location) {
        super(location);
    }

    @Override
    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    @Override
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void specialAbility(){
        System.out.println("Summoning Bulls!");
    }

    @Override
    public String getPieceClass() {
        return PieceConstants.MELEE;
    }

    @Override
    public StringProperty getPieceName() {
        return pieceName;
    }

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public void accept(SpecialVisitor v) {
        v.visit(this);
    }

    @Override
    public String getSpecialAblilityDescription() {
        return "Summon Bulls";
    }
}
