package fi.tira.pentominotiler.logic;

public class ArrayPiece implements OrientedPiece {
    
    private Block[] blocks;

    public ArrayPiece(Block[] blocks) {
        this.blocks = blocks;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }
    
    
    
    
}
