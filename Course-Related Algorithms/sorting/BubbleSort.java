/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author EduardoPC
 */
public class BubbleSort {

    public static void main(String[] args) {

        int size = 1000000;
        int[] numbers = new int[size];

        Random generator = new Random();
        for (int i = 0; i < size - 1; i++) {
            numbers[i] = generator.nextInt(size);
        }

        long startTime = System.nanoTime();
        try {
            sort(numbers);
        } finally {
            long endTime = System.nanoTime() - startTime;
            double totalTime = TimeUnit.NANOSECONDS.toMillis(endTime);
            System.out.println("Bubble Sort: " + totalTime + " milliseconds to sort an array of " + size + " elements.");
            System.out.println(Arrays.toString(numbers));
        }

    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
