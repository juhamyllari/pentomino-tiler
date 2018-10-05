package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;

/**
 * A utility class for handling Piece objects.
 *
 * @author juha
 */
public class PieceUtils {

    /**
     * Convert a string representation of a piece to a Piece object. The
     * validity of the piece is not checked.
     *
     * @param str a String of length 25 with "0" for "no block" and "#" for
     * "block"
     * @return the piece
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
     * Create all the 12 pentominoes from Strings (provided internally).
     *
     * @return the 12 pieces
     */
    public static MyArrayList<Piece> allPieces() {
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
     * Create all the 8 variants of a Piece. Returns the original piece and
     * rotated and/or flipped variants thereof. Duplicates are not removed.
     *
     * @param piece a valid pentomino
     * @return all variants of the piece
     */
    public static MyArrayList<Piece> allVariants(Piece piece) {
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
     * Creates all the non-redundant variants of a Piece. (There are 1–8,
     * depending on the piece.) This is the same as the method allVariants with
     * duplicates omitted.
     *
     * @param piece a valid pentomino
     * @return the 1–8 non-redundant variants of the piece
     */
    public static MyArrayList<Piece> nonRedundant(Piece piece) {
        MyArrayList<Piece> all = allVariants(piece);
        MyArrayList<Piece> unique = new MyArrayList<>();
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
     *
     * @param piece
     * @return the 5 translations of the piece
     */
    public static MyArrayList<Piece> centered(Piece piece) {
        MyArrayList<Piece> result = new MyArrayList<>();
        for (Block block : piece.getBlocks()) {
            result.add(piece.move(-block.getRow(), -block.getCol()));
        }
        return result;
    }

}
