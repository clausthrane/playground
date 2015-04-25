package cth.alg.sort;

import java.util.List;

public class QuickSorter<T extends Comparable> {

    final private List<T> list;

    public QuickSorter(List<T> list) {
        this.list = list;
    }

    public List<T> quickSort() {
        return quickSort(0, list.size() - 1);
    }

    public List<T> quickSort(int low, int high) {
        if (low < high) {
            int partition = partition(low, high); // values left/right of pivot may be equal, low/high can be optimized
            quickSort(low, partition - 1);
            quickSort(partition + 1, high);
        }
        return this.list;
    }

    /**
     * Positions the pivo element in the lowest index st.
     * all elements to the left are smaller and all to the right are bigger
     * @param low
     * @param high
     * @return
     */
    int partition(int low, int high) {
        Pivot pivot = Pivot.choose(list, low, high);

        swap(pivot.getIdx(), high);

        int storeIdx = low;
        for (int i = low; i < high; i++) {
            T other = list.get(i);
            if (pivot.isBigger(other)) {
                swap(storeIdx, i);
                storeIdx++;
            }
        }

        swap(storeIdx, high);
        return storeIdx;
    }

    void swap(int j, int i) {
        T a = list.get(i);
        list.set(i, list.get(j));
        list.set(j, a);
    }

    public List<T> getList() {
        return list;
    }
}
