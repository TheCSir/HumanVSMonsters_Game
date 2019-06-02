package boardgame.gameModel.command;

import boardgame.gameModel.SpecialVisitor;
import boardgame.gameModel.TurnFacade;
import boardgame.gameModel.pieces.*;
import boardgame.gameModel.players.IPlayer;
import boardgame.util.Constants;
import boardgame.util.Location;
import boardgame.view.TileView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SummonCommand extends SpecialCommand {

    private TurnFacade tf;
    private String MinionName;
    private Location destination;
    private Minion newPiece;
    private double startingHealth;

    @Override
    public void execute() {
        IPlayer activePlayer = tf.getActivePlayer();

        // Check if special ability is already used for player
        if (!activePlayer.getIsAbilityUsed()) {
            // do the summon
            this.doSummon();
            // set ability used parameter
            activePlayer.setIsAbilityUsed(tf.getTurnNumber());
            // end turn
            tf.nextTurn();
        }
        else {
            System.out.println("Minions Already summoned!");
        }
    }

    @Override
    public void undo() {
        tf.removePiece(newPiece);
        tf.getActivePlayer().resetIsAbilityUsed();
        tf.goBackOneTurn();
    }

    @Override
    public void redo() {
        newPiece.setHealth(Constants.INITIALMINIONHEALTH);
        tf.addPiece(newPiece);
        tf.getActivePlayer().setIsAbilityUsed(tf.getTurnNumber());
        tf.nextTurn();
    }

    @Override
    public void setCommand(IPiece piece, SpecialVisitor sv, TurnFacade tf, IPiece selectedPiece, TileView tileView) {
        this.tf = tf;
        // place of new minion
        destination = tileView.getLocation();
    }

    public void setMinionName(String minionName){
        this.MinionName = minionName;
    }

    private void doSummon(){

        // Create abstract factory instance to crete a piece
        AbstractPieceFactory apf = FactoryProducer.getFactory(tf.getActivePlayer().playerType());
        // check is null
        assert apf != null;
        // create a piece instance
        IPiece temp = apf.getPiece(PieceConstants.MINION, destination);
        // cast Ipiece to minions class
        newPiece = (Minion) temp;
        newPiece.healthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() <= 0) {
                    tf.removePiece(newPiece);
                    tf.resetAbilityUsed();
                }
                if (oldValue.doubleValue() <= 0 && newValue.doubleValue() > 0) {
                    tf.addPiece(newPiece);
                    tf.resetAbilityUsed();
                }
            }
        });
        // set  piece name
        newPiece.setPieceName(MinionName);
        // set piece health points
        startingHealth = newPiece.getHealth();
        // add piece to game
        tf.addPiece(newPiece);
    }
}
