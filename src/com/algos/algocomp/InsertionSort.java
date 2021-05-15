package com.algos.algocomp;

/**
 * @author Feyza Bulgurcu 150117033
 */
public class InsertionSort extends Sort {

    public InsertionSort(int[] list) {
        super(list);
    }

    @Override
    public long sort() {
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
        return duration;
    }
}
