/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.sorting;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 *
 * @author EduardoPC
 */
public class QuickSort {

    public static void main(String[] args) {

        int size = 1000000;
        int[] numbers = new int[size];

        Random generator = new Random();
        for (int i = 0; i < size - 1; i++) {
            numbers[i] = generator.nextInt(10000000);
        }

        long startTime = System.nanoTime();
        try {
            sort(numbers, 0, size - 1);
        } finally {
            long endTime = System.nanoTime() - startTime;
            double totalTime = TimeUnit.NANOSECONDS.toMillis(endTime);
            String sizeString = NumberFormat.getNumberInstance(Locale.US).format(size);
            System.out.println("Quick Sort: " + totalTime + " milliseconds to sort an array of " + sizeString + " elements.");
        }

        System.out.println(Arrays.toString(numbers));

    }

    public static int partition(int[] arr, int l, int h) {

        int pivot = arr[l];
        int i = l - 1;
        int j = h + 1;

        while (true) {

            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(arr, i, j);

        }

    }

    public static void sort(int[] arr, int l, int h) {

        if (l < h) {

            int j = partition(arr, l, h);
            sort(arr, l, j);
            sort(arr, j + 1, h);

        }

    }

    public static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

}
