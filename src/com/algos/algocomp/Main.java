package com.algos.algocomp;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random(5621);
        int[] rdList = new int[500];

        for (int i = 0; i < rdList.length; i++) {
            rdList[i] = random.nextInt(10000);
        }

        QuickSort quickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        System.out.println(System.currentTimeMillis());
        quickSort.sort("dd");
        System.out.println(System.currentTimeMillis());
        boolean isSorted = quickSort.isSorted();
        System.out.println(isSorted ? "Sorted successfully!": "Cannot be sorted!");

        CountingSort countingSort = new CountingSort(Arrays.copyOf(rdList, rdList.length));

        System.out.println(System.currentTimeMillis());
        countingSort.sort();
        System.out.println(System.currentTimeMillis());
        boolean isSorted1 = countingSort.isSorted();
        System.out.println(isSorted1 ? "Sorted successfully!": "Cannot be sorted!");

        System.out.println(Arrays.toString(quickSort.getList()));
    }
}
