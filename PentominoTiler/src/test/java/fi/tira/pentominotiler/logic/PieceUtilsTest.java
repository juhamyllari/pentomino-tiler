package fi.tira.pentominotiler.logic;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import java.util.Arrays;
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
        Piece created = PieceUtils.stringToPiece(input);
        assertArrayEquals(expectedBlocks, created.getBlocks());
    }

    @Test
    public void testAllPieces() {
        MyArrayList<Piece> lst = PieceUtils.allPieces();
        assertEquals(12, lst.size());
        assertEquals(lst.get(0).toString(), "0#000###000#0000000000000");
    }
    
    @Test
    public void testNonRedundant() {
        Piece x = PieceUtils.stringToPiece("0#000###000#0000000000000");
        Piece i = PieceUtils.stringToPiece("#####00000000000000000000");
        Piece u = PieceUtils.stringToPiece("##000#0000##0000000000000");
        Piece p = PieceUtils.stringToPiece("##000##000#00000000000000");
        
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

        Piece piece = new Piece(input);
        MyArrayList<Piece> output = PieceUtils.allOrientations(piece);
        assertEquals(output.size(), 8);
    }

    @Test
    public void testStringToPiece() {
        String input = "#####00000000000000000000";
        Piece i = PieceUtils.stringToPiece(input);
        assertEquals(input, i.toString());
    }

    @Test
    public void testCentered() {
        Piece x = PieceUtils.stringToPiece("0#000###000#0000000000000");
        MyArrayList<Piece> lst = PieceUtils.centered(x);
        assertEquals(5, lst.size());
        Block origin = new Block(0, 0);
        lst.stream().map(piece -> piece.getBlocks()).forEachOrdered((blocks) -> {
            assertEquals(true, Arrays.stream(blocks).anyMatch(block -> block.equals(origin)));
        });
    }

}
