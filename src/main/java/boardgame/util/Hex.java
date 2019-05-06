package boardgame.util;

/**
 * The type Hex.
 */
public class Hex {
    /**
     * The Col.
     */
    private int col; //x
    /**
     * The Row.
     */
    private int row; //y

    /**
     * Instantiates a new Hex.
     *
     * @param col the col
     * @param row the row
     */
    Hex(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Hex{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
