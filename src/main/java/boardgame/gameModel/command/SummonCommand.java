package boardgame.gameModel.command;

import boardgame.gameModel.IGameManager;
import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.IPiece;
import java.util.Random;

import boardgame.gameModel.pieces.Minion;
import boardgame.util.Location;
import boardgame.util.Constants;

public class SummonCommand extends SpecialCommand {

    private TurnFacade tf;
    private IGameManager gm;
    private String MinionName;

    @Override
    public void execute() {

        System.out.println("It's Summoning Time!");

        if(!gm.getActivePlayer().getIsAbilityUsed()){
            this.doSummon();
            gm.getActivePlayer().setIsAbilityUsed(true);
            gm.getTurn().nextTurn(gm.getPlayers());
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
    public void setCommand(IGameManager gm, IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece iPiece, IPiece selectedPiece) {
        this.tf = tf;
        this.gm = gm;
    }

    public void setMinionName(String minionName){
        this.MinionName = minionName;
    }

    private void doSummon(){

        Random rand = new Random();

        int x = rand.nextInt(10);
        int y = rand.nextInt(10);

        Location newLocation = new Location(x,y);
        IPiece newPiece = new Minion(newLocation,MinionName);
        gm.addPiece(newPiece);
    }


}
