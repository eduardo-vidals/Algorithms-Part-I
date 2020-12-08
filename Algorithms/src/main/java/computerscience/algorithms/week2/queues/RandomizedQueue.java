/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.week2.queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author EduardoPC
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;
    private int n;
    private Item[] a;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // Resizes the array based on the capacity that you want to give it
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }

        a = copy;
    }

    // add the item
    // Puts the elements at the end of the list and the array size doubles each time the array size is reached
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item;

    }
    // remove and return a random item
    // A random element is chosen from the array 
    // First, we define an item to keep of hold of the data in the random index (since we're removing the element within the array)
    // Secondly, we redefine the data of the array at the random index to have the last element of the array (n - 1)
    // Thirdly, the last element in the array is set to null so we avoid loitering from taking place
    // Lastly, we decrease the size and if the array is greater than 0 and 1/4 of the size of the array length, then it shrinks.
    // We can now return the item that held data of the item at the random index

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int random = StdRandom.uniform(0, n);
        Item item = a[random];
        a[random] = a[n - 1];
        a[n - 1] = null;
        n--;

        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int random = StdRandom.uniform(0, n);
        Item item = a[random];

        return item;
    }

    @Override
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {

        private Item[] copy;
        private int i;

        public RandomArrayIterator() {

            // A copy of the array is made because the array has null elements that can be shuffled.
            // We want it so the copy only includes the elements that we care about. 
            copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }

            StdRandom.shuffle(copy);

        }

        @Override
        // There is a next item as long as i is less than the size of elements in the array
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return copy[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        StdOut.println("Sample: " + rq.sample());
        StdOut.println("Dequeue: " + rq.dequeue());

        for (Integer s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        for (Integer s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();

        StdOut.println("Is the randomized queue empty?: " + rq.isEmpty());
        StdOut.println("Size: " + rq.size());

    }

}
