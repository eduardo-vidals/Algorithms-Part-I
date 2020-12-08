package computerscience.algorithms.week3.collinearpoints;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EduardoPC
 */
abstract class D {
    int bar(D a) { return 0; }
    int bar(B b) { return 1; }
    int bar(C c) { return 2; }
}

abstract class A {
    int bar(A a) { return 0; }
    int bar(B b) { return 1; }
    int bar(C c) { return 2; }
}

class B extends D {
    int bar(D a) { return 3; }
    int bar(B b) { return 4; }
    int bar(C c) { return 5; }
    int lame(){return 22; };
}

class C extends B {
    int foo() { return ((D)this).bar((B)this); }
}

public class Main {
    
    public static void main(String[] args){
        C x = new C();
        System.out.println(x.foo());
    }
    
}
