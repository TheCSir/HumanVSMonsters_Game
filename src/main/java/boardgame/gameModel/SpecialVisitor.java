package boardgame.gameModel;

import boardgame.gameModel.pieces.*;
import boardgame.gameModel.state.HighlightTilesVisitor;
import boardgame.gameModel.state.command.SpecialCommand;
import boardgame.gameModel.state.states;

public interface SpecialVisitor {

    HighlightTilesVisitor getHv();

    //Human visitors
    void visit(Priest piece);

    void visit(Warrior piece);

    void visit(Archer piece);

    void visit(Griffin piece);

    void visit(Medusa piece);

    void visit(Minotaur piece);

    SpecialCommand getCommand();

    states getState();
}
