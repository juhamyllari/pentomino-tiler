package fi.tira.pentominotiler.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The actual search is not unit tested at this point!
 * This is because it does not yet terminate.
 * @author juha
 */
public class SearchTest {

    public SearchTest() {
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
    public void testSearch() {
        Board bd = new Board(6, 10, Board.LETTER_SYMBOLS);
        Search s = new Search(bd);
    }

    @Test
    public void testGetSolutions() {
        Board bd = new Board(6, 10, Board.LETTER_SYMBOLS);
        Search s = new Search(bd);
        assertEquals(0, s.getSolutions().size());
    }

}
