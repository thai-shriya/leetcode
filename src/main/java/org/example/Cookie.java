package org.example;

import java.util.Arrays;

public class Cookie {

    public int findContentChildren(int[] g, int[] s) {

        /* If we sort the arrays, then we can easily assign smaller cookies with
        children who are content with minimum size cookies. We can add count to number of content children
        until s[i] >=g[j] condition is satisfied.
        Time complexity - O(nlog n + mlogm) -> to sort both the arrays + travesing through the arrays
        space complexity - O(1)
        */

        Arrays.sort(g);
        Arrays.sort(s);
        int contentChildren = 0;
        int cookieIndex = 0;
        while (cookieIndex < s.length && contentChildren < g.length) {
            if (s[cookieIndex] >= g[contentChildren]) {
                contentChildren++;
            }
            cookieIndex++;
        }
        return contentChildren;
    }

    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        System.out.println("Test Case 1: " + cookie.findContentChildren(g1, s1)); // Output: 1

        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        System.out.println("Test Case 2: " + cookie.findContentChildren(g2, s2)); // Output: 2

        int[] g3 = {2, 3, 5};
        int[] s3 = {1, 1, 1};
        System.out.println("Test Case 3: " + cookie.findContentChildren(g3, s3)); // Output: 0

        int[] g4 = {1, 2, 3};
        int[] s4 = {3};
        System.out.println("Test Case 4: " + cookie.findContentChildren(g4, s4));
    }
}
