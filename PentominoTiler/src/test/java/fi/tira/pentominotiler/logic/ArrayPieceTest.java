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

    @org.junit.Test
    public void testGetBlocks() {
        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(0, i);
        }

        ArrayPiece piece = new ArrayPiece(input);
        Block[] got = piece.getBlocks();
        assertArrayEquals(input, got);
    }

    @org.junit.Test
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

    @org.junit.Test
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

    @org.junit.Test
    public void testTouchAxes() {

        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(0, i);
        }

        ArrayPiece piece = new ArrayPiece(input).move(7, -2);

        assertArrayEquals(input, piece.touchAxes().getBlocks());
    }

    @org.junit.Test
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

    @org.junit.Test
    public void testFlipOverX() {
    }

    @org.junit.Test
    public void testRotate90() {
    }

}
