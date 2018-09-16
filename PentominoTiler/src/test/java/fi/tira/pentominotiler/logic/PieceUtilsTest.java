package fi.tira.pentominotiler.logic;

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
public class PieceUtilsTest {

    public PieceUtilsTest() {
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
    public void testStringToArrayPiece() {
        Block[] expectedBlocks = new Block[5];
        for (int i = 0; i < 5; i++) {
            expectedBlocks[i] = new Block(i, 0);
        }
        
        String input = "#0000#0000#0000#0000#0000";
        ArrayPiece created = PieceUtils.stringToArrayPiece(input);
        assertArrayEquals(expectedBlocks, created.getBlocks());
    }

    @Test
    public void testAllPieces() {
        List<ArrayPiece> lst = PieceUtils.allPieces();
        assertEquals(12, lst.size());
        assertEquals(lst.get(0).toString(), "0#000###000#0000000000000");
    }
    
    @Test
    public void testNonRedundant() {
        ArrayPiece x = PieceUtils.stringToArrayPiece("0#000###000#0000000000000");
        ArrayPiece i = PieceUtils.stringToArrayPiece("#####00000000000000000000");
        ArrayPiece u = PieceUtils.stringToArrayPiece("##000#0000##0000000000000");
        ArrayPiece p = PieceUtils.stringToArrayPiece("##000##000#00000000000000");
        
        assertEquals(1, PieceUtils.nonRedundant(x).size());
        assertEquals(2, PieceUtils.nonRedundant(i).size());
        assertEquals(4, PieceUtils.nonRedundant(u).size());
        assertEquals(8, PieceUtils.nonRedundant(p).size());
    }
    
    @Test
    public void testAllOrientations() {
        Block[] input = new Block[5];
        for (int i = 0; i < 5; i++) {
            input[i] = new Block(i, 0);
        }

        ArrayPiece piece = new ArrayPiece(input);
        List<ArrayPiece> output = PieceUtils.allOrientations(piece);
        assertEquals(output.size(), 8);
    }

}
