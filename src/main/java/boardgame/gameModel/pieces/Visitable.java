package boardgame.gameModel.pieces;

import boardgame.gameModel.SpecialVisitor;

public interface Visitable {

    void accept(SpecialVisitor v);
}
