package cth.alg.metricspace;


import cth.alg.set.Partitioning;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Vector;

import static com.google.common.base.Preconditions.checkArgument;
import static cth.utils.collections.ListUtils.randomSubList;
import static cth.utils.math.MathUtils.sqred;
import static cth.utils.math.VectorUtils.*;

/**
 * Lloyd's algorithm, also known as Voronoi iteration or relaxation
 * <p>
 * we require (T,d) to be a metric space.
 */
public class KMeans<T> {

    private final int k;
    private final Vector<Double> ZERO;

    public KMeans(int k) {
        this.k = k;
        ZERO = newVector(k, 0d);
    }

    public Partitioning<T> partition(List<Observation<T>> observations) {
        checkArgument(CollectionUtils.isNotEmpty(observations), "cannot partition empty observations");

        List<Observation<T>> means = randomSubList(k, observations);
        boolean stable;
        do {
            Partitioning partitioning = partitionObservations(observations, means);
            stable = updateMeans(means, partitioning);
            if (stable) return partitioning;
        } while (!stable);

        throw new IllegalStateException("Unable to get a stable partitioning");
    }

    Partitioning<T> partitionObservations(List<Observation<T>> observations, List<Observation<T>> means) {
        Partitioning<T> partitioning = new Partitioning(k);
        for (Observation<T> observation : observations) {
            int partitionIdx = closestPartition(observation, means);
            partitioning.add(partitionIdx, observation);
        }
        return partitioning;
    }

    int closestPartition(Observation<T> observation, List<Observation<T>> means) {
        int bestPartition = 0;
        for (int i = 1; i < means.size(); i++) {
            int oldDistance = sqred(euclideanDistance(observation.position(), means.get(bestPartition).position()));
            int newDistance = sqred(euclideanDistance(observation.position(), means.get(i).position()));
            if (newDistance < oldDistance) {
                bestPartition = i;
            }
        }
        return bestPartition;
    }

    boolean updateMeans(List<Observation<T>> means, Partitioning partition) {

        boolean meansAreUnchanged = true;
        for (int i = 0; i < partition.count(); i++) {
            Observation<T> mean = calculateMean(partition.get(i));
            Observation<T> oldMean = means.get(i);
            meansAreUnchanged = meansAreUnchanged && mean.equals(oldMean);
            means.set(i, mean);
        }

        return meansAreUnchanged;
    }

    Observation<T> calculateMean(List<Observation<T>> observations) {
        Vector<Double> sum = newVector(dimensions(observations), 0d);
        for (Observation<T> observation : observations) {
            sum = add(sum, observation.position());
        }
        Vector<Double> scalar = newVector(dimensions(observations), (1.0d / observations.size()));
        Vector<Double> centroid = mult(sum, scalar);
        return ArtificialObservation.at(centroid);
    }

    private int dimensions(List<Observation<T>> observations) {
        return observations.iterator().next().position().size();
    }


    static class ArtificialObservation<T> extends Observation<T> {

        private ArtificialObservation(T obj, Vector<Double> pos) {
            super(obj, pos);
        }

        public static <T> ArtificialObservation<T> at(Vector<Double> pos) {
            return new ArtificialObservation<>(null, pos);
        }
    }

}
