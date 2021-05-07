package com.algos.algocomp;

public class HeapSort {
    private int[] list;
    private long duration;

    public HeapSort(int[] list) {
        this.list = list;
    }

    public void sort() {
        long startTime = System.nanoTime();
        int sizeOfTheList = list.length;

        // Build heap
        for (int i = sizeOfTheList / 2 - 1; i >= 0; i--)
            heapify(sizeOfTheList, i);


        for (int i = sizeOfTheList - 1; i >= 0; i--) {

            int root = list[0];
            list[0] = list[i];
            list[i] = root;


            heapify(i, 0);
        }
        duration = System.nanoTime() - startTime;
    }

    void heapify(int heapSize, int i) {

        int largestElement = i;     //initialize largest element as root
        int left_child = 2 * i + 1;
        int right_child = 2 * i + 2;

        //if left_child > root
        if (left_child < heapSize && list[left_child] > list[largestElement])
            largestElement = left_child;


        //if right_child > largestElement
        if (right_child < heapSize && list[right_child] > list[largestElement])
            largestElement = right_child;


        if (largestElement != i) {

            int swap = list[i];
            list[i] = list[largestElement];
            list[largestElement] = swap;


            heapify(heapSize, largestElement);
        }
    }

    public long getDuration() {
        return duration;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
