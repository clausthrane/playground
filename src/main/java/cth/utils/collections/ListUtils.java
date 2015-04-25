package cth.utils.collections;

import java.util.List;

/**
 * Created by thrane on 22/04/15.
 */
public class ListUtils {

    public static <T> void swap(List<T> list, int j, int i) {
        T a = list.get(i);
        list.set(i, list.get(j));
        list.set(j, a);
    }

    public static <T extends Comparable> boolean smallerThanPredecessor(List<T> list, int idx) {
        T predecessor = list.get(idx - 1);
        T item = list.get(idx);
        return predecessor.compareTo(item) > 0;
    }

    public static <T extends Comparable> T next(List<T> list, int idx) {
        return list.get(idx + 1);
    }

    public static <T extends Comparable> T prev(List<T> list, int idx) {
        return list.get(idx - 1);
    }
}
