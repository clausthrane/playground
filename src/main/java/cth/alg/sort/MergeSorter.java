package cth.alg.sort;


import com.google.common.collect.Lists;

import java.util.List;

public class MergeSorter<T extends Comparable> {

    private final List<T> input;

    public MergeSorter(List<T> input) {
        this.input = input;
    }

    public List<T> mergeSort() {
        return mergeSort(this.input);
    }

    List<T> mergeSort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }

        int low = 0;
        int mid = list.size() / 2;
        int high = list.size() - 1;


        List<T> leftSplit = list.subList(low, mid);
        List<T> rightSplit = list.subList(mid, high + 1);

        List<T> left = Lists.newArrayList(mergeSort(leftSplit));
        List<T> right = Lists.newArrayList(mergeSort(rightSplit));

        return merge(left, right);
    }

    List<T> merge(List<T> left, List<T> right) {
        List<T> result = Lists.newArrayList();

        while (!left.isEmpty() && !right.isEmpty()) {
            T smallest = removeSmallest(left, right);
            result.add(smallest);
        }

        result.addAll(left);
        result.addAll(right);

        return result;
    }


    T removeSmallest(List<T> left, List<T> right) {
        if (leftIsSmaller(left, right)) {
            return left.remove(0);
        } else {
            return right.remove(0);
        }
    }

    boolean leftIsSmaller(List<T> left, List<T> right) {
        T leftElement = left.get(0);
        T rightElement = right.get(0);
        return (leftElement.compareTo(rightElement) <= 0);
    }

}
