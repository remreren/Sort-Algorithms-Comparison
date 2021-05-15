package com.algos.algocomp;

/**
 * @author Feyza Bulgurcu 150117033
 */
public class BinaryInsertionSort extends Sort {

    public BinaryInsertionSort(int[] list) {
        super(list);
    }

    @Override
    public long sort() {
        long startTime = System.nanoTime();
        for (int i = 1; i < list.length; ++i) {
            int key = list[i];
            int insertedPosition = searchPosition(0, i - 1, key);

            for (int j = i - 1; j >= insertedPosition; --j) list[j + 1] = list[j];

            list[insertedPosition] = key;
        }

        duration = System.nanoTime() - startTime;
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
}
