package fi.tira.pentominotiler.performance;

import fi.tira.pentominotiler.datastructures.MyArrayList;
import fi.tira.pentominotiler.logic.Board;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class runs a performance test and is not an actual unit test.
 *
 * @author juha
 */
public class PerformanceMeasuresTest {

    public PerformanceMeasuresTest() {
        System.out.println("Running performance tests. This may take some time.\n");
        PerformanceMeasures.comparePerformance(3, 20, 10);
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
    public void testTestPerformance() {
    }

}
