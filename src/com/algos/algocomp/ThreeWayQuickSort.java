package com.algos.algocomp;

/**
 * @author Emre Eren 150118020
 */
public class ThreeWayQuickSort extends Sort {

    public ThreeWayQuickSort(int[] list) {
        super(list);
    }

    @Override
    long sort() {
        long startTime = System.nanoTime();
        sortDeutschNationalFlag(0, list.length - 1);
        duration = System.nanoTime() - startTime;
        return duration;
    }

    private void sortDeutschNationalFlag(int leftP, int rightP) {
        if (rightP > leftP) {
            if (leftP - rightP == 1) {
                if (list[rightP] > list[leftP]) swap(rightP, leftP);
                return;
            }

            int pivot = list[rightP];
            int[] partitions = deutschNationalFlagPartition(leftP, rightP, pivot);
            sortDeutschNationalFlag(leftP, partitions[0]);
            sortDeutschNationalFlag(partitions[1], rightP);
        }
    }

    int[] deutschNationalFlagPartition(int left, int right, int pivot) {
        int mid = left;

        while (mid <= right) {
            if (list[mid] < pivot) swap(left++, mid++);
            else if (list[mid] > pivot) swap(mid, right--);
            else mid++;
        }

        return new int[]{left - 1, mid};
    }
}
