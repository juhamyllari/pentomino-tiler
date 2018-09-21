package fi.tira.pentominotiler.logic;

import java.util.Arrays;

/**
 * The ArrayPiece class implements a pentomino piece as an array of Block objects.
 * @author juha
 */
public class ArrayPiece {

    private Block[] blocks;
    private int numberOfBlocks;

    /**
     * This constructor is used to create a full (size 5) pentomino piece.
     * @param blocks
     */
    public ArrayPiece(Block[] blocks) {
        this.blocks = blocks;
        this.numberOfBlocks = 5;
    }

    /**
     * This constructor can be used to create partial pieces.
     * @param blocks
     * @param numberOfBlocks
     */
    public ArrayPiece(Block[] blocks, int numberOfBlocks) {
        this.blocks = blocks;
        this.numberOfBlocks = numberOfBlocks;
    }

    /**
     * Getter for the Block array.
     * @return the Block array
     */
    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * Set the Block object array.
     * @param blocks
     */
    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    /**
     * Create a new, translated piece.
     * @param rowOffset
     * @param colOffset
     * @return an ArrayPiece translated by the specified amount
     */
    public ArrayPiece move(int rowOffset, int colOffset) {
        Block[] newBlocks = new Block[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            newBlocks[i] = blocks[i].move(rowOffset, colOffset);
        }
        return new ArrayPiece(newBlocks);
    }

    /**
     * Create a new pieced translated to touch both axes in the first quadrant.
     * @return a new ArrayPiece
     */
    public ArrayPiece align() {
        Block[] newArray = new Block[5];
        return this.move(-this.minRow(), -this.minCol());
    }

    /**
     * Returns the smallest row value of any block in the piece.
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
     * @return min(col value of all blocks)
     */
    private int minCol() {
        int min = Integer.MAX_VALUE;
        for (Block block : blocks) {
            min = Math.min(min, block.getCol());
        }
        return min;
    }

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
     * @return a new ArrayPiece
     */
    public ArrayPiece flipOverX() {
        ArrayPiece aligned = this.align();
        Block[] newBlocks = Arrays.stream(aligned.blocks)
                .map(block -> block.flipOverX())
                .toArray(Block[]::new);
        return new ArrayPiece(newBlocks).align();
    }
    
    /**
     * Rotate the piece 90 degrees counterclockwise and move to touch both axes in the first quadrant.
     * @return a new ArrayPiece
     */
    public ArrayPiece rotate90() {
        ArrayPiece aligned = this.align();
        Block[] newBlocks = Arrays.stream(aligned.blocks)
                .map(block -> block.rotate90())
                .toArray(Block[]::new);
        return new ArrayPiece(newBlocks).align();
    }
    
    public void printPiece() {
        String stringForm = this.toString();
        for (int i = 0; i < stringForm.length(); i++) {
            if (i % 5 == 0) {
                System.out.println("");
            }
            System.out.print(stringForm.charAt(i));
        }
        System.out.println("");
    }

}
