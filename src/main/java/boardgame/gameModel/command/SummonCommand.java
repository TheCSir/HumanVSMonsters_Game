package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.*;
import boardgame.util.Constants;
import boardgame.util.Location;
import boardgame.util.LocationFactory;
import boardgame.view.TileView;

public class SummonCommand extends SpecialCommand {

    private TurnFacade tf;
    private IGameManager gm;
    private String MinionName;
    private Location destination;
    private Minion newPiece;
    private int startingHealth;

    @Override
    public void execute() {

        if(!gm.getActivePlayer().getIsAbilityUsed()){
            this.doSummon();
            gm.getActivePlayer().setIsAbilityUsed(gm.getTurn().getTurnNumber());
            tf.nextTurn();
        }
        else {
            System.out.println("Minions Already summoned!");
        }
    }

    @Override
    public void undo() {

        gm.getActivePlayer().resetIsAbilityUsed();
        tf.goBackOneTurn();

    }

    @Override
    public void redo() {
        newPiece.setHealth(Constants.INITIALMINIONHEALTH);
        gm.getActivePlayer().setIsAbilityUsed(gm.getTurn().getTurnNumber());
        gm.getMonsterPieces().add(newPiece);
        tf.nextTurn();
    }

    @Override
    public void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        this.gm = gm;
        destination = tileView.getLocation();
    }

    public void setMinionName(String minionName){
        this.MinionName = minionName;
    }

    private void doSummon(){

//        newPiece = new Minion(destination, MinionName);
//        startingHealth = newPiece.getHealth();
//        tf.addPiece(newPiece);

        AbstractPieceFactory apf = FactoryProducer.getFactory(gm.getActivePlayer().playerType());
        assert apf != null;
        IPiece temp = apf.getPiece(PieceConstants.MINION, destination);
        newPiece = (Minion) temp;
        newPiece.setPieceName(MinionName);
        startingHealth = newPiece.getHealth();
        gm.getMonsterPieces().add(newPiece);
    }
}
