package com.algos.algocomp;

public class Test {

    public static boolean isSorted(int[] list) {
        if (list.length < 1) return true;
        int prev = list[0];
        boolean isSorted = true;
        int i = 1;
        for (i = 1; i < list.length; i++) {
            if ((prev > list[i])) {
                isSorted = false;
                break;
            }
            prev = list[i];
        }
        if (!isSorted)
            System.out.printf("Problematic values are; %d at index %d and %d at index %d%n", prev, i - 1, list[i], i);
        return isSorted;
    }
}
