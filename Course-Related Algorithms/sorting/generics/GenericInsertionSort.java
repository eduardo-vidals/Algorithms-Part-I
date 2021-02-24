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
public class GenericInsertionSort {

    public static <T extends Comparable<? super T>> void sort(T[] arr) {

        for (int i = 1; i < arr.length; i++) {

            // we keep track of the key
            T key = arr[i];
            int j = i - 1;

            // if the key to the left is bigger, then replace it
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            // we can replace arr[j + 1] with the key since that is
            // the last swap we need to do
            arr[j + 1] = key;
        }

    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arrayString = {"Jason", "Bob", "Aaron", "Mary", "Jessica", "Eduardo", "Sharon", "Dominik", "Carlos"};
        GenericInsertionSort.sort(arrayString);
        System.out.println(Arrays.toString(arrayString));

    }

}
