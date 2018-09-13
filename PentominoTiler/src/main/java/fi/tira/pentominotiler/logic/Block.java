package fi.tira.pentominotiler.logic;

/**
 * A Block object represents one of the five blocks (squares) which make up a pentomino piece.
 * @author juha
 */
public class Block {
    
    private int row;
    private int col;

    /**
     * Create a Block object by specifying its row and column coordinates.
     * @param row
     * @param col
     */
    public Block(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    /**
     * Translate block by (rowOffset, colOffset)
     * @param rowOffset
     * @param colOffset
     * @return a new Block
     */
    public Block move(int rowOffset, int colOffset) {
        return new Block(row + rowOffset, col + colOffset);
    }
    
    /**
     * Flip over the x axis.
     * @return a new Block
     */
    public Block flipOverX() {
        return new Block(-row, col);
    }
    
    /**
     * Rotate 90 degrees counterclockwise.
     * @return a new Block
     */
    public Block rotate90() {
        return new Block(col, -row);
    }
    
}
