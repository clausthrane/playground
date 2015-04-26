package cth.alg.set;

import com.google.common.collect.ArrayListMultimap;
import cth.alg.metricspace.Observation;

import java.util.List;

public class Partitioning<T> {

    private final int partitions;
    private final ArrayListMultimap<Integer, Observation<T>> m;

    public Partitioning(int number) {
        partitions = number;
        m = ArrayListMultimap.create(partitions, 100);
    }

    public Partitioning<T> add(int partition, Observation<T> observation) {
        m.put(partition, observation);
        return this;
    }

    public int count() {
        return partitions;
    }

    public List<Observation<T>> get(int i) {
        return m.get(i);
    }


}
