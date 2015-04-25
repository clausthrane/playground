package cth.alg.sort;


import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static cth.alg.sort.SortTestUtils.randomList;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MergeSorterTest {

    MergeSorter<String> sorter = new MergeSorter<>(null);

    @Test
    public void testCanSortSortedList() throws Exception {
        sorter = new MergeSorter<String>(Lists.newArrayList("a", "b", "c"));
        List<String> sorted = sorter.mergeSort();
        assertEquals("a", sorted.get(0));
        assertEquals("b", sorted.get(1));
        assertEquals("c", sorted.get(2));
    }

    @Test
    public void testCanSortUnSortedList() throws Exception {
        sorter = new MergeSorter(Lists.newArrayList("c", "a", "b"));
        List<String> sorted = sorter.mergeSort();
        assertEquals("a", sorted.get(0));
        assertEquals("b", sorted.get(1));
        assertEquals("c", sorted.get(2));
    }

    @Test
    public void testArbitraryInput() throws Exception {
        List<String> list = randomList(10);
        List<String> copy = Lists.newArrayList(list);
        sorter = new MergeSorter(list);
        List<String> sorted = sorter.mergeSort();
        Collections.sort(copy);
        assertTrue(CollectionUtils.isEqualCollection(sorted, copy));
    }


    @Test
    public void testRemoveSmallest() throws Exception {
        List<String> left = Lists.newArrayList("a");
        List<String> right = Lists.newArrayList("b");
        String smallest = sorter.removeSmallest(left, right);
        assertEquals("a", smallest);
        assertEquals(0, left.size());
        assertEquals(1, right.size());
    }

    @Test
    public void testLeftIsSmaller() throws Exception {
        List<String> left = Lists.newArrayList("a");
        List<String> right = Lists.newArrayList("b");
        assertTrue(sorter.leftIsSmaller(left, right));
    }

    @Test
    public void testMergeLists() throws Exception {
        sorter = new MergeSorter<>(null);

        List<String> merged = sorter.merge(Lists.newArrayList("a"), Lists.newArrayList("b"));
        assertEquals("a", merged.get(0));
        assertEquals("b", merged.get(1));
    }

    @Test
    public void testMergeUnevenLengthLists() throws Exception {
        sorter = new MergeSorter<>(null);

        List<String> merged1 = sorter.merge(Lists.newArrayList("a", "d", "c"), Lists.newArrayList("b"));
        assertEquals("a", merged1.get(0));
        assertEquals("b", merged1.get(1));
        assertEquals("d", merged1.get(2));
        assertEquals("c", merged1.get(3));

        List<String> merged2 = sorter.merge(Lists.newArrayList("b", "d", "c"), Lists.newArrayList("a"));
        assertEquals("a", merged2.get(0));
        assertEquals("b", merged2.get(1));
        assertEquals("d", merged2.get(2));
        assertEquals("c", merged2.get(3));

    }
}
