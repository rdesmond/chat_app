package com.example.controller;

import java.util.Random;

import java.util.Scanner;

public class Jules {
    public static void main(String[] args) {
        forLoopTwo();
    }

    public static void forLoopTwo(){
        int i = 100;
        int j = 0;
        for (; i>=1; i--) {
            j += i;
        }

        System.out.println(j);

    }
}
