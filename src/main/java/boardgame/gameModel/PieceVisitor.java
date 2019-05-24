package boardgame.gameModel;


import boardgame.gameModel.command.*;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.state.HighlightTilesVisitor;
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
        state = states.HEALSTATE;

        HealCommand h = new HealCommand();
        h.setHealValue(priest.getHealValue());
        specialCommand = h;
    }

    @Override
    public void visit(Warrior warrior) {
        state = states.SPECIALATTACKSTATE;

        hv.setHighlightDistance(1);
        SpecialAttackCommand specialAttackCommand = new SpecialAttackCommand();
        specialAttackCommand.setSpecialAttackMultiplier(warrior.getSpecialAttackMultiplier());
        specialCommand = specialAttackCommand;
    }

    @Override
    public void visit(Archer archer) {
        state = states.RANGEDATTACK;

        hv.setHighlightDistance(archer.getRangedDistance());
        RangedAttackCommand rangedAttackCommand = new RangedAttackCommand();
        rangedAttackCommand.setRangedAttackValue(archer.getRangedAttackValue());
        specialCommand = rangedAttackCommand;
    }

    @Override
    public void visit(Griffin piece) {
        state = states.SUMMON;

        SummonCommand summon = new SummonCommand();
        summon.setMinionName("Hawks");
        specialCommand = summon;
    }

    @Override
    public void visit(Medusa piece) {

        state = states.SUMMON;

        SummonCommand summon = new SummonCommand();
        summon.setMinionName("Snakes");
        specialCommand = summon;
    }

    @Override
    public void visit(Minotaur piece) {

        state = states.SUMMON;

        SummonCommand summon = new SummonCommand();
        summon.setMinionName("Bulls");
        specialCommand = summon;
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
