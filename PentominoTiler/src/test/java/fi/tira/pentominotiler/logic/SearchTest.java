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

    static Board bd;
    static Search s;

    public SearchTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        bd = new Board(3, 20, Board.LETTER_SYMBOLS);
        s = new Search(bd);
        s.runSearch();
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
        assertEquals(2, s.getSolutions().size());
    }

    @Test
    public void testGetSolutions() {
        assertEquals(60, s.getSolutions().get(1).toString().length());
    }

    @Test
    public void testRunSearch() {
    }

    @Test
    public void testGetTried() {
        assertEquals(true, s.getTried().size() > 0 && s.getTried().size() < 1e6);
    }

    @Test
    public void testFoundProperty() {
        assertEquals(2, s.foundProperty().get());
    }

    @Test
    public void testGetDuration() {
        assertEquals(true, s.getDuration() > 0L);
    }

}
