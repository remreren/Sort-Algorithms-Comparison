package com.algos.algocomp;

public class CountingSort {
    private int[] list;
    private long duration;

    public CountingSort(int[] list) {
        this.list = list;
    }

    void sort() {
        long startTime = System.nanoTime();
        int[] c = new int[Main.MAX_VALUE];
        int[] b = new int[list.length];

        for (int i = 0; i < list.length; i++) c[list[i]] += 1;
        for (int i = 1; i < Main.MAX_VALUE; i++) c[i] += c[i - 1];
        for (int i = list.length - 1; i >= 0; i--) b[--c[list[i]]] = list[i];
        list = b;
        duration = System.nanoTime() - startTime;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
