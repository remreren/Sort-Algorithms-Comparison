package com.algos.algocomp;

import java.io.*;
import java.util.*;

/**
 * @author Emre Eren 150118020
 * Edanur Öztürk 150117007
 * Feyza Bulgurcu 150117033
 */
public class Main {

    // Maximum value that each element should be.
    public static final int MAX_VALUE = 10000;

    // We told that we are doing trials more than one. For the scalability of app, we defined it here.
    private static final int TRIAL_NUMBERS = 11;

    // Defined here how many different size count should be done.
    private static final int SIZE_CASE_COUNT = 10;

    // Case names. We have 3 different case inputs; Random, Sorted and Reverse Sorted Lists.
    private static final String[] CASES = {"Random", "Sorted", "Reverse Sorted"};

    // For different sizes, we have multipliers and base.
    private static final int BASE = 1024;
    private static final int MULTIPLIER = 1;

    // Names of sort types.
    private static final String[] SORTS = {"Hoares QuickSort", "3way QuickSort", "Counting Sort", "BinaryInsertion Sort", "Insertion Sort", "HeapSort", "MergeSort"};

    public static void main(String[] args) {

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
         * Average case for Insertion Sort is at random state. Works in O(n^2) time complexity.
         * Worst case for Insertion Sort is at reverse sorted state. Works in O(n^2) time complexity.
         *
         * Best case and worst case does not matter for HeapSort. Always works in O(nlogn) time complexity.
         *
         * Best case and worst case does not matter for MergeSort. Always works in O(nlogn) time complexity.
         */

        // int[size case count][case count][size]
        // This list holds all inputs.
        int[][][] lists = new int[SIZE_CASE_COUNT][CASES.length][];

        File[] files = getInputFiles();
        if (files.length < SIZE_CASE_COUNT) {
            // Input generation loop. Creates all input cases.
            for (int i = 0; i < lists.length; i++) {
                int length = BASE * (1 + i * MULTIPLIER);

                lists[i][0] = random(0, MAX_VALUE, length, 5621);
                lists[i][1] = linspace(0, MAX_VALUE, length);
                lists[i][2] = linspace(MAX_VALUE, 0, length);
            }

            writeInput(lists);
        } else {
            lists = readFiles(files);
        }

        // Warm machine up
        long p = 0;
        for (long i = 0; i < (int) (999999999 * 1.2d); i++) {
            p += 16;
            p /= 4;
        }
        System.out.println(p);

        // This array holds results.
        long[][][][] results = new long[TRIAL_NUMBERS][SORTS.length][SIZE_CASE_COUNT][CASES.length];

        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < lists.length; j++) {
                for (int k = 0; k < lists[j].length; k++) {
                    long[] ts = sortAll(lists[j][k]);
                    for (int m = 0; m < ts.length; m++)
                        results[i][m][j][k] = ts[m];
                }
            }
        }

        // Write results to a csv file.
        try {
            FileWriter str = new FileWriter("data.csv");
            for (int i = 1; i < results.length; i++) {
                str.append("\n,Trial #").append(String.format(Locale.ENGLISH, "%d\n,", i));
                for (int k = 0; k < results[i][0].length; k++)
                    for (int m = 0; m < results[i][0][k].length; m++)
                        str.append(String.format(Locale.ENGLISH, "%d,", lists[k][m].length));
                str.append("\n,");
                for (int k = 0; k < results[i][0].length; k++)
                    for (int m = 0; m < results[i][0][k].length; m++)
                        str.append(CASES[m]).append(",");
                str.append("\n");
                for (int j = 0; j < results[i].length; j++) {
                    str.append(SORTS[j]).append(",");
                    for (int k = 0; k < results[i][j].length; k++) {
                        for (int m = 0; m < results[i][j][k].length; m++) {
                            str.append(String.format(Locale.ENGLISH, "%.6f,", (double) results[i][j][k][m] / 1000000));
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

    static int[][][] readFiles(File... files) {
        int[][][] input = new int[files.length][3][];
        for (int i = 0; i < files.length; i++) {
            try {
                if (files[i].exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(files[i]));
                    int size = Integer.parseInt(reader.readLine());
                    for (int m = 0; m < input[i].length; m++) {
                        input[i][m] = new int[size];
                        for (int j = 0; j < size; j++) {
                            input[i][m][j] = Integer.parseInt(reader.readLine());
                        }
                    }
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return input;
    }

    static File[] getInputFiles() {
        ArrayList<File> files = new ArrayList<>();
        File fold = new File("./inputs");
        if (!fold.exists())
            if (!fold.mkdir()) return null;

        File f = new File("./inputs");
        if (f.exists()) {
            for (File file : f.listFiles()) {
                if (file.getName().startsWith("input"))
                    files.add(file);
            }
        }
        return files.toArray(new File[0]);
    }

    static void writeInput(int[][][] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            try {
                File file = new File(String.format("./inputs/input_%d.txt", i + 1));
                if (file.createNewFile()) {
                    FileWriter writer = new FileWriter(file);
                    for (int k = 0; k < inputs[i].length; k++) {
                        if (k == 0)
                            writer.append(String.format("%d\n", inputs[i][k].length));
                        for (int m = 0; m < inputs[i][k].length; m++) {
                            writer.append(String.format("%d\n", inputs[i][k][m]));
                        }
                    }
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Take a list and apply it to all sort types. Return the results of it.
    private static long[] sortAll(int[] lists) {
        long[] times = new long[SORTS.length];
        for (int i = 0; i < SORTS.length; i++) {
            Sort sort;
            switch (i) {
                case 1:
                    sort = new ThreeWayQuickSort(Arrays.copyOf(lists, lists.length));
                    break;

                case 2:
                    sort = new CountingSort(Arrays.copyOf(lists, lists.length));
                    break;

                case 3:
                    sort = new BinaryInsertionSort(Arrays.copyOf(lists, lists.length));
                    break;

                case 4:
                    sort = new InsertionSort(Arrays.copyOf(lists, lists.length));
                    break;

                case 5:
                    sort = new HeapSort(Arrays.copyOf(lists, lists.length));
                    break;

                case 6:
                    sort = new MergeSort(Arrays.copyOf(lists, lists.length));
                    break;
                case 0:

                default:
                    sort = new HoaresQuickSort(Arrays.copyOf(lists, lists.length));
                    break;
            }
            times[i] = sort.sort();
        }

        return times;
    }

    // Populate a list according to its start, end and required count.
    static int[] linspace(int start, int end, int count) {
        int[] list = new int[count];
        boolean reverse = start > end;
        int range = Math.abs(end - start);
        for (int i = 0; i < list.length; i++) {
            int tmp = (int) (((list.length - (double) i - 1) / (list.length - 1)) * (range - 1));
            list[i] = reverse ? tmp : range - tmp - 1;
        }

        return list;
    }

    // Populate a random list according to upper, lower bounds, count and seed.
    static int[] random(int p1, int p2, int count, int seed) {
        int[] list = new int[count];
        Random random = new Random(seed);
        int min = Math.min(p1, p2);
        int max = Math.max(p1, p2);
        for (int i = 0; i < list.length; i++) list[i] = min + random.nextInt(max);
        return list;
    }
}
