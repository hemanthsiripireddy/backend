package com.siripireddy.quartzscheduler.service;

public class Calculator {
    // add two numbers
    public int add(int a, int b) {
        return a + b;
    }
    // method to check a String is palindrome
    public boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;

        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }



}
