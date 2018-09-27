package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import java.util.List;

/**
 * A utility class for handling Piece objects.
 * @author juha
 */
public class PieceUtils {
    
    /**
     * Converts a string representation of a piece to an Piece.
     * The validity of the piece is not checked.
     * @param str a String of length 25 with "0" for "no block" and "#" for "block"
     * @return an Piece
     */
    public static Piece stringToPiece(String str) {
        Block[] blocks = new Block[5];
        int blockIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                int row = i / 5;
                int col = i % 5;
                blocks[blockIndex++] = new Block(row, col);
            }
        }
        return new Piece(blocks);
    }
    
    /**
     * Create all the 12 pentominoes from strings (provided internally).
     * @return the 12 pieces
     */
    public static List<Piece> allPieces() {
        MyArrayList<Piece> pieces = new MyArrayList<>();
        pieces.add(stringToPiece("0#000###000#0000000000000"));
        pieces.add(stringToPiece("#####00000000000000000000"));
        pieces.add(stringToPiece("0#0000#000###000000000000"));
        pieces.add(stringToPiece("00#0000#00###000000000000"));
        pieces.add(stringToPiece("0##00##000#00000000000000"));
        pieces.add(stringToPiece("00#00###00#00000000000000"));
        pieces.add(stringToPiece("##000#0000##0000000000000"));
        pieces.add(stringToPiece("##000##000#00000000000000"));
        pieces.add(stringToPiece("##0000###0000000000000000"));
        pieces.add(stringToPiece("##0000#0000#0000#00000000"));
        pieces.add(stringToPiece("0#000##0000##000000000000"));
        pieces.add(stringToPiece("0#000####0000000000000000"));
        return pieces;
    }
    
    /**
     * Creates all the 8 orientations of an Piece.
     * @param piece a valid pentomino
     * @return all orientations of the piece
     */
    public static List<Piece> allOrientations(Piece piece) {
        piece = piece.align();
        MyArrayList<Piece> pieces = new MyArrayList<>();
        Piece flipped = piece.flipOverX();
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
     * Creates all the non-redundant orientations of an Piece.
     * (There are 1–8, depending on the piece.)
     * @param piece a valid pentomino
     * @return the 1–8 non-redundant orientations of the piece
     */
    public static List<Piece> nonRedundant(Piece piece) {
        List<Piece> all = allOrientations(piece);
        List<Piece> unique = new MyArrayList<>();
        for (Piece candidate : all) {
            boolean found = false;
            for (Piece p : unique) {
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
    public static List<Piece> centered(Piece piece) {
        List<Piece> result = new MyArrayList<>();
        for (Block block : piece.getBlocks()) {
            result.add(piece.move(-block.getRow(), -block.getCol()));
        }
        return result;
    } 


}
