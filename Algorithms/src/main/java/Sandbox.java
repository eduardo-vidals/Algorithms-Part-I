
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EduardoPC
 */
public class Sandbox {

    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        
        String statement = "this is not the droid you are looking for";
        
        String[] elements = statement.split(" ");
        
        for (String element : elements){
            strings.add(element);
        }
        
        for (String s : strings){
            if (s.length() > 3){
                System.out.println(s);
            }
        }
        
        Collections.sort(strings);

    }

}
