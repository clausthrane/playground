package cth.utils.collections;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

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

    public static <T> T next(List<T> list, int idx) {
        return list.get(idx + 1);
    }

    public static <T> T prev(List<T> list, int idx) {
        return list.get(idx - 1);
    }

    public static <T> T findRandom(List<T> list) {
        return takeRandom(list, false);
    }

    public static <T> T takeRandom(List<T> list) {
        return takeRandom(list, true);
    }

    private static <T> T takeRandom(List<T> list, boolean remove) {
        checkArgument(CollectionUtils.isNotEmpty(list), "List cannot be empty");
        int i = RandomUtils.nextInt(list.size());
        return remove ? list.remove(i) : list.get(i);
    }

    public static <T> List<T> randomSubList(int size, List<T> list) {
        List<T> copy = Lists.newArrayList(list);
        List<T> sublist = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            sublist.add(i, takeRandom(copy));
        }
        return sublist;
    }
}
