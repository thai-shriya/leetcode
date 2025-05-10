package org.example;

public class Haystack {
    public int strStr(String haystack, String needle) {

        /*
        * First check if needle substring is present in string haystack. If it is not present then return -1
        * Else, start traversing the haystack string taking the needle length substring and compare
        * each possible substring with needle. Return the index where it matches the string
        * Time complexity - O(n)
        * Space Complexity - 0(n) */

        if (needle.length() > haystack.length())
            return -1;
        if (!haystack.contains(needle))
            return -1;
        else {
            for (int i = 0; i <= haystack.length() - needle.length(); i++) {
                String substring = haystack.substring(i, i + needle.length());
                if (substring.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Haystack haystack = new Haystack();

        // Test case 1: Needle is in the haystack
        System.out.println("Test Case 1: " + haystack.strStr("hello", "ll")); // Expected output: 2

        // Test case 2: Needle is not in the haystack
        System.out.println("Test Case 2: " + haystack.strStr("aaaaa", "bba")); // Expected output: -1

        // Test case 3: Needle is empty
        System.out.println("Test Case 3: " + haystack.strStr("hello", "")); // Expected output: 0

        // Test case 4: Haystack is empty
        System.out.println("Test Case 4: " + haystack.strStr("", "a")); // Expected output: -1

        // Test case 5: Haystack and needle are the same
        System.out.println("Test Case 5: " + haystack.strStr("a", "a")); // Expected output: 0

        // Test case 6: Needle is longer than haystack
        System.out.println("Test Case 6: " + haystack.strStr("short", "longer")); // Expected output: -1
    }
}
