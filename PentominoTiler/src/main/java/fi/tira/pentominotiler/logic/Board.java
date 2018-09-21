package fi.tira.pentominotiler.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juha
 */
public class Board {

    public static final char[] HEX_SYMBOLS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C'};
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

    public Board(int rows, int cols, char[] symbols) {
        this.array = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.used = new boolean[12];
        this.unused = 12;
        this.symbols = symbols;
    }

    public void printBoard() {
        printArray(array, rows, cols);
    }

    public static void printArray(char[][] array, int rows, int cols) {
        /* Temporarily public for testing purposes. Will be made private or moved into
        the printBoard method.*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = array[i][j] == 0 ? '0' : array[i][j];
                System.out.print(c);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public boolean canPlace(ArrayPiece piece, int row, int col) {
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

    public Board placePiece(ArrayPiece piece, int row, int col, int symbolIndex) {
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

    public String toString() {
        return arrayToString(array);
    }

    public List<String> symmetryStrings() {
        List<String> symmetries = new ArrayList<>();
        char[][] flippedOverX = flipArrayOverX(array);
        symmetries.add(arrayToString(array));
        symmetries.add(arrayToString(flippedOverX));
        symmetries.add(arrayToString(flipArrayOverY(array)));
        symmetries.add(arrayToString(flipArrayOverY(flippedOverX)));
        return symmetries;
    }

    public int getUnused() {
        return unused;
    }

    public boolean isUsed(int index) {
        return used[index];
    }

    public int getNextIndex() {
        return findNextIndex(0);
    }

    private int findNextIndex(int start) {
        for (int i = start; i < rows * cols; i++) {
            if (array[i / cols][i % cols] == 0) {
                return i;
            }
        }
        return -1;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

}
