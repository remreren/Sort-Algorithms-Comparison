package com.algos.algocomp;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final int MAX_VALUE = 10000;
    private static final int TRIAL_NUMBERS = 5;
    private static final int SIZE_CASE_COUNT = 10;
    private static final int CASE_COUNT = 3;

    private static final String[] names = new String[]{"Hoares QuickSort", "3way QuickSort", "Counting Sort", "BinaryInsertion Sort", "Insertion Sort", "HeapSort", "MergeSort"};

    public static void main(String[] args) {

        int cons = 10000;
        int growth = 1;

        /**
         * Best case for Quicksort is at random state. Works in O(nlogn) time complexity.
         * Worst case for Quicksort is at sorted or reverse sorted state. Works in O(n^2) time complexity.
         *
         * Best case for 3-way QuickSort is at random state. Works in O(nlogn) time complexity.
         * Worst case for 3-way QuickSort is at sorted or reverse sorted state. Works in O(n^2) time complexity.
         *
         * Best case and worst case does not matter for Counting sort. Everytime it gives us an O(n+k) time complexity.
         *
         * Best case for BinaryInsertion Sort is at sorted state. Works in O(n) time complexity.
         * Worst case for BinaryInsertion Sort is at reverse sorted state. Works in O(n^2) time complexity.
         *
         * Best case for Insertion Sort is at sorted state. Works in O(n) time complexity.
         * Worst case for Insertion Sort is at reverse sorted state. Works in O(n^2) time complexity.
         *
         * Best case and worst case does not matter for HeapSort. Always works in O(nlogn) time complexity.
         *
         * Best case and worst case does not matter for MergeSort. Always works in O(nlogn) time complexity.
         */

        // int[size case count][case count][size]
        int[][][] lists = new int[SIZE_CASE_COUNT][CASE_COUNT][];
        for (int i = 0; i < lists.length; i++) {
            int length = cons * (1 + i * growth);
            Random random = new Random(5621);

            lists[i][0] = new int[length];
            lists[i][1] = new int[length];
            lists[i][2] = new int[length];

            for (int j = 0; j < lists[i][0].length; j++) lists[i][0][j] = random.nextInt(MAX_VALUE);
            for (int j = 0; j < lists[i][1].length; j++)
                lists[i][1][lists[i][1].length - j - 1] = (int) (((lists[i][1].length - (double) j) / lists[i][1].length) * (MAX_VALUE - 1));
            for (int j = 0; j < lists[i][2].length; j++)
                lists[i][2][j] = (int) (((lists[i][2].length - (double) j) / lists[i][2].length) * (MAX_VALUE - 1));
        }

        long[][][][] results = new long[TRIAL_NUMBERS][SIZE_CASE_COUNT][CASE_COUNT][];

        for (int k = 0; k < results.length; k++) {
            for (int i = 0; i < lists.length; i++) {
                for (int j = 0; j < lists[i].length; j++) {
                    results[k][i][j] = sortAllReturn(lists[i][j], j == 0 ? "Random List Elements" : j == 1 ? "Sorted List Elements" : "Reverse Sorted List Elements");
                }
            }
        }

        for (int i = 0; i < results.length; i++) {
            System.out.printf("Result #%d\n", i + 1);
            for (int j = 0; j < results[i].length; j++) {
                System.out.printf("\t> Element size: %d\n", lists[j][0].length);
                for (int k = 0; k < results[i][j].length; k++) {
                    System.out.printf("\t\t> Case %d - %s\n", k + 1, k == 0 ? "Random List Elements" : k == 1 ? "Sorted List Elements" : "Reverse Sorted List Elements");
                    for (int m = 0; m < results[i][j][k].length; m++) {
                        System.out.printf("\t\t\t- %s : %d ns / %.2f ms\n", names[m], results[i][j][k][m], (double) results[i][j][k][m] / 1000000);
                    }
                }
            }
        }

        // list[sort count][trial number][size case count][case count]

        long[][][][] sunum = new long[TRIAL_NUMBERS][names.length][SIZE_CASE_COUNT][CASE_COUNT];

        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results[i].length; j++) {
                for (int k = 0; k < results[i][j].length; k++) {
                    for (int m = 0; m < results[i][j][k].length; m++) {
                        sunum[i][m][j][k] = results[i][j][k][m];
                    }
                }
            }
        }

        try {
            FileWriter str = new FileWriter("data.csv");
            for (int i = 0; i < sunum.length; i++) {
                str.append("\n,Trial #").append(String.format("%d\n,", i + 1));
                for (int k = 0; k < sunum[i][0].length; k++) for(int m = 0; m < sunum[i][0][k].length; m++) str.append(String.format("%dK,", lists[k][m].length / 1000));
                str.append("\n,");
                for (int k = 0; k < sunum[i][0].length; k++) for(int m = 0; m < sunum[i][0][k].length; m++) str.append(m == 0 ? "Random," : m == 1 ? "Sorted," : "Reverse Sorted,");
                str.append("\n");
                for (int j = 0; j < sunum[i].length; j++) {
                    str.append(names[j]).append(",");
                    for (int k = 0; k < sunum[i][j].length; k++) {
                        for (int m = 0; m < sunum[i][j][k].length; m++) {
                            str.append(String.format("%.2f,", (double) sunum[i][j][k][m] / 1000000));
                        }
                    }
                    str.append("\n");
                }
            }

            str.flush();
            str.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long[] sortAllReturn(int[] rdList, String note) {

        System.out.println("#########");
        System.out.printf("Element Size: %d\n", rdList.length);
        System.out.println(note);

        QuickSort hoarequickSort = null;

        try {
            hoarequickSort = CreateSort_QuickSort(rdList);
            System.out.println("Quicksort/Hoares:");
            System.out.printf("\tDuration: %dns/%dms\n", hoarequickSort.getDuration(), Math.round((double) hoarequickSort.getDuration() / 1000000));
            System.out.println(hoarequickSort.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        QuickSort quickSort3way = null;

        try {
            quickSort3way = CreateSort_QuickSort(rdList, "dd");
            System.out.println("Quicksort/3way:");
            System.out.printf("\tDuration: %dns/%dms\n", quickSort3way.getDuration(), Math.round((double) quickSort3way.getDuration() / 1000000));
            System.out.println(quickSort3way.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        CountingSort countingSort = CreateSort_CountingSort(rdList);
        System.out.println("Counting sort:");
        System.out.printf("\tDuration: %dns/%dms\n", countingSort.getDuration(), Math.round((double) countingSort.getDuration() / 1000000));
        System.out.println(countingSort.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");

        BinaryInsertionSort binaryinsertion = CreateSort_BinaryInsertionSort(rdList);
        System.out.println("BinaryInsertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", binaryinsertion.getDuration(), Math.round((double) binaryinsertion.getDuration() / 1000000));
        System.out.println(binaryinsertion.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");

        InsertionSort insertion = CreateSort_InsertionSort(rdList);
        System.out.println("Insertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", insertion.getDuration(), Math.round((double) insertion.getDuration() / 1000000));
        System.out.println(insertion.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");

        HeapSort heap = CreateSort_HeapSort(rdList);
        System.out.println("HeapSort:");
        System.out.printf("\tDuration: %dns/%dms\n", heap.getDuration(), Math.round((double) heap.getDuration() / 1000000));
        System.out.println(heap.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");

        MergeSort merge = CreateSort_MergeSort(rdList);
        System.out.println("MergeSort:");
        System.out.printf("\tDuration: %dns/%dms\n", merge.getDuration(), Math.round((double) merge.getDuration() / 1000000));
        System.out.println(merge.isSorted() ? "\tSorted successfully!" : "\tCannot be sorted!");

        System.out.println("#########");
        return new long[]{hoarequickSort != null ? hoarequickSort.getDuration() : 0, quickSort3way != null ? quickSort3way.getDuration() : 0, countingSort.getDuration(), binaryinsertion.getDuration(), insertion.getDuration(), heap.getDuration(), merge.getDuration()};
    }

    static QuickSort CreateSort_QuickSort(int[] arr) {
        return CreateSort_QuickSort(arr, "hoares");
    }

    static QuickSort CreateSort_QuickSort(int[] arr, String type) {
        QuickSort quickSort = new QuickSort(Arrays.copyOf(arr, arr.length));
        quickSort.sort(type);
        return quickSort;
    }

    static CountingSort CreateSort_CountingSort(int[] arr) {
        CountingSort countingSort = new CountingSort(Arrays.copyOf(arr, arr.length));
        countingSort.sort();
        return countingSort;
    }

    static BinaryInsertionSort CreateSort_BinaryInsertionSort(int[] arr) {
        BinaryInsertionSort binaryInsertionSort = new BinaryInsertionSort(Arrays.copyOf(arr, arr.length));
        binaryInsertionSort.sort();
        return binaryInsertionSort;
    }

    static InsertionSort CreateSort_InsertionSort(int[] arr) {
        InsertionSort insertionSort = new InsertionSort(Arrays.copyOf(arr, arr.length));
        insertionSort.sort();
        return insertionSort;
    }

    static HeapSort CreateSort_HeapSort(int[] arr) {
        HeapSort heapSort = new HeapSort(Arrays.copyOf(arr, arr.length));
        heapSort.sort();
        return heapSort;
    }

    static MergeSort CreateSort_MergeSort(int[] arr) {
        MergeSort mergeSort = new MergeSort(Arrays.copyOf(arr, arr.length));
        mergeSort.sort();
        return mergeSort;
    }
}
