package computerscience.algorithms.sorting.generics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;

/**
 *
 * @author Eduardo
 */
public class GenericMergeSort {

    public static <T extends Comparable<? super T>> void sort(T[] arr, int l, int r) {

        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }

    }

    private static <T extends Comparable<? super T>> void merge(T[] arr, int l, int m, int r) {
        int nL = m - l + 1;
        int nR = r - m;

        T L[] = (T[]) new Comparable[nL];
        T R[] = (T[]) new Comparable[nR];

        for (int i = 0; i < nL; i++) {
            L[i] = arr[l + i];
        }

        for (int j = 0; j < nR; j++) {
            R[j] = arr[m + j + 1];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < nL && j < nR) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
            k++;
        }

        while (i < nL) {
            arr[k] = L[i++];
            k++;
        }

        while (j < nR) {
            arr[k] = R[j++];
            k++;
        }
    }

    public static void main(String[] args) {
        String[] arrayString = {"Jason", "Bob", "Aaron", "Mary", "Jessica", "Eduardo", "Sharon", "Dominik", "Carlos"};
        GenericMergeSort.sort(arrayString, 0, arrayString.length - 1);
        System.out.println(Arrays.toString(arrayString));

    }

}
