package com.algos.algocomp;

/**
 * @author Edanur Öztürk 150117007
 */
public class MergeSort extends Sort {

    public MergeSort(int[] list) {
        super(list);
    }

    @Override
    public long sort() {
        long startTime = System.nanoTime();
        mergeSort(0, list.length - 1);
        duration = System.nanoTime() - startTime;
        return duration;
    }

    public void mergeSort(int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(left, middle);
            mergeSort(middle + 1, right);
            merge(left, middle, right);
        }
    }

    private void merge(int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftA = new int[n1], rightA = new int[n2];

        for (int i = 0; i < n1; i++)
            leftA[i] = list[left + i];
        for (int i = 0; i < n2; i++)
            rightA[i] = list[middle + i + 1];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftA[i] <= rightA[j]) list[k] = leftA[i++];
            else list[k] = rightA[j++];
            k++;
        }

        while (i < n1) {
            list[k++] = leftA[i++];
        }

        while (j < n2) {
            list[k++] = rightA[j++];
        }
    }
}
