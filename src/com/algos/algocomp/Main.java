package com.algos.algocomp;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final int MAX_VALUE = 10000;

    public static void main(String[] args) {

        int con = 100;
        int[][][] rdList = new int[3][3][];
        for (int i = 0; i < rdList.length; i++) {
            Random random = new Random(5621);

            rdList[i][0] = new int[(int) Math.pow(con, i + 1)];
            rdList[i][1] = new int[(int) Math.pow(con, i + 1)];
            rdList[i][2] = new int[(int) Math.pow(con, i + 1)];

            for (int j = 0; j < rdList[i][0].length; j++) rdList[i][0][j] = random.nextInt(MAX_VALUE);
            for (int j = 0; j < rdList[i][1].length; j++)
                rdList[i][1][rdList[i][1].length - i - 1] = (int) (((rdList[i][1].length - (float) i) / rdList[i][1].length) * (MAX_VALUE - 1));
            for (int j = 0; j < rdList[i][2].length; j++)
                rdList[i][1][i] = (int) (((rdList[i][2].length - (float) i) / rdList[i][2].length) * (MAX_VALUE - 1));
        }

        for (int i = 0; i < rdList.length; i++) {
            for (int j = 0; j < rdList[i].length; j++) {
                sortAll(rdList[i][j], j == 0 ? "Random List Elements" : j == 1 ? "Sorted List Elements" : "Reverse Sorted List Elements");
            }
        }

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

        QuickSort hoarequickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));
        System.out.println("#########");
        System.out.printf("Element Size: %d\n", rdList.length);
        System.out.println(note);

        try {
            hoarequickSort.sort("hoares");
            boolean isSorted = hoarequickSort.isSorted();
            System.out.println("Quicksort/Hoares:");
            System.out.printf("\tDuration: %dns/%dms\n", hoarequickSort.getDuration(), hoarequickSort.getDuration() / 1000000);
            System.out.println(isSorted ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        QuickSort lomutoquickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        try {
            lomutoquickSort.sort("lomutos");
            boolean isSorted6 = lomutoquickSort.isSorted();
            System.out.println("Quicksort/Lomutos:");
            System.out.printf("\tDuration: %dns/%dms\n", lomutoquickSort.getDuration(), lomutoquickSort.getDuration() / 1000000);
            System.out.println(isSorted6 ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        QuickSort quickSort3way = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        try {
            quickSort3way.sort("dd");
            boolean isSorted5 = quickSort3way.isSorted();
            System.out.println("Quicksort/3way:");
            System.out.printf("\tDuration: %dns/%dms\n", quickSort3way.getDuration(), quickSort3way.getDuration() / 1000000);
            System.out.println(isSorted5 ? "\tSorted successfully!" : "\tCannot be sorted!");
        } catch (StackOverflowError e) {
            System.out.println("Problem!");
        }

        CountingSort countingSort = new CountingSort(Arrays.copyOf(rdList, rdList.length));

        countingSort.sort();
        boolean isSorted1 = countingSort.isSorted();
        System.out.println("Counting sort:");
        System.out.printf("\tDuration: %dns/%dms\n", countingSort.getDuration(), countingSort.getDuration() / 1000000);
        System.out.println(isSorted1 ? "\tSorted successfully!" : "\tCannot be sorted!");

        BinaryInsertionSort binaryinsertion = new BinaryInsertionSort(Arrays.copyOf(rdList, rdList.length));

        binaryinsertion.sort();
        boolean isSorted2 = binaryinsertion.isSorted();
        System.out.println("BinaryInsertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", binaryinsertion.getDuration(), binaryinsertion.getDuration() / 1000000);
        System.out.println(isSorted2 ? "\tSorted successfully!" : "\tCannot be sorted!");

        InsertionSort insertion = new InsertionSort(Arrays.copyOf(rdList, rdList.length));

        insertion.sort();
        boolean isSorted3 = insertion.isSorted();
        System.out.println("Insertion sort:");
        System.out.printf("\tDuration: %dns/%dms\n", insertion.getDuration(), insertion.getDuration() / 1000000);
        System.out.println(isSorted3 ? "\tSorted successfully!" : "\tCannot be sorted!");

        HeapSort heap = new HeapSort(Arrays.copyOf(rdList, rdList.length));

        heap.sort();
        boolean isSorted7 = heap.isSorted();
        System.out.println("HeapSort:");
        System.out.printf("\tDuration: %dns/%dms\n", heap.getDuration(), heap.getDuration() / 1000000);
        System.out.println(isSorted7 ? "\tSorted successfully!" : "\tCannot be sorted!");

        MergeSort merge = new MergeSort(Arrays.copyOf(rdList, rdList.length));

        merge.sort();
        boolean isSorted8 = merge.isSorted();
        System.out.println("MergeSort:");
        System.out.printf("\tDuration: %dns/%dms\n", merge.getDuration(), merge.getDuration() / 1000000);
        System.out.println(isSorted8 ? "\tSorted successfully!" : "\tCannot be sorted!");
        System.out.println("#########");
    }
}
