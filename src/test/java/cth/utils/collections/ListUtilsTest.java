package cth.utils.collections;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListUtilsTest {

    @Test
    public void testRandomSubList() throws Exception {
        List<String> data = Lists.newArrayList("a", "b", "c");
        List<String> strings = ListUtils.randomSubList(2, data);
        assertEquals(2, strings.size());
        assertTrue(CollectionUtils.isSubCollection(strings, data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomSubListError() throws Exception {
        ListUtils.randomSubList(1, Lists.newArrayList());
    }

    @Test
    public void testTakeRandom() throws Exception {

        List<String> data = Lists.newArrayList("a", "b", "c");
        List<String> copy = Lists.newArrayList(data);

        String s1 = ListUtils.takeRandom(data);
        assertTrue(copy.contains(s1));
        assertEquals(2, data.size());

        String s2 = ListUtils.takeRandom(data);
        assertTrue(copy.contains(s2));
        assertEquals(1, data.size());

        String s3 = ListUtils.takeRandom(data);
        assertTrue(copy.contains(s3));
        assertEquals(0, data.size());
    }

}
