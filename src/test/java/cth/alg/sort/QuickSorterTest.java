package cth.alg.sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cth.alg.sort.SortTestUtils.randomList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertTrue;

public class QuickSorterTest {
    QuickSorter<String> sorter = null;

    @Test
    public void testSortAlreadySorted() throws Exception {
        sorter = new QuickSorter<String>(Lists.newArrayList("a", "b"));
        List<String> sorted = sorter.quickSort();
        assertEquals("a", sorted.get(0));
        assertEquals("b", sorted.get(1));
    }

    @Test
    public void testSortUnsorted() throws Exception {
        sorter = new QuickSorter<String>(Lists.newArrayList("b", "a"));
        List<String> sorted = sorter.quickSort();
        assertEquals("a", sorted.get(0));
        assertEquals("b", sorted.get(1));
    }

    @Test
    public void testArbitraryInput() throws Exception {
        List<String> list = randomList(10);
        List<String> copy = Lists.newArrayList(list);
        sorter = new QuickSorter<String>(list);
        List<String> sorted = sorter.quickSort();
        Collections.sort(copy);
        assertTrue(CollectionUtils.isEqualCollection(sorted, copy));
    }

    @Test
    public void testEmptyList() throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        sorter = new QuickSorter(list);
        List<String> sortedList = sorter.quickSort();
        assertEquals(list, sortedList);
        assertEquals(list.size(), sortedList.size());
    }

    @Test
    public void testOneLengthList() throws Exception {
        ArrayList<String> list = Lists.newArrayList("a");
        sorter = new QuickSorter<String>(list);
        List<String> sortedList = sorter.quickSort();
        assertEquals(list, sortedList);
        assertEquals(list.size(), sortedList.size());
    }

    @Test
    public void testSwap() throws Exception {
        ArrayList<String> list = Lists.newArrayList("a", "b", "c", "d", "e");
        sorter = new QuickSorter<String>(list);
        sorter.swap(1, 3);
        assertEquals("b", list.get(3));
        assertEquals("d", list.get(1));
    }

    @Test
    public void testPartitionOfTwo() throws Exception {
        ArrayList<String> list = Lists.newArrayList("b", "a");
        sorter = new QuickSorter<String>(list);
        sorter.partition(0, list.size() - 1);
        assertEquals("a", sorter.getList().get(0));
        assertEquals("b", sorter.getList().get(1));
    }

    @Test
    public void testPartitionOfThree() throws Exception {
        ArrayList<String> list = Lists.newArrayList("b", "a", "c");
        sorter = new QuickSorter<String>(list);
        sorter.partition(0, list.size() - 1);
        assertEquals("a", sorter.getList().get(0));
    }

}
