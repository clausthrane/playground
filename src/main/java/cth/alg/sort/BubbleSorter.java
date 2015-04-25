package cth.alg.sort;

import java.util.List;

import static cth.utils.collections.ListUtils.smallerThanPredecessor;
import static cth.utils.collections.ListUtils.swap;

/**
 * Bubble topSort has worst-case and average complexity both О(n2), where n is the number of items being sorted.
 */
public class BubbleSorter<T extends Comparable> {

    private final List<T> list;

    public BubbleSorter(List<T> input) {
        this.list = input;
    }

    public List<T> bubbleSort() {

        boolean stillSwapping;
        do {
            stillSwapping = false;
            for (int i = 1; i < list.size(); i++) {
                if (smallerThanPredecessor(list, i)) {
                    swap(list, i, i - 1);
                    stillSwapping = true;
                }
            }
        } while (stillSwapping);

        return list;
    }
}
