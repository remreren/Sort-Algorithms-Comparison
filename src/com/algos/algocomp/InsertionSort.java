package com.algos.algocomp;

public class InsertionSort {
    private int[] list;
    private long duration;

    public InsertionSort(int[] list) {
        this.list = list;
    }

    void sort() {
        long startTime = System.nanoTime();
        int e = list.length;
        for (int j = 1; j < e; j++) {
            int key = list[j];
            int i = j - 1;
            while ((i > -1) && (list[i] > key)) {
                list[i + 1] = list[i];
                i--;
            }
            list[i + 1] = key;
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
