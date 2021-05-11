package com.algos.algocomp;

public class MergeSort {
    private int[] list;
    private long duration = 0;

    public MergeSort(int[] list) {
        this.list = list;
    }

    public void sort() {
        long startTime = System.nanoTime();
        mergeSort(0, list.length - 1);
        duration = System.nanoTime() - startTime;
    }

    public void mergeSort(int lowerIndex, int upperIndex) {
        if (lowerIndex != upperIndex) {
            int middleIndex = (lowerIndex + upperIndex) / 2;
            mergeSort(lowerIndex, middleIndex);
            mergeSort(middleIndex + 1, upperIndex);
            merge(lowerIndex, middleIndex + 1, upperIndex);
        }
    }

    private void merge(int lowerOne, int higherOne, int upperIndex) {
        int temporaryIndex = 0;
        int lowerIndex = lowerOne;
        int middleIndex = higherOne - 1;
        int totalNumberOfElements = upperIndex - lowerIndex + 1;

        while (lowerIndex <= middleIndex && higherOne <= upperIndex) {
            if (list[lowerIndex] < list[higherOne]) {
                list[temporaryIndex++] = list[lowerIndex++];
            } else {
                list[temporaryIndex++] = list[higherOne++];
            }
        }

        while (lowerIndex <= middleIndex) {
            list[temporaryIndex++] = list[lowerIndex++];
        }

        while (higherOne <= upperIndex) {
            list[temporaryIndex++] = list[higherOne++];
        }

        if (totalNumberOfElements >= 0) System.arraycopy(list, 0, list, lowerOne, totalNumberOfElements);
    }

    public long getDuration() {
        return duration;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
