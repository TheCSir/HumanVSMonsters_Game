package boardgame.gameModel;

import boardgame.gameModel.pieces.*;
import boardgame.gameModel.state.command.SpecialCommand;

public interface SpecialVisitor {

    //Human visitors
    void visit(Priest piece);

    void visit(Warrior piece);

    void visit(Archer piece);

    void visit(Griffin piece);

    void visit(Medusa piece);

    void visit(Minotaur piece);

    SpecialCommand getCommand();
}
