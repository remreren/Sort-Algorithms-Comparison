package com.algos.algocomp;

public class BinaryInsertionSort {
    private final int[] list;
    private long duration;

    public BinaryInsertionSort(int[] list) {
        this.list = list;
    }

    void sort() {
        long startTime = System.nanoTime();
        for (int i = 1; i < list.length; ++i) {
            int key = list[i];
            int insertedPosition = searchPosition(0, i - 1, key);

            if (i - insertedPosition >= 0)
                System.arraycopy(list, insertedPosition, list, insertedPosition + 1, i - insertedPosition);

            list[insertedPosition] = key;
        }

        duration = System.nanoTime() - startTime;
    }

    public long getDuration() {
        return duration;
    }

    public int searchPosition(int start, int last, int key) {
        while (start <= last) {
            int middle = start + (last - start) / 2;

            if (key < list[middle]) last = middle - 1;
            else start = middle + 1;
        }

        return start;
    }

    public int[] getList() {
        return list;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
