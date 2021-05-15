package com.algos.algocomp;

/**
 * @author Emre Eren 150118020
 */
public abstract class Sort {
    protected int[] list;
    protected long duration = 0;

    public Sort(int[] list) {
        this.list = list;
    }

    abstract long sort();

    protected void swap(int p1, int p2) {
        int tempVal = list[p1];
        list[p1] = list[p2];
        list[p2] = tempVal;
    }

    public long getDuration() {
        return duration;
    }

    public int[] getList() {
        return list;
    }

    public boolean isSorted() {
        return Test.isSorted(list);
    }
}
