package cth.alg.sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static cth.alg.sort.SortTestUtils.randomList;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeapSorterTest {

    HeapSorter<String> sorter;

    @Test
    public void testSortedInput() throws Exception {
        sorter = new HeapSorter<>(Lists.newArrayList("a", "b", "c"));
        List<String> sorted = sorter.heapSort();
        System.out.println(sorted);
        assertEquals("a", sorted.get(0));
        assertEquals("c", sorted.get(1));
        assertEquals("b", sorted.get(2));
    }

    @Test
    public void testArbitraryInput() throws Exception {
        List<String> list = randomList(100);
        List<String> copy = Lists.newArrayList(list);
        sorter = new HeapSorter(list);
        List<String> sorted = sorter.heapSort();
        Collections.sort(copy);
        System.out.println(copy);
        assertTrue(CollectionUtils.isEqualCollection(sorted, copy));
    }

    @Test
    public void testLargest() throws Exception {
        sorter = new HeapSorter<>(Lists.newArrayList("a", "b", "b"));

        assertEquals(0, sorter.largestValue(0, 1));
        assertEquals(1, sorter.largestValue(1, 1));
        assertEquals(2, sorter.largestValue(1, 2));
    }

    @Test
    public void testSiftDown() throws Exception {
        sorter = new HeapSorter<>(Lists.newArrayList("c", "b", "a"));
        sorter.siftDown(0, 2);
        List<String> list = sorter.getList();
        String leftChild = list.get(sorter.leftChild(0));
        String rightChild = list.get(sorter.rightChild(0));
        assertEquals(-1, "a".compareTo(leftChild));
        assertEquals(-2, "a".compareTo(rightChild));
    }

    @Test
    public void testChildAccessors() throws Exception {
        sorter = new HeapSorter<>(null);
        assertEquals(1, sorter.leftChild(0));
        assertEquals(2, sorter.rightChild(0));

        assertEquals(21, sorter.leftChild(10));
        assertEquals(22, sorter.rightChild(10));
    }
}
