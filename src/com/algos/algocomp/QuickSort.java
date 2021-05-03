package com.algos.algocomp;

import java.util.Arrays;
import java.util.Locale;

public class QuickSort {

    private int[] list;
    private int[] listOriginal;
    private String partition;

    public QuickSort(int[] list) {
        this.listOriginal = list;
        reset();
    }

    void reset() {
        partition = null;
        list = Arrays.copyOf(listOriginal, listOriginal.length);
    }

    boolean isSorted() {
        return Test.isSorted(list);
    }

    void sort(String partition) {
        this.partition = partition;

        if (partition.toLowerCase(Locale.forLanguageTag("TR")).equals("hoare") || partition.toLowerCase(Locale.forLanguageTag("TR")).equals("hoares"))
            sortHoare(0, list.length - 1);
        else if (partition.toLowerCase(Locale.forLanguageTag("TR")).equals("lomuto") || partition.toLowerCase(Locale.forLanguageTag("TR")).equals("lomutos"))
            sortLomuto(0, list.length - 1);
        else sortDeutchNationalFlag(0, list.length - 1);
    }

    void sortDeutchNationalFlag(int leftP, int rightP) {
        if (rightP <= leftP) return;
        else {
            if (leftP - rightP == 1) {
                if (list[rightP] > list[leftP]) swap(rightP, leftP);
                return;
            }

            int pivot = list[rightP];
            int[] partitions = deutchNationalFlagPartition(leftP, rightP, pivot);
            sortDeutchNationalFlag(leftP, partitions[0]);
            sortDeutchNationalFlag(partitions[1], rightP);
        }
    }

    int[] deutchNationalFlagPartition(int left, int right, int pivot) {
        int mid = left;

        while (mid <= right) {
            if (list[mid] < pivot) swap(left++, mid++);
            else if (list[mid] > pivot) swap(mid, right--);
            else mid++;
        }

        return new int[]{left - 1, mid};
    }

    void sortHoare(int leftP, int rightP) {
        if (rightP <= leftP) return;
        else {
            int pivot = list[rightP];
            int partition = hoarePartition(leftP, rightP, pivot);
            sortHoare(leftP, partition - 1);
            sortHoare(partition + 1, rightP);
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

    void sortLomuto(int leftP, int rightP) {
        if (rightP < leftP) return;
        else {
            int pivot = list[rightP];
            int partition = lomutoPartition(leftP, rightP, pivot);
            sortLomuto(leftP, partition - 1);
            sortLomuto(partition + 1, rightP);
        }
    }

    int lomutoPartition(int left, int right, int pivot) {
        int leftP = left - 1;

        for (int i = left; i <= right - 1; i++) {
            if (list[i] <= pivot) swap(++leftP, i);
        }

        swap(leftP + 1, right);
        return leftP + 1;
    }

    void swap(int p1, int p2) {
        int tempVal = list[p1];
        list[p1] = list[p2];
        list[p2] = tempVal;
    }

    public int[] getList() {
        return list;
    }
}
