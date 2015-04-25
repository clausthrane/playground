package cth.alg.sort;


import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class InsertionSorterTest {

    InsertionSorter<String> sorter;

    @Test
    public void testAlreadySortedArray() throws Exception {
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");
        sorter = new InsertionSorter<>(list);
        List<String> sorted = sorter.insertionSort();
        Assert.assertEquals("a", sorted.get(0));
        Assert.assertEquals("b", sorted.get(1));
        Assert.assertEquals("c", sorted.get(2));
    }

    @Test
    public void testUnsortedInput() throws Exception {
        List<String> list = SortTestUtils.randomList(10);
        List<String> copy = Lists.newArrayList(list);
        sorter = new InsertionSorter<>(list);
        List<String> sorted = sorter.insertionSort();
        Collections.sort(copy);
        assertTrue(CollectionUtils.isEqualCollection(sorted, copy));
    }
}
