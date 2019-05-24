package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import boardgame.gameModel.pieces.Minion;
import boardgame.util.Location;
import boardgame.view.TileView;

public class SummonCommand extends SpecialCommand {

    private TurnFacade tf;
    private IGameManager gm;
    private String MinionName;
    private Location destination;


    @Override
    public void execute() {

        System.out.println("It's Summoning Time!");

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

    }

    @Override
    public void redo() {

    }

    @Override
    public void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece iPiece, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        this.gm = gm;
        destination = tileView.getLocation();
    }

    public void setMinionName(String minionName){
        this.MinionName = minionName;
    }

    private void doSummon(){

        IPiece newPiece = new Minion(destination, MinionName);
        tf.addPiece(newPiece);
    }
}
