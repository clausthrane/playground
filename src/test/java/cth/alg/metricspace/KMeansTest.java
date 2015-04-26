package cth.alg.metricspace;

import com.google.common.collect.Lists;
import cth.alg.set.Partitioning;
import cth.utils.math.VectorUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static cth.utils.math.VectorUtils.newVector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KMeansTest {

    KMeans one_mean = new KMeans(1);
    Vector<Double> vector = newVector(1.0d);

    @Test
    public void testCalculateMean() throws Exception {
        Observation<String> obs1 = Observation.at(newVector(1.5d, 1.5d), "foo");
        Observation<String> obs2 = Observation.at(newVector(2.0d, 1d), "bar");
        Observation<String> obs3 = Observation.at(newVector(1.75d, 1.25d), "baz");
        Observation mean = new KMeans(0).calculateMean(Arrays.asList(obs1, obs2));
        assertEquals(obs3.position(), mean.position());
    }

    @Test
    public void testUpdateMeans() throws Exception {
        Observation<String> obs1 = Observation.at(newVector(1.0d, 1.0d), "foo");
        Observation<String> obs2 = Observation.at(newVector(10.0d, 10.0d), "bar");
        Observation<String> obs3 = Observation.at(newVector(3.0d, 3.0d), "baz");
        Observation<String> obs4 = Observation.at(newVector(12.0d, 12.0d), "daz");

        List<Observation<String>> means = Arrays.asList(obs1, obs2);

        Partitioning<String> partitioning = new Partitioning(means.size())
                .add(0, obs1)
                .add(0, obs3)
                .add(1, obs2)
                .add(1, obs4);

        boolean stable = new KMeans(0).updateMeans(means, partitioning);

        assertFalse(stable);
        assertEquals(newVector(2.0d, 2.0d), means.get(0).position());
        assertEquals(newVector(11.0d, 11.0d), means.get(1).position());

        stable = new KMeans(0).updateMeans(means, partitioning);
        assertTrue(stable);
    }

    @Test
    public void testClosestPartition() throws Exception {
        Observation<String> obs1 = Observation.at(newVector(10.0d, 15.0d), "foo");
        Observation<String> obs2 = Observation.at(newVector(1.0d, 1.0d), "bar");
        Observation<String> obs3 = Observation.at(newVector(1.0d, 28.0d), "baz");

        int i = new KMeans(0).closestPartition(obs1, Arrays.asList(obs2, obs3));
        assertEquals(1, i);
    }

    @Test
    public void testPartitionObservations() throws Exception {
        Observation<String> obs1 = Observation.at(newVector(1.0d, 1.0d), "foo");
        Observation<String> obs2 = Observation.at(newVector(10.0d, 10.0d), "bar");
        Observation<String> obs3 = Observation.at(newVector(3.0d, 3.0d), "baz");
        Observation<String> obs4 = Observation.at(newVector(12.0d, 12.0d), "daz");

        List<Observation> toPartition = Arrays.asList(obs1, obs2, obs3, obs4);
        List<Observation> centroids = Arrays.asList(obs2, obs3, obs4);

        Partitioning partitioning = new KMeans(3).partitionObservations(toPartition, centroids);
        assertEquals(3, partitioning.count());
        assertEquals(1, partitioning.get(0).size());
        assertEquals(2, partitioning.get(1).size());
        assertEquals(1, partitioning.get(2).size());
    }

    @Test
    public void testTrivial1Means() throws Exception {
        Observation<String> obs = Observation.at(vector, "hello");
        Partitioning partition = one_mean.partition(Arrays.asList(obs));
        assertTrue(partition.get(0).contains(vector));
    }

    @Test
    public void testPartition() throws Exception {

        Vector<Double> center1 = newVector(1.0d, 1.0d, 1.0d);
        Vector<Double> center2 = newVector(5.0d, 5.0d, 5.0d);
        Vector<Double> center3 = newVector(10.0d, 1.0d, 5.0d);

        List<Vector<Double>> cluster1 = buildCluster(center1);
        List<Vector<Double>> cluster2 = buildCluster(center2);
        List<Vector<Double>> cluster3 = buildCluster(center3);

        List<Vector<Double>> all = Lists.newArrayList();
        all.addAll(cluster1);
        all.addAll(cluster2);
        all.addAll(cluster3);

        List<Observation<String>> obs = Lists.newArrayList();
        for (Vector<Double> v : all) {
            obs.add(Observation.at(v, RandomStringUtils.random(4)));
        }

        KMeans alg = new KMeans(3);

        Partitioning partition = alg.partition(obs);
        assertEquals(3, partition.count());

    }

    private List<Vector<Double>> buildCluster(Vector<Double> center1) {
        List<Vector<Double>> cluster = Lists.newArrayList();
        for (int i = -2; i < 2; i++) {
            double x = (0.1 * i) * RandomUtils.nextInt(5);
            double y = (0.1 * i) * RandomUtils.nextInt(5);
            double z = (0.1 * i) * RandomUtils.nextInt(5);
            cluster.add(VectorUtils.add(newVector(x, y, z), center1));
        }
        return cluster;
    }


    //    @Test
//    public void testString2Means() throws Exception {
//        List<String> list = Lists.newArrayList("a", "aa", "aaaaa");
//        KMeans km = new KMeans();
//
//        Comparator<String> d = (s1, s2) -> s1.chars().sum() - s2.chars().sum();
//
//        Map<Integer, List<Integer>> partition = km.partition(1, list, d);
//
//        assertTrue(partition.get(0).contains("a"));
//        assertTrue(partition.get(0).contains("aa"));
//        assertTrue(partition.get(1).contains("aaaaa"));
//    }


}
