package fi.tira.pentominotiler.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
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
        Board bd = new Board(3, 20, Board.LETTER_SYMBOLS);
        Search s = new Search(bd);
        s.runSearch();
        assertEquals(2, s.getSolutions().size());
    }

    @Test
    public void testGetSolutions() {
        Board bd = new Board(3, 20, Board.LETTER_SYMBOLS);
        Search s = new Search(bd);
        s.runSearch();
        assertEquals(60, s.getSolutions().get(1).toString().length());
    }

}
