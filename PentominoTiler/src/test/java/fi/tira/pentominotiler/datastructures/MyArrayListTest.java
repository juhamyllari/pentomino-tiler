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
    
}
