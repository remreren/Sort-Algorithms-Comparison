package com.algos.algocomp;

public class InsertionSort {
    private final int[] list;
    private long duration;

    public InsertionSort(int[] list) {
        this.list = list;
    }

    void sort() {
        long startTime = System.nanoTime();
        int e = list.length;
        for (int i = 1; i < e; i++) {
            int key = list[i];
            int j = i - 1;
            while ((j > -1) && (list[j] > key)) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
        duration = System.nanoTime() - startTime;
    }

    public long getDuration() {
        return duration;
    }

    public int[] getList() {
        return list;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
