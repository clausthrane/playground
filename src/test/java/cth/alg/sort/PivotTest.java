package cth.alg.sort;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PivotTest {

    @Test
    public void testPivotIdx() throws Exception {
        int i = Pivot.chooseMiddleIdx(1, 10);
        assertEquals(5, i);
    }

    @Test
    public void testPivotSelection() throws Exception {
        ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d", "e");
        Pivot pivot = Pivot.choose(list, 0, list.size());
        assertEquals("c", pivot.get());
    }

    @Test
    public void testPivot() throws Exception {
        Pivot pivot = new Pivot("c", 0);
        assertTrue(pivot.isBigger("a"));
        assertTrue(pivot.isBigger("b"));
        assertFalse(pivot.isBigger("d"));
    }

    @Test
    public void testOneElement() throws Exception {
        ArrayList<String> list = Lists.newArrayList("a");
        Pivot p = Pivot.choose(list, 0, 1);
        assertEquals("a", p.get());
        assertEquals(0, p.getIdx());
    }

    @Test
    public void testCompareTo() throws Exception {
        assertTrue("c".compareTo("d") < 0);
        assertTrue("c".compareTo("c") == 0);
        assertTrue("c".compareTo("b") > 0);
        assertTrue("c".compareTo("a") > 0);
    }
}
