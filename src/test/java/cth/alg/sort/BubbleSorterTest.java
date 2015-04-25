package cth.alg.sort;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static cth.alg.sort.SortTestUtils.randomList;
import static org.junit.Assert.assertTrue;

public class BubbleSorterTest {

    private BubbleSorter sorter;

    @Test
    public void testArbitraryInput() throws Exception {
        List<String> list = randomList(10);
        List<String> copy = Lists.newArrayList(list);
        sorter = new BubbleSorter(list);
        List<String> sorted = sorter.bubbleSort();
        Collections.sort(copy);
        assertTrue(CollectionUtils.isEqualCollection(sorted, copy));
    }
}
