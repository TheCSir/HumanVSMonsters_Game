package boardgame.gameModel.tiles;

import static java.lang.Math.*;
import static java.lang.Math.sin;

public class HexagonalTile extends Tile{

    private final int numSides = 6;

    public HexagonalTile(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.sides = this.getHexagonSides();

        for (double p : this.sides) {
            this.getPoints().add(p);
        }
    }

    private double[] getHexagonSides() {
        int graphicsHeight = this.size * 2;
        double graphicsWidth = sqrt(3) / 2 * graphicsHeight;
        int xxAxis = (int) (graphicsWidth * (double) xAxis + 0.5 * graphicsWidth * (double) yAxis);
        int yyAxis = (int) (3.0 / 4.0 * graphicsHeight * yAxis);

        double hexSides[] = new double[numSides * 2];
        double angle;
        for (int i = 0; i < numSides; i++) {
            angle = 2 * PI / numSides * (i + 0.5);
            hexSides[(i * 2)] = (xxAxis + size * cos(angle));
            hexSides[(i * 2 + 1)] = (yyAxis + size * sin(angle));
        }
        return hexSides;
    }
}
