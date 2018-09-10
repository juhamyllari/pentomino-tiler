package fi.tira.pentominotiler.logic;

public class Block {
    
    private int row;
    private int col;

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
    
    public Block move(int rowOffset, int colOffset) {
        return new Block(row + rowOffset, col + colOffset);
    }
    
    public Block flipOverX() {
        return new Block(-row, col);
    }
    
    public Block flipOverY() {
        return new Block(row, -col);
    }
    
    public Block rotate90() {
        return new Block(col, -row);
    }
    
}
