/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.sorting.generics;

import java.util.Arrays;

/**
 *
 * @author Eduardo
 */
public class GenericSelectionSort {

    public static <T extends Comparable<? super T>> void sort(T[] arr) {

        for (int i = 0; i < arr.length; i++) {
            T minValue = arr[i];
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {

                if (minValue.compareTo(arr[j]) > 0) {
                    minValue = arr[j];
                    minIndex = j;
                }

            }
            swap(arr, i, minIndex);
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arrayString = {"Jason", "Bob", "Aaron", "Mary", "Jessica", "Eduardo", "Sharon", "Dominik", "Carlos"};
        GenericSelectionSort.sort(arrayString);
        System.out.println(Arrays.toString(arrayString));
    }
}
