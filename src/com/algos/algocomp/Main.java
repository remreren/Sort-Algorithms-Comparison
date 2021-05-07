package com.algos.algocomp;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random(5621);
        int[] rdList = new int[500000];

        for (int i = 0; i < rdList.length; i++) {
            rdList[i] = random.nextInt(10000);
        }

        QuickSort hoarequickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        hoarequickSort.sort("hoares");
        boolean isSorted = hoarequickSort.isSorted();
        System.out.println("Quicksort/Hoares:");
        System.out.printf("\tDuration: %dns/%dms\n", hoarequickSort.getDuration(), hoarequickSort.getDuration()/1000000);
        System.out.println(isSorted ? "\tSorted successfully!": "\tCannot be sorted!");

        QuickSort lomutoquickSort = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        lomutoquickSort.sort("lomutos");
        boolean isSorted6 = lomutoquickSort.isSorted();
        System.out.println("Quicksort/Lomutos:");
        System.out.printf("\tDuration: %dns/%dms\n", lomutoquickSort.getDuration(), lomutoquickSort.getDuration()/1000000);
        System.out.println(isSorted6 ? "\tSorted successfully!": "\tCannot be sorted!");

        QuickSort quickSort3way = new QuickSort(Arrays.copyOf(rdList, rdList.length));

        quickSort3way.sort("dd");
        boolean isSorted5 = quickSort3way.isSorted();
        System.out.println("Quicksort/3way:");
        System.out.printf("\tDuration: %dns/%dms\n", quickSort3way.getDuration(), quickSort3way.getDuration()/1000000);
        System.out.println(isSorted5 ? "\tSorted successfully!": "\tCannot be sorted!");

        CountingSort countingSort = new CountingSort(Arrays.copyOf(rdList, rdList.length));

        countingSort.sort();
        boolean isSorted1 = countingSort.isSorted();
        System.out.println("Counting sort:");
        System.out.printf("\tDuration: %dns/%dms\n", countingSort.getDuration(), countingSort.getDuration()/1000000);
        System.out.println(isSorted1 ? "\tSorted successfully!": "\tCannot be sorted!");

//        BinaryInsertionSort binaryinsertion = new BinaryInsertionSort(Arrays.copyOf(rdList, rdList.length));
//
//        binaryinsertion.sort();
//        boolean isSorted2 = binaryinsertion.isSorted();
//        System.out.println("BinaryInsertion sort:");
//        System.out.printf("\tDuration: %dns/%dms\n", binaryinsertion.getDuration(), binaryinsertion.getDuration()/1000000);
//        System.out.println(isSorted2 ? "\tSorted successfully!": "\tCannot be sorted!");
//
//        InsertionSort insertion = new InsertionSort(Arrays.copyOf(rdList, rdList.length));
//
//        insertion.sort();
//        boolean isSorted3 = insertion.isSorted();
//        System.out.println("Insertion sort:");
//        System.out.printf("\tDuration: %dns/%dms\n", insertion.getDuration(), insertion.getDuration()/1000000);
//        System.out.println(isSorted3 ? "\tSorted successfully!": "\tCannot be sorted!");

        HeapSort heap = new HeapSort(Arrays.copyOf(rdList, rdList.length));

        heap.sort();
        boolean isSorted7 = heap.isSorted();
        System.out.println("HeapSort:");
        System.out.printf("\tDuration: %dns/%dms\n", heap.getDuration(), heap.getDuration()/1000000);
        System.out.println(isSorted7 ? "\tSorted successfully!": "\tCannot be sorted!");

        MergeSort merge = new MergeSort(Arrays.copyOf(rdList, rdList.length));

        merge.sort();
        boolean isSorted8 = merge.isSorted();
        System.out.println("MergeSort:");
        System.out.printf("\tDuration: %dns/%dms\n", merge.getDuration(), merge.getDuration()/1000000);
        System.out.println(isSorted8 ? "\tSorted successfully!": "\tCannot be sorted!");

//        System.out.println(Arrays.toString(quickSort.getList()));
    }
}
