package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import java.util.List;

/**
 * A Board object represents a pentomino board.
 * 
 * @author juha
 */
public class Board {

    /**
     * Hexadecimal symbols (1 to C) for the 12 pentomino pieces.
     */
    public static final char[] HEX_SYMBOLS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C'};

    /**
     * Traditional capital letter symbols for the 12 pentomino pieces.
     */
    public static final char[] LETTER_SYMBOLS = {'X', 'I', 'T', 'V', 'W', 'Z', 'U', 'P', 'N', 'L', 'F', 'Y'};

    private char[][] array;
    private int rows;
    private int cols;
    private boolean[] used;
    private char[] symbols;
    private int unused;

    private Board(char[][] array, boolean[] used, int unused, char[] symbols) {
        this.array = array;
        this.rows = array.length;
        this.cols = array[0].length;
        this.used = used;
        this.unused = unused;
        this.symbols = symbols;
    }

    /**
     * Constructs a Board object of the specified dimensions with the specified
     * set of symbols for the pieces. Two symbol arrays are provided in the
     * class. The permitted Board dimensions are (3, 20), (4, 15), (5, 12)
     * and (6, 10).
     * 
     * @param rows
     * @param cols
     * @param symbols
     */
    public Board(int rows, int cols, char[] symbols) {
        if (rows * cols != 60
                || rows > cols
                || rows < 3) {
            throw new IllegalArgumentException("Invalid board dimensions"
                    + " (" + rows + ", " + cols + ")." );
        }
        this.array = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.used = new boolean[12];
        this.unused = 12;
        this.symbols = symbols;
    }

    /**
     * Prints the Board. Blocks are represented with the symbol of the
     * corresponding piece. Empty squares are shown as '0'.
     */
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = array[i][j] == 0 ? '0' : array[i][j];
                System.out.print(c);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Returns true if the piece can be placed in the specified location.
     *
     * @param piece
     * @param row
     * @param col
     * @return true if the piece can be placed
     */
    public boolean canPlace(Piece piece, int row, int col) {
        boolean allowed = true;
        for (Block block : piece.getBlocks()) {
            int effectiveRow = row + block.getRow();
            int effectiveCol = col + block.getCol();
            if (effectiveRow < 0
                    || effectiveRow >= rows
                    || effectiveCol < 0
                    || effectiveCol >= cols
                    || array[effectiveRow][effectiveCol] != 0) {
                allowed = false;
                break;
            }
        }
        return allowed;
    }

    /**
     * Returns a new Board with the piece placed in the specified location. The
     * symbol of the piece is symbols[symbolIndex].
     *
     * @param piece
     * @param row
     * @param col
     * @param symbolIndex
     * @return a board with the new piece
     */
    public Board placePiece(Piece piece, int row, int col, int symbolIndex) {
        char[][] newArray = copyBoardArray(this);
        for (Block block : piece.getBlocks()) {
            int effectiveRow = row + block.getRow();
            int effectiveCol = col + block.getCol();
            newArray[effectiveRow][effectiveCol] = symbols[symbolIndex];
        }
        boolean[] newUsed = new boolean[12];
        for (int i = 0; i < 12; i++) {
            newUsed[i] = used[i];
        }
        newUsed[symbolIndex] = true;
        return new Board(newArray, newUsed, unused - 1, symbols);
    }

    private static char[][] copyBoardArray(Board b) {
        char[][] newArray = new char[b.rows][b.cols];
        for (int i = 0; i < b.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                newArray[i][j] = b.array[i][j];
            }
        }
        return newArray;
    }

    private static char[][] flipArrayOverX(char[][] array) {
        int nrows = array.length;
        int ncols = array[0].length;
        char[][] newArray = new char[nrows][ncols];
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                newArray[nrows - i - 1][j] = array[i][j];
            }
        }
        return newArray;
    }

    private static char[][] flipArrayOverY(char[][] array) {
        int nrows = array.length;
        int ncols = array[0].length;
        char[][] newArray = new char[nrows][ncols];
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                newArray[i][ncols - j - 1] = array[i][j];
            }
        }
        return newArray;
    }

    private static String arrayToString(char[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                char c = array[row][col] == 0 ? '0' : array[row][col];
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Returns a String representation of the Board. Blocks are represented with
     * the symbol of the corresponding piece. Empty squares are shown as '0'.
     *
     * @return a String representation
     */
    public String toString() {
        return arrayToString(array);
    }

    /**
     * Returns a list containing String representations of the Board in each of
     * its three alternative symmetries. The original (not mirrored or rotated)
     * version is always unique in the search and is therefore omitted.
     *
     * @return mirrored and rotated versions of the board
     */
    public List<String> symmetryStrings() {
        List<String> symmetries = new MyArrayList<>(3);
        char[][] flippedOverX = flipArrayOverX(array);
        symmetries.add(arrayToString(flippedOverX));
        symmetries.add(arrayToString(flipArrayOverY(array)));
        symmetries.add(arrayToString(flipArrayOverY(flippedOverX)));
        return symmetries;
    }

    /**
     * Returns the number of pieces not yet placed on the board.
     *
     * @return number of unused pieces
     */
    public int getUnused() {
        return unused;
    }

    /**
     * Returns true if the piece has already been placed. The piece is specified
     * by its symbol index (0-11).
     *
     * @param index
     * @return true if piece has been placed
     */
    public boolean isUsed(int index) {
        return used[index];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    /**
     * Returns the contents of the board square specified by a linear index.
     *
     * @param index
     * @return array[index / ncols][index % ncols]
     */
    public char charAtLinearIndex(int index) {
        int row = index / cols;
        int col = index % cols;
        return array[row][col];
    }

}
