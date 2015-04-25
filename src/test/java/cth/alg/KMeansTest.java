package cth.alg;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KMeansTest {

    Comparator<Integer> distance = (o1, o2) -> o1 - o2;

    @Test
    public void testTrivial1Means() throws Exception {
        List<Integer> list = Lists.newArrayList(1);
        KMeans km = new KMeans();

        Map<Integer, List<Integer>> partition = km.partition(1, list, distance);

        assertTrue(partition.get(0).contains(1));
    }

    @Test
    public void testTrivial2Means() throws Exception {
        List<Integer> list = Lists.newArrayList(1, 2);
        KMeans km = new KMeans();

        Map<Integer, List<Integer>> partition = km.partition(1, list, distance);

        assertTrue(partition.get(0).contains(1));
        assertTrue(partition.get(1).contains(2));
    }

    @Test
    public void test2Means() throws Exception {
        List<Integer> list = Lists.newArrayList(1, 2, 5);
        KMeans km = new KMeans();

        Map<Integer, List<Integer>> partition = km.partition(1, list, distance);

        assertTrue(partition.get(0).contains(1));
        assertTrue(partition.get(0).contains(2));
        assertTrue(partition.get(1).contains(5));
    }


    @Test
    public void testString2Means() throws Exception {
        List<String> list = Lists.newArrayList("a", "aa", "aaaaa");
        KMeans km = new KMeans();

        Comparator<String> d = (s1, s2) -> s1.chars().sum() - s2.chars().sum();

        Map<Integer, List<Integer>> partition = km.partition(1, list, d);

        assertTrue(partition.get(0).contains("a"));
        assertTrue(partition.get(0).contains("aa"));
        assertTrue(partition.get(1).contains("aaaaa"));
    }

    @Test
    public void testTakeRandom() throws Exception {

        KMeans<String> km = new KMeans();

        List<String> data = Lists.newArrayList("a", "b", "c");
        List<String> copy = Lists.newArrayList(data);

        String s1 = km.findRandom(data);
        assertTrue(copy.contains(s1));
        assertEquals(2, data.size());

        String s2 = km.findRandom(data);
        assertTrue(copy.contains(s2));
        assertEquals(1, data.size());

        String s3 = km.findRandom(data);
        assertTrue(copy.contains(s3));
        assertEquals(0, data.size());
    }
}
