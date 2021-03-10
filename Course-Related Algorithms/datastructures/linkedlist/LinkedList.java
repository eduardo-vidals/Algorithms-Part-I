/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author EduardoPC
 */
public class LinkedList<Item> implements Iterable<Item> {
    Node tail;
    Node head;
    int n;

    private class Node {
        Node next;
        Node prev;
        Item data;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Node oldTail = tail;
        tail = new Node();
        tail.data = data;
        tail.prev = oldTail;

        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        n++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldTail = tail;

        if (size() >= 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            tail = null;
            head = null;
        }

        n--;
        return oldTail.data;
    }

    public void reverseLinkedList() {
        Node next = null;
        Node current = null;

        while (head != null) {
            next = head.next;
            head.next = current;
            head.prev = next;
            current = head;
            head = next;
        }

        head = current;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Item data : this) {
            sb.append(data).append(" ");
        }

        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            Item item = current.data;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        LinkedList<Integer> s = new LinkedList<>();
        s.push(2);
        s.push(4);
        s.push(5);
        s.push(6);
        s.pop();

        System.out.println(s);
        s.reverseLinkedList();
        System.out.println(s);
        System.out.println(s.head.next.prev.data);

    }

}
