package com.algos.algocomp;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final int MAX_VALUE = 10000;

    private static final String[] names = new String[]{"Hoares QuickSort", "3way QuickSort", "Counting Sort", "BinaryInsertion Sort", "Insertion Sort", "HeapSort", "MergeSort"};

    public static void main(String[] args) {

        int cons = 1000;
        int growth = 10;

        int[][][] rdList = new int[3][3][];
        for (int i = 0; i < rdList.length; i++) {
            int calculatedLen = cons * (int) Math.pow(growth, i + 1);
            Random random = new Random(5621);

            rdList[i][0] = new int[calculatedLen];
            rdList[i][1] = new int[calculatedLen];
            rdList[i][2] = new int[calculatedLen];

            for (int j = 0; j < rdList[i][0].length; j++) rdList[i][0][j] = random.nextInt(MAX_VALUE);
            for (int j = 0; j < rdList[i][1].length; j++)
                rdList[i][1][rdList[i][1].length - i - 1] = (int) (((rdList[i][1].length - (float) i) / rdList[i][1].length) * (MAX_VALUE - 1));
            for (int j = 0; j < rdList[i][2].length; j++)
                rdList[i][1][i] = (int) (((rdList[i][2].length - (float) i) / rdList[i][2].length) * (MAX_VALUE - 1));
        }

        long[][][][] results = new long[3][rdList.length][rdList[0].length][];

        for (int k = 0; k < results.length; k++) {
            for (int i = 0; i < rdList.length; i++) {
                for (int j = 0; j < rdList[i].length; j++) {
                    results[k][i][j] = sortAllReturn(rdList[i][j], j == 0 ? "Random List Elements" : j == 1 ? "Sorted List Elements" : "Reverse Sorted List Elements");
                }
            }
        }

        for (int i = 0; i < results.length; i++) {
            System.out.printf("Result #%d\n", i + 1);
            for (int j = 0; j < results[i].length; j++) {
                System.out.printf("\t> Element size: %d\n", rdList[j][i].length);
                for (int k = 0; k < results[i][j].length; k++) {
                    System.out.printf("\t\t> Case %d - %s\n", k + 1, k == 0 ? "Random List Elements" : k == 1 ? "Sorted List Elements" : "Reverse Sorted List Elements");
                    for (int m = 0; m < results[i][j][k].length; m++) {
                        System.out.printf("\t\t\t- %s : %d ns / %.2f ms\n", names[m], results[i][j][k][m], (double) results[i][j][k][m] / 1000000);
                    }
                }
            }
        }

//        for (int j = 0; j < rdList[2].length; j++) {
//            sortAllReturn(rdList[2][j], j == 0 ? "Random List Elements" : j == 1 ? "Sorted List Elements" : "Reverse Sorted List Elements");
//        }

//        sortAll(rdList[2][0], "Random List Elements");

//        int[] rdList1 = new int[50000];
//        int[] rdList2 = new int[50000];
//        int[] rdList3 = new int[50000];
//
//        for (int i = 0; i < rdList1.length; i++) rdList1[i] = random.nextInt(MAX_VALUE);
//        for (int i = 0; i < rdList2.length; i++) rdList2[rdList3.length - i - 1] = (int)(((rdList2.length - (float) i) / rdList2.length) * (MAX_VALUE - 1));
//        for (int i = 0; i < rdList3.length; i++) rdList3[i] = (int)(((rdList3.length - (float) i) / rdList3.length) * (MAX_VALUE - 1));
//
//        sortAll(rdList1, "Random List Elements");
//        sortAll(rdList2, "Sorted List Elements");
//        sortAll(rdList3, "Reverse Sorted List Elements");
    }

    static void sortAll(int[] rdList, String note) {

        System.out.println("#########");
        System.out.printf("Element Size: %d\n", rdList.length);
        System.out.println(note);

        QuickSort hoarequickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        try {
            hoarequickSort.sort("hoares");
            boolean isSorted = hoarequickSort.isSorted();
            System.out.println("Quicksort/Hoares:");
            System.out.printf("\tDuration: %dns/%dms\n", hoarequickSort.getDuration(), Math.round((double) hoarequickSort.getDuration() / 1000000));
            System.out.println(isSorted ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

//        QuickSort lomutoquickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));
//
//        try {
//            lomutoquickSort.sort("lomutos");
//            boolean isSorted6 = lomutoquickSort.isSorted();
//            System.out.println("Quicksort/Lomutos:");
//            System.out.printf("\tDuration: %dns/%dms\n", lomutoquickSort.getDuration(), Math.round((double) lomutoquickSort.getDuration() / 1000000));
//            System.out.println(isSorted6 ? "\tSorted successfully!" : "\tCannot be sorted!");
//        } catch (StackOverflowError e) {
//            System.out.println("Problem!");
//        }

        QuickSort quickSort3way = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        try {
            quickSort3way.sort("dd");
            boolean isSorted5 = quickSort3way.isSorted();
            System.out.println("Quicksort/3way:");
            System.out.printf("\tDuration: %dns/%dms\n", quickSort3way.getDuration(), Math.round((double) quickSort3way.getDuration() / 1000000));
            System.out.println(isSorted5 ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        CountingSort countingSort = new CountingSort(Arrays.copyOf(rdList, rdList.length));

        countingSort.sort();
        boolean isSorted1 = countingSort.isSorted();
        System.out.println("Counting sort:");
        System.out.printf("\tDuration: %dns/%dms\n", countingSort.getDuration(), Math.round((double) countingSort.getDuration() / 1000000));
        System.out.println(isSorted1 ? "\tSorted successfully!" : "\tCannot be sorted!");

        BinaryInsertionSort binaryinsertion = new BinaryInsertionSort(Arrays.copyOf(rdList, rdList.length));

        binaryinsertion.sort();
        boolean isSorted2 = binaryinsertion.isSorted();
        System.out.println("BinaryInsertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", binaryinsertion.getDuration(), Math.round((double) binaryinsertion.getDuration() / 1000000));
        System.out.println(isSorted2 ? "\tSorted successfully!" : "\tCannot be sorted!");

        InsertionSort insertion = new InsertionSort(Arrays.copyOf(rdList, rdList.length));

        insertion.sort();
        boolean isSorted3 = insertion.isSorted();
        System.out.println("Insertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", insertion.getDuration(), Math.round((double) insertion.getDuration() / 1000000));
        System.out.println(isSorted3 ? "\tSorted successfully!" : "\tCannot be sorted!");

        HeapSort heap = new HeapSort(Arrays.copyOf(rdList, rdList.length));

        heap.sort();
        boolean isSorted7 = heap.isSorted();
        System.out.println("HeapSort:");
        System.out.printf("\tDuration: %dns/%dms\n", heap.getDuration(), Math.round((double) heap.getDuration() / 1000000));
        System.out.println(isSorted7 ? "\tSorted successfully!" : "\tCannot be sorted!");

        MergeSort merge = new MergeSort(Arrays.copyOf(rdList, rdList.length));

        merge.sort();
        boolean isSorted8 = merge.isSorted();
        System.out.println("MergeSort:");
        System.out.printf("\tDuration: %dns/%dms\n", merge.getDuration(), Math.round((double) merge.getDuration() / 1000000));
        System.out.println(isSorted8 ? "\tSorted successfully!" : "\tCannot be sorted!");
        System.out.println("#########");
    }

    static long[] sortAllReturn(int[] rdList, String note) {

        System.out.println("#########");
        System.out.printf("Element Size: %d\n", rdList.length);
        System.out.println(note);

        QuickSort hoarequickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        try {
            hoarequickSort.sort("hoares");
            boolean isSorted = hoarequickSort.isSorted();
            System.out.println("Quicksort/Hoares:");
            System.out.printf("\tDuration: %dns/%dms\n", hoarequickSort.getDuration(), Math.round((double) hoarequickSort.getDuration() / 1000000));
            System.out.println(isSorted ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

//        QuickSort lomutoquickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));
//
//        try {
//            lomutoquickSort.sort("lomutos");
//            boolean isSorted6 = lomutoquickSort.isSorted();
//            System.out.println("Quicksort/Lomutos:");
//            System.out.printf("\tDuration: %dns/%dms\n", lomutoquickSort.getDuration(), Math.round((double) lomutoquickSort.getDuration() / 1000000));
//            System.out.println(isSorted6 ? "\tSorted successfully!" : "\tCannot be sorted!");
//        } catch (StackOverflowError e) {
//            System.out.println("Problem!");
//        }

        QuickSort quickSort3way = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        try {
            quickSort3way.sort("dd");
            boolean isSorted5 = quickSort3way.isSorted();
            System.out.println("Quicksort/3way:");
            System.out.printf("\tDuration: %dns/%dms\n", quickSort3way.getDuration(), Math.round((double) quickSort3way.getDuration() / 1000000));
            System.out.println(isSorted5 ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        CountingSort countingSort = new CountingSort(Arrays.copyOf(rdList, rdList.length));

        countingSort.sort();
        boolean isSorted1 = countingSort.isSorted();
        System.out.println("Counting sort:");
        System.out.printf("\tDuration: %dns/%dms\n", countingSort.getDuration(), Math.round((double) countingSort.getDuration() / 1000000));
        System.out.println(isSorted1 ? "\tSorted successfully!" : "\tCannot be sorted!");

        BinaryInsertionSort binaryinsertion = new BinaryInsertionSort(Arrays.copyOf(rdList, rdList.length));

        binaryinsertion.sort();
        boolean isSorted2 = binaryinsertion.isSorted();
        System.out.println("BinaryInsertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", binaryinsertion.getDuration(), Math.round((double) binaryinsertion.getDuration() / 1000000));
        System.out.println(isSorted2 ? "\tSorted successfully!" : "\tCannot be sorted!");

        InsertionSort insertion = new InsertionSort(Arrays.copyOf(rdList, rdList.length));

        insertion.sort();
        boolean isSorted3 = insertion.isSorted();
        System.out.println("Insertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", insertion.getDuration(), Math.round((double) insertion.getDuration() / 1000000));
        System.out.println(isSorted3 ? "\tSorted successfully!" : "\tCannot be sorted!");

        HeapSort heap = new HeapSort(Arrays.copyOf(rdList, rdList.length));

        heap.sort();
        boolean isSorted7 = heap.isSorted();
        System.out.println("HeapSort:");
        System.out.printf("\tDuration: %dns/%dms\n", heap.getDuration(), Math.round((double) heap.getDuration() / 1000000));
        System.out.println(isSorted7 ? "\tSorted successfully!" : "\tCannot be sorted!");

        MergeSort merge = new MergeSort(Arrays.copyOf(rdList, rdList.length));

        merge.sort();
        boolean isSorted8 = merge.isSorted();
        System.out.println("MergeSort:");
        System.out.printf("\tDuration: %dns/%dms\n", merge.getDuration(), Math.round((double) merge.getDuration() / 1000000));
        System.out.println(isSorted8 ? "\tSorted successfully!" : "\tCannot be sorted!");
        System.out.println("#########");
        return new long[]{hoarequickSort.getDuration(), quickSort3way.getDuration(), countingSort.getDuration(), binaryinsertion.getDuration(), insertion.getDuration(), heap.getDuration(), merge.getDuration()};
    }
}
