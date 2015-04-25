package cth.alg;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Lloyd's algorithm, also known as Voronoi iteration or relaxation
 *
 * we require (T,d) to be a metric space.
 */
public class KMeans<T extends Comparable> {

    private final int k;
    private final Comparator<T> distance;
    private final T zero;

    public KMeans(int k, Comparator<T> distance, T zero) {
        this.k = k;
        this.distance = distance;
        this.zero = zero;
    }

    public Map<Integer, List<T>> partition(List<T> observations) {
        Map<Integer, List<T>> partition = kSizedMap(k);
        List<T> means = initialiseWithRandom(observations);

        doPartition(partition, means, observations);

        updateMeans(means, partition);


        return partition;

    }

    // boolean = did change?
    private void updateMeans(List<T> means, Map<Integer, List<T>> partitions) {
        for (Map.Entry<Integer, List<T>> partition : partitions.entrySet()) {
            T newMean = calculateMean(partition.getValue());
            means.set(partition.getKey(), newMean);
        }
    }

    private T calculateMean(List<T> observations) {
        int sum = 0;
        for (T observation : observations) {
            sum += distance.compare(observation, zero);
        }
        return sum/observations.size();
    }

    private void doPartition(Map<Integer, List<T>> partition, List<T> means, List<T> observations) {
        for (T observation : observations) {
            int partitionIdx = findBestFit(observation, means, distance);
            partition.get(partitionIdx).add(observation);
        }
    }

    private int findBestFit(T observation, List<T> means, Comparator<T> distance) {
        int currentPartition = 0;
        for (int i = 0; i < means.size(); i++) {
            int newDistance = distance.compare(observation, means.get(i));
            int oldDistance = distance.compare(observation, means.get(currentPartition));
            if (newDistance < oldDistance) {
                currentPartition = i;
            }
        }
        return currentPartition;
    }

    private List<T> initialiseWithRandom(List<T> observations) {
        List<T> means = Lists.newArrayList();
        for (int i = 0; i <= this.k ; i++) {
            means.add(i, findRandom(observations));
        }
        return means;
    }

    T findRandom(List<T> list) {
        int i = RandomUtils.nextInt(list.size());
        return list.get(i);
    }

    Map<Integer, List<T>> kSizedMap(int k) {
        Map<Integer, List<T>> m = Maps.newHashMap();
        for (int i = 0; i < k; i++) {
            m.put(i, Lists.newArrayList());
        }
        return m;
    }

}
