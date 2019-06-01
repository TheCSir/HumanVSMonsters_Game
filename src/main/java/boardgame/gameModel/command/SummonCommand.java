package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.Constants;
import boardgame.util.Location;
import boardgame.view.TileView;

public class SummonCommand extends SpecialCommand {

    private TurnFacade tf;
    private String MinionName;
    private Location destination;
    private Minion newPiece;
    private int startingHealth;

    @Override
    public void execute() {
        IPlayer activePlayer = tf.getActivePlayer();

        if (!activePlayer.getIsAbilityUsed()) {
            this.doSummon();
            activePlayer.setIsAbilityUsed(tf.getTurnNumber());
            tf.nextTurn();
        }
        else {
            System.out.println("Minions Already summoned!");
        }
    }

    @Override
    public void undo() {
        tf.getActivePlayer().resetIsAbilityUsed();
        tf.goBackOneTurn();
    }

    @Override
    public void redo() {
        newPiece.setHealth(Constants.INITIALMINIONHEALTH);
        tf.getActivePlayer().setIsAbilityUsed(tf.getTurnNumber());
        tf.nextTurn();
    }

    @Override
    public void setCommand(IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        destination = tileView.getLocation();
    }

    public void setMinionName(String minionName){
        this.MinionName = minionName;
    }

    private void doSummon(){

        AbstractPieceFactory apf = FactoryProducer.getFactory(tf.getActivePlayer().playerType());
        assert apf != null;
        IPiece temp = apf.getPiece(PieceConstants.MINION, destination);
        newPiece = (Minion) temp;
        newPiece.setPieceName(MinionName);
        startingHealth = newPiece.getHealth();
        tf.addPiece(newPiece);
    }
}
