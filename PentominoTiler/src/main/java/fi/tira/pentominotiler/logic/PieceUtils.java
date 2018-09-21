package fi.tira.pentominotiler.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for handling ArrayPiece objects.
 * @author juha
 */
public class PieceUtils {
    
    /**
     * Converts a string representation of a piece to an ArrayPiece.
     * The validity of the piece is not checked.
     * @param str a String of length 25 with "0" for "no block" and "#" for "block"
     * @return an ArrayPiece
     */
    public static ArrayPiece stringToArrayPiece(String str) {
        Block[] blocks = new Block[5];
        int blockIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                int row = i / 5;
                int col = i % 5;
                blocks[blockIndex++] = new Block(row, col);
            }
        }
        return new ArrayPiece(blocks);
    }
    
    /**
     * Create all the 12 pentominoes from strings (provided internally).
     * @return an ArrayList of the pieces.
     */
    public static List<ArrayPiece> allPieces() {
        ArrayList<ArrayPiece> pieces = new ArrayList<>();
        pieces.add(stringToArrayPiece("0#000###000#0000000000000"));
        pieces.add(stringToArrayPiece("#####00000000000000000000"));
        pieces.add(stringToArrayPiece("0#0000#000###000000000000"));
        pieces.add(stringToArrayPiece("00#0000#00###000000000000"));
        pieces.add(stringToArrayPiece("0##00##000#00000000000000"));
        pieces.add(stringToArrayPiece("00#00###00#00000000000000"));
        pieces.add(stringToArrayPiece("##000#0000##0000000000000"));
        pieces.add(stringToArrayPiece("##000##000#00000000000000"));
        pieces.add(stringToArrayPiece("##0000###0000000000000000"));
        pieces.add(stringToArrayPiece("##0000#0000#0000#00000000"));
        pieces.add(stringToArrayPiece("0#000##0000##000000000000"));
        pieces.add(stringToArrayPiece("0#000####0000000000000000"));
        return pieces;
    }
    
    /**
     * Creates all the 8 orientations of an ArrayPiece.
     * @param piece a valid pentomino
     * @return list of all orientations
     */
    public static List<ArrayPiece> allOrientations(ArrayPiece piece) {
        piece = piece.align();
        ArrayList<ArrayPiece> pieces = new ArrayList<>();
        ArrayPiece flipped = piece.flipOverX();
        pieces.add(piece);
        pieces.add(flipped);
        pieces.add(piece.rotate90());
        pieces.add(piece.rotate90().rotate90());
        pieces.add(piece.rotate90().rotate90().rotate90());
        pieces.add(flipped.rotate90());
        pieces.add(flipped.rotate90().rotate90());
        pieces.add(flipped.rotate90().rotate90().rotate90());
        return pieces;
    }
    
    /**
     * Creates all the non-redundant orientations of an ArrayPiece.
     * (There are 1–8, depending on the piece.)
     * @param piece a valid pentomino
     * @return an ArrayList of length 1–8
     */
    public static List<ArrayPiece> nonRedundant(ArrayPiece piece) {
        List<ArrayPiece> all = allOrientations(piece);
        List<ArrayPiece> unique = new ArrayList<>();
        for (ArrayPiece candidate : all) {
            boolean found = false;
            for (ArrayPiece p : unique) {
                if (candidate.toString().equals(p.toString())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                unique.add(candidate);
            }
        }
        return unique;
    }
    
    /**
     * For each block in the piece, the method centers the piece on that block.
     * The output is a list of 5 distinct copies of the piece, each translated
     * so as to move a single block to the origin.
     * @param piece
     * @return a list of 5 translations of the piece
     */
    public static List<ArrayPiece> centered(ArrayPiece piece) {
        List<ArrayPiece> result = new ArrayList<>();
        for (Block block : piece.getBlocks()) {
            result.add(piece.move(-block.getRow(), -block.getCol()));
        }
        return result;
    } 


}
