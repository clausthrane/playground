package cth.alg.sort;

import java.util.List;

import static cth.utils.collections.ListUtils.smallerThanPredecessor;
import static cth.utils.collections.ListUtils.swap;

/**
 * http://en.wikipedia.org/wiki/Insertion_sort
 *
 * Adaptive, i.e., efficient for data sets that are already substantially sorted: the time complexity is O(nk) when each element in the input is no more than k places away from its sorted position
 * Stable; i.e., does not change the relative order of elements with equal keys
 * In-place; i.e., only requires a constant amount O(1) of additional memory space
 * Online; i.e., can insertionSort a list as it receives it
 */
public class InsertionSorter<T extends Comparable> {

    private final List<T> list;

    public InsertionSorter(List<T> input) {
        list = input;
    }

    public List<T> insertionSort() {
        for (int forwardIndex = 0; forwardIndex < list.size(); forwardIndex++) {
            sortBackwards(forwardIndex);
        }

        return list;
    }

    void sortBackwards(int upto) {
        for (int i = upto; i > 0; i--) {
            if (smallerThanPredecessor(list, i)) {
                swap(list, i, i - 1);
            }
        }
    }



}
