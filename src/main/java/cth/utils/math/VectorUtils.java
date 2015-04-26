package cth.utils.math;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Vector;
import java.util.function.BinaryOperator;

public class VectorUtils {

    public static <T> Vector<T> newVector(int size) {
        Vector vector = new Vector();
        vector.setSize(size);
        return vector;
    }

    public static <T> Vector<T> newVector(T ... items) {
        Vector vector = new Vector();
        vector.setSize(items.length);
        for (int i = 0; i < items.length; i++) {
            vector.set(i, items[i]);
        }
        return vector;
    }

    public static <T> List<Vector<T>> newVectors(T[] ... items) {
        List<Vector<T>> list = Lists.newArrayList();
        for (T[] item : items) {
            list.add(newVector(item));
        }
        return list;
    }

    public static <T> Vector<T> newVector(int size, T defaultValue) {
        Vector<T> v = newVector(size);
        for (int j = 0; j < size; j++) {
            v.set(j, defaultValue);
        }
        return v;
    }

    public static Vector<Double> add(Vector<Double> p, Vector<Double> q) {
        checkDimensions(p, q);
        return forEachIndex(p, q, (i, j) -> i + j);
    }

    public static Vector<Double> subtract(Vector<Double> p, Vector<Double> q) {
        checkDimensions(p, q);
        return forEachIndex(p, q, (i, j) -> i - j);
    }

    public static Vector<Double> divide(Vector<Double> p, Vector<Double> q) {
        checkDimensions(p, q);
        return forEachIndex(p, q, (i, j) -> i / j);
    }

    public static Vector<Double> mult(Vector<Double> p, Vector<Double> q) {
        checkDimensions(p, q);
        return forEachIndex(p, q, (i, j) -> i * j);
    }

    private static <D extends Number> Vector<D> forEachIndex(Vector<D> p, Vector<D> q, BinaryOperator<D> operator) {
        Vector<D> d = newVector(p.size());
        for (int i = 0; i < p.size(); i++) {
            d.set(i, operator.apply(p.get(i), q.get(i)));
        }
        return d;
    }

    public static int euclideanDistance(Vector<Double> p, Vector<Double> q) {
        checkDimensions(p, q);
        return computeEuclideanDistance(p, q);
    }

    private static int computeEuclideanDistance(Vector<Double> p, Vector<Double> q) {
        int res = 0;

        for (int i = 0; i < q.size(); i++) {
            res += Math.pow(q.get(i) - p.get(i), 2);
        }

        double sqrt = Math.sqrt((double) res);
        return (int) Math.floor(sqrt);
    }

    private static void checkDimensions(Vector p, Vector q) {
        if (p.size() != q.size()) throw new IllegalArgumentException("vectors must be of equal dimensions");
    }
}
