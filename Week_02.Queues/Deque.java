/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.week2.queues;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author EduardoPC
 */
public class Deque<Item> implements Iterable<Item> {

    private int n;
    private Node first;
    private Node last;

    // define a doubly linked list Node
    private class Node {

        private Item data;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = first;
        first = new Node();
        first.data = data;
        first.next = oldFirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }

        n++;

    }

    // add the item to the back
    public void addLast(Item data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Node oldLast = last;
        last = new Node();
        last.data = data;
        last.prev = oldLast;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldFirst = first;

        if (size() > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        n--;
        return oldFirst.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldLast = last;

        if (size() > 1) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        n--;
        return oldLast.data;
    }

    @Override
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.data;
            current = current.next;
            return item;

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(4);
        deque.addFirst(2);
        deque.addLast(5);
        deque.addFirst(3);
        StdOut.println("Number removed: " + deque.removeFirst());
        StdOut.println("Number removed: " + deque.removeLast());
        for (Integer s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println(" ");
        StdOut.println("Is the deque empty?: " + deque.isEmpty());
        StdOut.println("Size: " + deque.size());

    }

}
