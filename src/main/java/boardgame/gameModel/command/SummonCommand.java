package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Minion;
import boardgame.util.Constants;
import boardgame.util.Location;
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
            gm.getActivePlayer().setIsAbilityUsed(true);
            tf.nextTurn();
        }
        else {
            System.out.println("Minions Already summoned!");
        }
    }

    @Override
    public void undo() {

        gm.getActivePlayer().setIsAbilityUsed(false);
        gm.removePiece(newPiece);
        tf.goBackOneTurn();

    }

    @Override
    public void redo() {
        newPiece.setHealth(Constants.INITIALMINIONHEALTH);
        gm.getActivePlayer().setIsAbilityUsed(true);
        gm.addPiece(newPiece);
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

        newPiece = new Minion(destination, MinionName);
        startingHealth = newPiece.getHealth();
        tf.addPiece(newPiece);
    }
}
