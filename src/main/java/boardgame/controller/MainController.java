package boardgame.controller;

/*
 Controller class for main screen.
 */

import boardgame.view.HexagonTile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //TODO Move all these variables out of main controller class so they can be dynamically assigned.
    private final static double r = 40; // the inner radius from hexagon center to outer corner
    private final static double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
    private final static double TILE_HEIGHT = 2 * r;
    private final static double TILE_WIDTH = 2 * n;

    @FXML // fx:id="turnTime"
    private Text turnTime; // Value injected by FXMLLoader

    @FXML
    private Pane boardPane;

    @FXML
    private VBox tileInfoPane;

    double time = 60;

    //TODO move event.

    public MainController () {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turnTime.setText("Turn Time " + time);

        drawBasicGrid(10, 10, r);

    }


    //TODO refactor to separate class responsible for drawing grid and return AnchorPane.
    //TODO Add static map to start.
    private void drawBasicGrid(int rows, int columns, double radius) {
        double xStartOffset = 40;
        double yStartOffset = 40;


        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                double xCoord = x * TILE_WIDTH + (y % 2) * n + xStartOffset;
                double yCoord = y * TILE_HEIGHT * 0.75 + yStartOffset;

                HexagonTile tile = new HexagonTile(xCoord, yCoord, radius);
                boardPane.getChildren().add(tile);
                tile.setOnMouseClicked(e -> handleTileClicked(tile));
                if (x==2 && y==2) {

                    HexagonTile pieceTile = new HexagonTile(xCoord, yCoord, radius);
                    try {
                        pieceTile.setImagePattern("src/main/resources/bigBird.PNG");
                    }catch (FileNotFoundException e) {
                        System.out.println("Image File not found!");
                    }
                    pieceTile.setFill(Color.BLUE);
                    boardPane.getChildren().add(pieceTile);
                    System.out.println(pieceTile.getTranslateX());
                    pieceTile.setOnMouseClicked(e -> handlePieceClicked(pieceTile));
                }

            }
        }
    }

    //private addPieces

    private HexagonTile selectedTile = null;

    private boolean tileSelected = false;

    private void handlePieceClicked(HexagonTile tile){
        System.out.println("you clicked a piece!");
        this.selectedTile = tile;
        this.tileSelected = true;
        System.out.println(tile.getLayoutX());
    }

    private void handleTileClicked(HexagonTile tile) {
        if(selectedTile !=null && tileSelected){

            Translate translate = new Translate();
            translate.setX(tile.getBoundsInParent().getCenterX()- selectedTile.getBoundsInParent().getCenterX());
            translate.setY(tile.getBoundsInParent().getCenterY() - selectedTile.getBoundsInParent().getCenterY());
            selectedTile.getTransforms().addAll(translate);
            tile.setFill(Color.GREEN);
            System.out.println(selectedTile.getBoundsInParent().getCenterX());

            //Bring piece to front so that it doesn't gte stuck behind background tile.
            selectedTile.toFront();

            selectedTile=null;
            tileSelected=false;
        }
    }

    private void changeTilePosition(HexagonTile piece) {

    }




}
