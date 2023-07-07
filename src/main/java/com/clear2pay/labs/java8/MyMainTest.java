package com.clear2pay.labs.java8;

import java.util.Arrays;

public class MyMainTest {
    public static void main(String[] args) {
        String s = "   sdfad   d";

        if( Arrays.asList(s.trim().split("[ ]")).size() == 1) {
            System.out.println("Hello");
        }
    }
}
