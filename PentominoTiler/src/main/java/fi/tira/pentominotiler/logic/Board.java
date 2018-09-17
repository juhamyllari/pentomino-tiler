package fi.tira.pentominotiler.logic;

/**
 *
 * @author juha
 */
public class Board {

    public static final char[] HEX_SYMBOLS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C'};
    public static final char[] LETTER_SYMBOLS = {'X', 'I', 'T', 'V', 'W', 'Z', 'U', 'P', 'N', 'L', 'F', 'Y'};

    private char[] board;
    private int rows;
    private int cols;
    private boolean[] used;
    private char[] symbols;

    public Board(char[] board, int rows, boolean[] used, char[] symbols) {
        this.board = board;
        this.rows = rows;
        this.cols = board.length / rows;
        this.used = used;
    }

    public Board(int rows, int cols, char[] symbols) {
        this.board = new char[rows * cols];
        this.rows = rows;
        this.cols = cols;
        this.used = new boolean[12];
        this.symbols = symbols;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            if (i % cols == 0) {
                System.out.println("");
            }
            System.out.print(board[i]);
        }
        System.out.println("");
    }

    public boolean canPlace(ArrayPiece piece, int row, int col) {
        boolean allowed = true;
        for (Block block : piece.getBlocks()) {
            int idx = (block.getRow() + row) * cols + (block.getCol() + col);
            if (idx < 0 || idx >= board.length || board[idx] != 0) {
                allowed = false;
                break;
            }
        }
        return allowed;
    }

    public Board placePiece(ArrayPiece piece, int row, int col, int symbolIndex) {
        char[] newBoard = new char[rows * cols];
        for (int i = 0; i < board.length; i++) {
            newBoard[i] = board[i];
        }
        for (Block block : piece.getBlocks()) {
            int idx = (block.getRow() + row) * cols + (block.getCol() + col);
            board[idx] = symbols[symbolIndex];
        }
        boolean[] newUsed = new boolean[12];
        for (int i = 0; i < 12; i++) {
            newUsed[i] = used[i];
        }
        newUsed[symbolIndex] = true;
        return new Board(board, rows, newUsed, symbols);
    }

}
