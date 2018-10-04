package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juha
 */
public class BoardTest {

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCanPlace() {
        Piece piece = PieceUtils.stringToPiece("#0000#0000#0000#0000#0000");
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS);
        assertEquals(true, b1.canPlace(piece, 0, 0));
        assertEquals(false, b1.canPlace(piece, 2, 0));
        Board b2 = b1.placePiece(piece, 0, 0, 1);
        assertEquals(false, b2.canPlace(piece, 0, 0));
    }

    @Test
    public void testPlacePiece() {
        Piece piece = PieceUtils.stringToPiece("#0000#0000#0000#0000#0000");
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS).placePiece(piece, 0, 0, 1);
        assertEquals('I', b1.charAtLinearIndex(0));
        assertEquals(0, b1.charAtLinearIndex(1));
    }

    @Test
    public void testToString() {
        Piece piece = PieceUtils.stringToPiece("#0000#0000#0000#0000#0000");
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS).placePiece(piece, 0, 0, 1);
        String str = b1.toString();
        assertEquals(60, str.length());
        assertEquals('I', str.charAt(0));
        assertEquals('0', str.charAt(1));
    }

    @Test
    public void testSymmetryStrings() {
        String input = "#0000#0000#0000#0000#0000";
        Piece piece = PieceUtils.stringToPiece(input);
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS).placePiece(piece, 0, 0, 1);
        MyArrayList<String> lst = b1.symmetryStrings();
        assertEquals(3, lst.size());
        assertEquals("0000000000I000000000I000000000I000000000I000000000I000000000", lst.get(0));
        assertEquals("000000000I000000000I000000000I000000000I000000000I0000000000", lst.get(1));
        assertEquals("0000000000000000000I000000000I000000000I000000000I000000000I", lst.get(2));
    }

    @Test
    public void testGetUnused() {
        Piece piece = PieceUtils.stringToPiece("#0000#0000#0000#0000#0000");
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS);
        Board b2 = b1.placePiece(piece, 0, 0, 1);
        assertEquals(12, b1.getUnused());
        assertEquals(11, b2.getUnused());
    }

    @Test
    public void testIsUsed() {
        Piece piece = PieceUtils.stringToPiece("#0000#0000#0000#0000#0000");
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS);
        Board b2 = b1.placePiece(piece, 0, 0, 1);
        assertEquals(false, b1.isUsed(0));
        assertEquals(false, b1.isUsed(1));
        assertEquals(false, b2.isUsed(0));
        assertEquals(true, b2.isUsed(1));
    }

    @Test
    public void testGetCols() {
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS);
        assertEquals(10, b1.getCols());
    }

    @Test
    public void testGetRows() {
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS);
        assertEquals(6, b1.getRows());
    }

    @Test
    public void testCharAtLinearIndex() {
        Piece piece = PieceUtils.stringToPiece("0#000###000#0000000000000");
        Board b1 = new Board(6, 10, Board.LETTER_SYMBOLS).placePiece(piece, 0, 0, 0);
        assertEquals(0, b1.charAtLinearIndex(0));
        assertEquals('X', b1.charAtLinearIndex(1));
        assertEquals('X', b1.charAtLinearIndex(10));
    }
}
