package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;

/**
 * A Board object represents a pentomino board. The board may be filled
 * partially, fully or not at all.
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
     * Construct a Board object of the specified dimensions with the specified
     * set of symbols for the pieces. Two symbol arrays are provided in the
     * class. The permitted Board dimensions are (3, 20), (4, 15), (5, 12) and
     * (6, 10).
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
                    + " (" + rows + ", " + cols + ").");
        }
        this.array = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.used = new boolean[12];
        this.unused = 12;
        this.symbols = symbols;
    }

    /**
     * Return true if the piece can be placed in the specified location.
     *
     * @param piece
     * @param row
     * @param col
     * @return true if the piece can be placed
     */
    public boolean canPlace(Piece piece, int row, int col) {
        for (Block block : piece.getBlocks()) {
            int effectiveRow = row + block.getRow();
            int effectiveCol = col + block.getCol();
            if (effectiveRow < 0
                    || effectiveRow >= rows
                    || effectiveCol < 0
                    || effectiveCol >= cols
                    || array[effectiveRow][effectiveCol] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return a new Board with the piece placed in the specified location. The
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

    /**
     * Copy the internal array of a Board object. The array represents each
     * square of the board as a char. Each square contains either the symbol
     * (e.g. 'X') of the piece placed there or 0 if it is empty.
     *
     * @param b the board
     * @return a copy of the array
     */
    private static char[][] copyBoardArray(Board b) {
        char[][] newArray = new char[b.rows][b.cols];
        for (int i = 0; i < b.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                newArray[i][j] = b.array[i][j];
            }
        }
        return newArray;
    }

    /**
     * Return a copy of a board array flipped over the X (columns) axis.
     *
     * @param array the original array
     * @return a flipped copy of the array
     */
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

    /**
     * Return a copy of a board array flipped over the Y (rows) axis.
     *
     * @param array the original array
     * @return a flipped copy of the array
     */
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

    /**
     * Convert a board array to a String representation.
     * See toString for specifications.
     * 
     * @param array the array
     * @return a String representation
     */
    private static String arrayToString(char[][] array) {
        char[] chars = new char[60];
        int i = 0;
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                char c = array[row][col] == 0 ? '0' : array[row][col];
                chars[i] = c;
                i++;
            }
        }
        return new String(chars);
    }

    /**
     * Return a String representation of the Board. Blocks are represented with
     * the symbol of the corresponding piece. Empty squares are shown as '0'.
     *
     * @return a String representation
     */
    @Override
    public String toString() {
        return arrayToString(array);
    }

    /**
     * Return a list containing String representations of the Board in each of
     * its three alternative orientations. The original (not mirrored or
     * rotated) version is always unique in the search and is therefore omitted.
     *
     * @return mirrored and rotated versions of the board
     */
    public MyArrayList<String> symmetryStrings() {
        MyArrayList<String> orientations = new MyArrayList<>(3);
        char[][] flippedOverX = flipArrayOverX(array);
        orientations.add(arrayToString(flippedOverX));
        orientations.add(arrayToString(flipArrayOverY(array)));
        orientations.add(arrayToString(flipArrayOverY(flippedOverX)));
        return orientations;
    }

    /**
     * Return the number of pieces not yet placed on the board.
     *
     * @return number of unused pieces
     */
    public int getUnused() {
        return unused;
    }

    /**
     * Return true if the piece has already been placed. The piece is specified
     * by its symbol index (0-11).
     *
     * @param index
     * @return true if piece has been placed
     */
    public boolean isUsed(int index) {
        return used[index];
    }

    /**
     * Get the number of columns on the board.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Get the number of rows on the board.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Return the contents of the board square specified by a linear index.
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
