package com.example.doorsensor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @param <T> 排序对象
 * @param <K> 排序依据的属性
 */
public abstract class SortUtils<T, K> {

    @SuppressWarnings("InnerClassMayBeStatic")
    private class Tuple<S>{
        S value;
        int index;
    }

    /**
     * 从对象中获取属性的方法
     */
    @SuppressWarnings("WeakerAccess")
    public abstract K get(T t);

    /**
     * 使用快排对数组分割的思想，得到前k个最小的元素
     */
    @SuppressWarnings("unchecked")
    public List<T> firstK(List<T> list, int k, Comparator<K> comparator) {
        if (k >= list.size() || list.size() <= 10) {
            list.sort((o1, o2) -> comparator.compare(get(o1), get(o2)));
            if (k >= list.size()) {
                return list;
            }
            return prune(list, k);
        }
        // 防止数据过多，只使用有用的字段进行排序
        Tuple[] tuples = new Tuple[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            Tuple<K> tuple = new Tuple<>();
            tuple.value = get(list.get(i));
            tuple.index = i;
            tuples[i] = tuple;
        }
        firstKSort(tuples, k, comparator);
        return prune(list, tuples, k);
    }

    private void firstKSort(Tuple<K>[] tuples, int k, Comparator<K> comparator) {
        int left = 0;
        int right = tuples.length - 1;
        while (k > 0) {
            int index = partition(tuples, left, right, k, comparator);
            int num = index + 1 - left;
            if (num <= k) {
                Arrays.sort(tuples, left, left + num,
                        (o1, o2) -> comparator.compare(o1.value, o2.value));
                k -= num;
                left = index + 1;
            } else {
                right = index;
            }
        }
    }

    @SuppressWarnings({"StatementWithEmptyBody", "unchecked"})
    private int partition(Tuple<K>[] tuples, int left, int right, int k, Comparator<K> comparator) {
        Tuple<K> pivot = pickPivot(tuples, left, right, comparator);
        if (right - left + 1 < 4) {
            // 因为在pickPivot中将mid与right - 1进行了交换
            // 直接返回的不是排好序的数组，需要重新排序
            swap(tuples, (right + left + 1) / 2, right - 1);
            return left + k - 1;
        }
        int i = left;
        int j = right - 1;
        while (true) {
            while (comparator.compare(tuples[++i].value, pivot.value) < 0);
            while (comparator.compare(tuples[--j].value, pivot.value) > 0);
            if (i < j) {
                swap(tuples, i, j);
            } else {
                break;
            }
        }
        swap(tuples, i, right - 1);
        return i;
    }

    private Tuple pickPivot(Tuple<K>[] tuples, int left, int right, Comparator<K> comparator) {
        int mid = (left + right + 1) / 2;
        if(comparator.compare(tuples[left].value, tuples[mid].value) > 0) {
            swap(tuples, left, mid);
        }
        if (comparator.compare(tuples[left].value, tuples[right].value) > 0) {
            swap(tuples, left, right);
        }
        if (comparator.compare(tuples[mid].value, tuples[right].value) > 0) {
            swap(tuples, mid, right);
        }
        swap(tuples, mid, right - 1);
        return tuples[right - 1];
    }

    private List prune(List<T> list, int size) {
        List<T> resList = new ArrayList<>();
        int len = Math.min(size, list.size());
        for (int i = 0; i < len; i++) {
            resList.add(list.get(i));
        }
        return resList;
    }

    private List prune(List<T> list, Tuple<K>[] tuples, int size) {
        List<T> resList = new ArrayList<>();
        int len = Math.min(size, tuples.length);
        for (int i = 0; i < len; i++) {
            int index = tuples[i].index;
            resList.add(list.get(index));
        }
        return resList;
    }

    private void swap(Tuple<K>[] tuples, int i, int j) {
        Tuple<K> temp = tuples[i];
        tuples[i] = tuples[j];
        tuples[j] = temp;
    }
}
