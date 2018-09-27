package fi.tira.pentominotiler.logic;

import java.util.Arrays;

/**
 * The Piece class implements a pentomino piece as an array of Block objects.
 * 
 * @author juha
 */
public class Piece {

    private Block[] blocks;
    private int numberOfBlocks;

    /**
     * This constructor is used to create a full (size 5) pentomino piece.
     * 
     * @param blocks
     */
    public Piece(Block[] blocks) {
        this.blocks = blocks;
        this.numberOfBlocks = 5;
    }

    /**
     * This constructor can be used to create partial pieces.
     * 
     * @param blocks
     * @param numberOfBlocks
     */
    public Piece(Block[] blocks, int numberOfBlocks) {
        this.blocks = blocks;
        this.numberOfBlocks = numberOfBlocks;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * Create a new, translated piece.
     * 
     * @param rowOffset
     * @param colOffset
     * @return a piece translated by the specified amount
     */
    public Piece move(int rowOffset, int colOffset) {
        Block[] newBlocks = new Block[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            newBlocks[i] = blocks[i].move(rowOffset, colOffset);
        }
        return new Piece(newBlocks);
    }

    /**
     * Create a new pieced translated to touch both axes in the first quadrant.
     * 
     * @return a new piece aligned with both axes
     */
    public Piece align() {
        return this.move(-this.minRow(), -this.minCol());
    }

    /**
     * Returns the smallest row value of any block in the piece.
     * 
     * @return min(row values of all blocks)
     */
    private int minRow() {
        int min = Integer.MAX_VALUE;
        for (Block block : blocks) {
            min = Math.min(min, block.getRow());
        }
        return min;
    }

    /**
     * Returns the smallest column value of any block in the piece.
     * 
     * @return min(col value of all blocks)
     */
    private int minCol() {
        int min = Integer.MAX_VALUE;
        for (Block block : blocks) {
            min = Math.min(min, block.getCol());
        }
        return min;
    }

    /**
     * Convert the Piece to a String.
     * The String representation is only meaningful if the whole piece is
     * contained in the first five rows and columns of the first quadrant.
     * 
     * @return the piece as a String
     */
    @Override
    public String toString() {
        char[] chars = new char[25];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '0';
        }
        for (Block block : blocks) {
            int row = block.getRow();
            int col = block.getCol();
            chars[row * 5 + col] = '#';
        }
        return new String(chars);
    }

    /**
     * Flip piece over the x axis and move to touch both axes in the first quadrant.
     * 
     * @return a new Piece
     */
    public Piece flipOverX() {
        Piece aligned = this.align();
        Block[] newBlocks = Arrays.stream(aligned.blocks)
                .map(block -> block.flipOverX())
                .toArray(Block[]::new);
        return new Piece(newBlocks).align();
    }
    
    /**
     * Rotate the piece 90 degrees counterclockwise and move to touch both axes in the first quadrant.
     * 
     * @return a new Piece
     */
    public Piece rotate90() {
        Piece aligned = this.align();
        Block[] newBlocks = Arrays.stream(aligned.blocks)
                .map(block -> block.rotate90())
                .toArray(Block[]::new);
        return new Piece(newBlocks).align();
    }

}
