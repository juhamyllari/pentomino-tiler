package fi.tira.pentominotiler.datastructures;

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
public class MyArrayListTest {

    public MyArrayListTest() {
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
        MyArrayList<Integer> lst = new MyArrayList<>();
        assertEquals(false, lst.contains(0));
        lst.add(0);
        assertEquals(true, lst.contains(0));
        assertEquals(false, lst.contains(1));

    }

    @Test
    public void testGet() {
        MyArrayList<String> lst = new MyArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 2; i++) {
            lst.add(sb.toString());
            sb.append("hello");
        }
        assertEquals("", lst.get(0));
        assertEquals("hello", lst.get(1));
        assertEquals("hellohello", lst.get(2));
    }

    @Test
    public void testSize() {
        MyArrayList<Integer> lst = new MyArrayList<>();
        assertEquals(0, lst.size());
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        assertEquals(10, lst.size());
    }

    @Test
    public void testIsEmpty() {
        MyArrayList<Integer> lst = new MyArrayList<>();
        assertEquals(true, lst.isEmpty());
        lst.add(-23);
        assertEquals(false, lst.isEmpty());
    }

    @Test
    public void testContains() {
        MyArrayList<Integer> lst = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }
        assertEquals(true, lst.contains(0));
        assertEquals(true, lst.contains(9));
        assertEquals(false, lst.contains(10));
    }

    @Test
    public void testIterator() {
        MyArrayList<Integer> lst = new MyArrayList<>();
        assertEquals(false, lst.iterator().hasNext());
        lst.add(-1);
        assertEquals(true, lst.iterator().hasNext());
    }

    @Test
    public void testAddAll() {
        MyArrayList<Integer> lst1 = new MyArrayList<>();
        MyArrayList<Integer> lst2 = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst1.add(i);
        }
        lst2.addAll(lst1);
        assertEquals(10, lst2.size());

    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testClear() {
    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testToArray_0args() {
    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testToArray_GenericType() {
    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testRemove() {
    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testContainsAll() {
    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testRemoveAll() {
    }

    /**
     * Will not be implemented.
     */
    @Test
    public void testRetainAll() {
    }

}
