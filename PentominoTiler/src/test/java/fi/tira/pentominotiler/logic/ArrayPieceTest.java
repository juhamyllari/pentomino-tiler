package fi.tira.pentominotiler.logic;

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
public class ArrayPieceTest {

    public ArrayPieceTest() {
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
    public void testGetBlocks() {
        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(0, i);
        }

        ArrayPiece piece = new ArrayPiece(input);
        Block[] got = piece.getBlocks();
        assertArrayEquals(input, got);
    }

    @Test
    public void testSetBlocks() {
        Block[] firstInput = new Block[5];
        for (int i = 0; i < 5; i++) {
            firstInput[i] = new Block(0, i);
        }

        Block[] secondInput = new Block[5];
        for (int i = 0; i < 5; i++) {
            secondInput[i] = new Block(0, i);
        }

        ArrayPiece piece = new ArrayPiece(firstInput);
        piece.setBlocks(secondInput);
        Block[] got = piece.getBlocks();
        assertArrayEquals(secondInput, got);
    }

    @Test
    public void testMove() {

        int rowOffset = -1;
        int colOffset = 2;

        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(0, i);
        }

        Block[] expected = new Block[5];
        for (int i = 0; i < 5; i++) {
            expected[i] = new Block(rowOffset, i + colOffset);
        }

        ArrayPiece piece = new ArrayPiece(input).move(rowOffset, colOffset);
        assertArrayEquals(expected, piece.getBlocks());
    }

    @Test
    public void testTouchAxes() {

        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(0, i);
        }

        ArrayPiece piece = new ArrayPiece(input).move(7, -2);

        assertArrayEquals(input, piece.align().getBlocks());
    }

    @Test
    public void testToString() {
        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(i, 0);
        }

        ArrayPiece piece = new ArrayPiece(input);

        String expected = "#0000#0000#0000#0000#0000";
        String result = piece.toString();
        assertEquals(expected, result);
    }

    @Test
    public void testFlipOverX() {
        String input = "#0000####0000000000000000";
        String flippedInput = "####0#0000000000000000000";
        ArrayPiece pieceFlipped = PieceUtils.stringToArrayPiece(input).flipOverX();
        String flippedPieceString = pieceFlipped.toString();
        assertEquals(flippedInput, flippedPieceString);
    }

    @Test
    public void testRotate90() {
        Block[] input1 = new Block[5];
        for (int i = 0; i < 5; i++) {
            input1[i] = new Block(i, 0);
        }
        Block[] input2 = new Block[5];
        for (int i = 0; i < 5; i++) {
            input2[i] = new Block(0, i);
        }
        assertEquals(new ArrayPiece(input1).rotate90().toString(), new ArrayPiece(input2).toString());
    }

}
