package fi.tira.pentominotiler.datastructures;

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
public class MyHashSetTest {

    public MyHashSetTest() {
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
    public void testAdd() {
        MyHashSet<String> set = new MyHashSet<>();
        assertEquals(false, set.contains("hello"));
        set.add("hello");
        assertEquals(true, set.contains("hello"));
    }

    @Test
    public void testAddAll() {
        MyArrayList<Integer> lst = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        MyHashSet<Integer> set = new MyHashSet<>();
        assertEquals(false, set.contains(0));
        set.addAll(lst);
        assertEquals(true, set.contains(0));
        assertEquals(10, set.size());
    }

    @Test
    public void testContains() {
        MyHashSet<Integer> set = new MyHashSet<>();
        assertEquals(false, set.contains(-1));
        set.add(-1);
        assertEquals(true, set.contains(-1));
    }

    @Test
    public void testSize() {
        MyHashSet<Double> set = new MyHashSet<>();
        assertEquals(0, set.size());
        set.add(-23.0);
        assertEquals(1, set.size());
    }

    @Test
    public void testMaxBucketSize() {
        MyHashSet<Integer> set = new MyHashSet<>();
        assertEquals(true, set.maxBucketSize() == 0);
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }
        assertEquals(true, set.maxBucketSize() > 0);
        assertEquals(true, set.maxBucketSize() <= 10);
    }

}
