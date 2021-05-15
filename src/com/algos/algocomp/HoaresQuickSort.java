package com.algos.algocomp;

/**
 * @author Emre Eren 150118020
 */
public class HoaresQuickSort extends Sort {

    public HoaresQuickSort(int[] list) {
        super(list);
    }

    @Override
    public long sort() {
        long startTime = System.nanoTime();
        sortHoare(0, list.length - 1);
        duration = System.nanoTime() - startTime;
        return duration;
    }

    void sortHoare(int leftP, int rightP) {
        while (leftP < rightP) {
            int pivot = list[rightP];
            int partition = hoarePartition(leftP, rightP, pivot);

            if (partition - leftP < rightP - partition) {
                sortHoare(leftP, partition - 1);
                leftP = partition + 1;
            } else {
                sortHoare(partition + 1, rightP);
                rightP = partition - 1;
            }

//            sortHoare(leftP, partition - 1);
//            leftP = partition + 1;

//            sortHoare(leftP, partition - 1);
//            sortHoare(partition + 1, rightP);

//            if (leftP < partition) {
//                sortHoare(leftP, partition - 1);
//            } if (rightP > partition) {
//                sortHoare(partition + 1, rightP);
//            }
        }
    }

    int hoarePartition(int left, int right, int pivot) {
        int leftP = left;
        int rightP = right - 1;

        while (true) {
            while (list[leftP] < pivot) leftP++;
            while (rightP > 0 && list[rightP] >= pivot) rightP--;

            if (leftP >= rightP) break;
            else swap(leftP, rightP);
        }

        swap(leftP, right);
        return leftP;
    }
}
