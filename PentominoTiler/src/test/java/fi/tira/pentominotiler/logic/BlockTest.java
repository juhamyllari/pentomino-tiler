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
public class BlockTest {

    public BlockTest() {
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
    public void testGetRow() {
        Block block = new Block(2, 3);
        assertEquals(2, block.getRow());
    }

    @Test
    public void testSetRow() {
        Block block = new Block(0, 3);
        block.setRow(-2);
        assertEquals(-2, block.getRow());
    }

    @Test
    public void testGetCol() {
        Block block = new Block(2, 3);
        assertEquals(3, block.getCol());
    }

    @Test
    public void testSetCol() {
        Block block = new Block(2, 3);
        block.setCol(-7);
        assertEquals(-7, block.getCol());
    }

    @Test
    public void testMove() {
        Block block = new Block(1, 1);
        block = block.move(-3, 6);
        assertEquals(-2, block.getRow());
        assertEquals(7, block.getCol());
    }

    @Test
    public void testFlipOverX() {
        Block block = new Block(1, 2);
        block = block.flipOverX();
        assertEquals(-1, block.getRow());
        assertEquals(2, block.getCol());
    }

    @Test
    public void testRotate90() {
        Block block = new Block(3, -1);
        block = block.rotate90();
        assertEquals(-1, block.getRow());
        assertEquals(-3, block.getCol());
    }

    @Test
    public void testEquals() {
        Block block1 = new Block(3, -1);
        Block block2 = new Block(3, -1);
        Block block3 = new Block(-1, 3);
        assertEquals(block1, block2);
        assertNotEquals(block2, block3);
    }

    @Test
    public void testToString() {
        Block block = new Block(-1, -2);
        assertEquals("(-1, -2)", block.toString());
    }

}
