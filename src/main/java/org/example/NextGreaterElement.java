package org.example;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
        First, we will traverse the nums2 array entirely and find the next greater element for each element.
        Then, since nums1 is a subset of nums2, we can store the above obtained results in a hashmap.
        Then, it's easy to access the next greater element of each element of nums1 with the help of hashmap in O(1) time.

        For this, I used stack, because, stack helps to compare the element. After we reach the end of the array,
        the remaining elements in the stack are poped and added to the map with -1 value because these are the elements
        which does not have next greater element in the array.

        time complexity - O(n)
        space complexity - O(n)
        */
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[i] > stack.peek())
                map.put(stack.pop(),nums2[i]);
            stack.push(nums2[i]);
        }

        while (!stack.empty()) map.put(stack.pop(), -1);

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}
