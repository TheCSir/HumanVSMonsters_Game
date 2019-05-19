package boardgame.gameModel;


import boardgame.gameModel.pieces.*;
import boardgame.gameModel.state.HighlightTilesVisitor;
import boardgame.gameModel.state.command.HealCommand;
import boardgame.gameModel.state.command.RangedAttackCommand;
import boardgame.gameModel.state.command.SpecialAttackCommand;
import boardgame.gameModel.state.command.SpecialCommand;
import boardgame.gameModel.state.states;


public class PieceVisitor implements SpecialVisitor {

    private states state;
    private SpecialCommand specialCommand;

    private HighlightTilesVisitor hv = new HighlightTilesVisitor();

    @Override
    public HighlightTilesVisitor getHv() {
        return hv;
    }

    @Override
    public void visit(Priest priest) {
        System.out.println("You've triggered a: " + priest.getPieceClass());
        state = states.HEALSTATE;

        HealCommand h = new HealCommand();
        h.setHealValue(priest.getHealValue());
        specialCommand = h;
    }

    @Override
    public void visit(Warrior warrior) {
        System.out.println("You've triggered a: " + warrior.getPieceClass());
        state = states.SPECIALATTACKSTATE;

        hv.setHighlightDistance(1);
        SpecialAttackCommand specialAttackCommand = new SpecialAttackCommand();
        specialAttackCommand.setSpecialAttackMultiplier(warrior.getSpecialAttackMultiplier());
        specialCommand = specialAttackCommand;
    }

    @Override
    public void visit(Archer archer) {
        System.out.println("You've triggered a: " + archer.getPieceClass());
        state = states.RANGEDATTACK;

        hv.setHighlightDistance(archer.getRangedDistance());
        RangedAttackCommand rangedAttackCommand = new RangedAttackCommand();
        rangedAttackCommand.setRangedAttackValue(archer.getRangedAttackValue());
        specialCommand = rangedAttackCommand;
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


    @Override
    public states getState() {
        return state;
    }
}
