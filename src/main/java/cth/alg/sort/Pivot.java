package cth.alg.sort;

import cth.utils.math.MathUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.List;

public class Pivot<T extends Comparable> {

    private final T pivot;
    private final int idx;

    public static <T extends Comparable> Pivot choose(List<T> list, int low, int high) {
        int idx = chooseMiddleIdx(low, high);
        T p = list.get(idx);
        return new Pivot(p, idx);
    }

    static int chooseMiddleIdx(int low, int high) {
        return MathUtils.middle(low, high);
    }

    static int chooseRandomIdx(int low, int high) {
        int offset = RandomUtils.nextInt(high - low);
        return low + offset;
    }

    Pivot(T pivot, int idx) {
        this.idx = idx;
        this.pivot = pivot;
    }

    boolean isBigger(T other) {
        return pivot.compareTo(other) > 0;
    }

    int getIdx() {
        return this.idx;
    }

    T get() {
        return this.pivot;
    }

    @Override
    public String toString() {
        return "[" + idx + ", " + pivot.toString() + "]";
    }
}
