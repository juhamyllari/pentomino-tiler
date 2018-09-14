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
     * @param str
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
     * Creates all the 8 orientations of an ArrayPiece.
     * @param piece
     * @return list of all orientations
     */
    public static List<ArrayPiece> allOrientations(ArrayPiece piece) {
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


}
