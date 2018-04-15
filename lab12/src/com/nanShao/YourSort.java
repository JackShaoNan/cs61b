package com.nanShao;

/* YourSort.java */

public class YourSort {

    public static void sort(int[] A) {
        // Place your Part III code here.
        if(A.length > 50) {
            Sort.quicksort(A);
        }else {
            Sort.insertionSort(A);
        }
    }
}