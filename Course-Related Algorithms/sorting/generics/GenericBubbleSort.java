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
public class GenericBubbleSort {

    public static <T extends Comparable<? super T>> void sort(T[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0){
                    swap(arr, j, j + 1);
                }
            }
        }

    }
    
    private static <T> void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arrayString = {"Jason", "Bob", "Aaron", "Mary", "Jessica", "Eduardo", "Sharon", "Dominik", "Carlos"};
        GenericBubbleSort.sort(arrayString);
        System.out.println(Arrays.toString(arrayString));
    }

}
