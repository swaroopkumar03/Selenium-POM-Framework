package com.hrm.scripts;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by graceraj on 22-02-2017.
 */
public class DemoTest {
    public static void main(String[] args) {
        ArrayList<String> allText = new ArrayList<>();
        allText.add("a");
        allText.add("a");
        allText.add("c");

        HashSet<String> clone = new HashSet<>(allText);
        System.out.println(clone);

        HashSet <String> clone2 = new HashSet<>();
        for(String s : allText){
            boolean v = clone2.add(s);
            System.out.println(v);
        }
    }
}