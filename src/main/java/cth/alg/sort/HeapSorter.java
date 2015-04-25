package cth.alg.sort;

import cth.utils.collections.ListUtils;

import java.util.List;

/**
 * http://en.wikipedia.org/wiki/Heapsort
 * <p>
 * Although somewhat slower in practice on most machines than a well-implemented quicksort,
 * it has the advantage of a more favorable worst-case O(n log n) runtime.
 * <p>
 * Heapsort is an in-place algorithm, but it is not a stable topSort.
 */
public class HeapSorter<T extends Comparable> {

    private final List<T> list;

    public HeapSorter(List<T> input) {
        list = input;
    }

    public List<T> heapSort() {
        heapify();

        int end = list.size() - 1;
        while (end > 0) {
            ListUtils.swap(list, 0, end);
            end--;
            siftDown(0, end);
        }


        return this.list;
    }

    /**
     * Builds a binary heap, st. for each index idx, leftChild(idx) / rightChild(idx) are the children
     * heapify starts from the "last" parent, the parent with the highest possible index i.e. the parent of size - 1
     * and works it's way up the tree
     */
    void heapify() {
        int start = parent(list.size());

        while (start > 0) {
            siftDown(start, list.size() - 1);
            start--;
        }
    }

    /**
     * fix heap at root, st. all children are smaller than their parent
     * it assumes that only the root violates the property
     *
     * @param low  the root
     * @param high upper bound on the possible children
     */
    void siftDown(int low, int high) {

        int root = low;

        while (leftChild(root) <= high) {

            int swap = largestValue(root, leftChild(root));

            if (rightChild(root) <= high) {
                swap = largestValue(swap, rightChild(root));
            }

            if (swap == root) {
                return;
            } else {
                ListUtils.swap(list, root, swap);
                root = swap;
            }
        }
    }

    int leftChild(int parent) {
        return (parent * 2) + 1;
    }

    int rightChild(int parent) {
        return leftChild(parent) + 1;
    }

    int parent(int idx) {
        return (idx - 2) / 2;
    }

    int largestValue(int i, int j) {
        return list.get(i).compareTo(list.get(j)) < 0 ? i : j;
    }

    List<T> getList() {
        return list;
    }
}
