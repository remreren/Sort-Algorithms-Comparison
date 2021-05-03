package com.algos.algocomp;

public class CountingSort {
    private int[] list;

    public CountingSort(int[] list) {
        this.list = list;
    }

    void sort() {
        int[] c = new int[10000];
        int[] b = new int[list.length];

        for (int i = 0; i < list.length; i++) c[list[i]] += 1;
        for (int i = 1; i < 10000; i++) c[i] += c[i - 1];
        for (int i = list.length - 1; i >= 0; i--) b[--c[list[i]]] = list[i];
        list = b;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
