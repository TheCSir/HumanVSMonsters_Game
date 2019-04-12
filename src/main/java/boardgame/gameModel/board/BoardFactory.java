package boardgame.gameModel.board;

public class BoardFactory {

    public static IBoard createBoard(String boardType, int rows, int columns) {
        if (boardType.equals(Board2DHex.class.getName())) {
            return new Board2DHex(rows, columns);
        }
        return null;
    }
}
