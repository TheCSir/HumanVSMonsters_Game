package boardgame.gameModel;


import boardgame.gameModel.pieces.*;
import boardgame.gameModel.state.command.HealCommand;
import boardgame.gameModel.state.command.SpecialCommand;
import boardgame.gameModel.state.states;


public class PieceVisitor implements SpecialVisitor {

    private states state;
    private SpecialCommand specialCommand;

    @Override
    public void visit(Priest piece) {
        System.out.println("You've triggered a: " + piece.getPieceClass());
        state = states.HEALSTATE;
        HealCommand h = new HealCommand();
        h.setHealValue(3);
        specialCommand = h;
    }

    @Override
    public void visit(Warrior piece) {
        System.out.println("You've triggered a: " + piece.getPieceClass());
        state = states.BASH;
    }

    @Override
    public void visit(Archer piece) {
        System.out.println("You've triggered a: " + piece.getPieceClass());
    }

    @Override
    public void visit(Griffin piece) {
        System.out.println("You've triggered a: " + piece.getPieceClass());
    }

    @Override
    public void visit(Medusa piece) {
        System.out.println("You've triggered a: " + piece.getPieceClass());
    }

    @Override
    public void visit(Minotaur piece) {
        System.out.println("You've triggered a: " + piece.getPieceClass());
    }

    @Override
    public SpecialCommand getCommand() {
        return specialCommand;
    }


    public states getState() {
        return state;
    }
}
