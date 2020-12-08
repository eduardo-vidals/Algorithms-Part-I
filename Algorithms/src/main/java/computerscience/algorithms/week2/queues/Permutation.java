package computerscience.algorithms.week2.queues;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author EduardoPC
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        String text = StdIn.readString();
        rq.enqueue(text);

        while (!StdIn.isEmpty()) {
            text = StdIn.readString();
            rq.enqueue(text);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }

    }
}
