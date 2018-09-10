package fi.tira.pentominotiler.logic;

import java.util.Arrays;

public class ArrayPiece implements OrientedPiece {

    private Block[] blocks;
    private int numberOfBlocks;

    public ArrayPiece(Block[] blocks) {
        this.blocks = blocks;
        this.numberOfBlocks = 5;
    }

    public ArrayPiece(Block[] blocks, int numberOfBlocks) {
        // This constructor can be used to create partial pieces.
        this.blocks = blocks;
        this.numberOfBlocks = numberOfBlocks;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public ArrayPiece move(int rowOffset, int colOffset) {
        Block[] newBlocks = new Block[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            newBlocks[i] = blocks[i].move(rowOffset, colOffset);
        }
        return new ArrayPiece(newBlocks);
    }

    public ArrayPiece touchAxes() {
        Block[] newArray = new Block[5];
        return this.move(-this.minRow(), -this.minCol());
    }

    public int minRow() {
        int min = Integer.MAX_VALUE;
        for (Block block : blocks) {
            min = Math.min(min, block.getRow());
        }
        return min;
    }

    public int minCol() {
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

    public ArrayPiece flipOverX() {
        ArrayPiece aligned = this.touchAxes();
        Block[] newBlocks = Arrays.stream(aligned.blocks)
                .map(block -> block.flipOverX())
                .toArray(Block[]::new);
        return new ArrayPiece(newBlocks).touchAxes();
    }
    
    public ArrayPiece flipOverY() {
        ArrayPiece aligned = this.touchAxes();
        Block[] newBlocks = Arrays.stream(aligned.blocks)
                .map(block -> block.flipOverY())
                .toArray(Block[]::new);
        return new ArrayPiece(newBlocks).touchAxes();
    }

    public ArrayPiece rotate90() {
        Block[] newBlocks = Arrays.stream(blocks)
                .map(block -> block.rotate90())
                .toArray(Block[]::new);
        return new ArrayPiece(newBlocks);
    }

}
