package cth.alg.metricspace;

import java.util.Vector;

import static cth.utils.math.VectorUtils.euclideanDistance;

public class Observation<T> {

    private final T obj;
    private final Vector<Double> pos;


    protected Observation(T obj, Vector<Double> pos) {
        this.obj = obj;
        this.pos = pos;
    }

    public Vector<Double> position(){
        return this.pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Observation)) return false;

        Observation that = (Observation) o;

        return euclideanDistance(this.position(), that.position()) == 0;
    }

    @Override
    public int hashCode() {
        int result = obj != null ? obj.hashCode() : 0;
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        return result;
    }

    public static <T> Observation<T> at(Vector<Double> pos, T obj) {
        return new Observation<>(obj, pos);
    }
}
