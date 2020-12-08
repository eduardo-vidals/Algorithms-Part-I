/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author EduardoPC
 */
public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private class Node {
        Item data;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item data) {
        Node oldLast = last;
        last = new Node();
        last.data = data;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Item data = first.data;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return data;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {

        private Node current = first;  // node containing current item

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.data;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();

        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.enqueue(7);
        
        System.out.println(queue);

    }

}
