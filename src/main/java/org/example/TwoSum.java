package org.example;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[] {i, map.get(target - nums[i])};
            }
            else
                map.put(nums[i], i);
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        // Test case 1
        int[] result1 = solver.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Test Case 1: " + Arrays.toString(result1));  // Expected: [1, 0]

        // Test case 2
        int[] result2 = solver.twoSum(new int[]{3, 2, 4}, 6);
        System.out.println("Test Case 2: " + Arrays.toString(result2));  // Expected: [2, 1]

        // Test case 3
        int[] result3 = solver.twoSum(new int[]{3, 3}, 6);
        System.out.println("Test Case 3: " + Arrays.toString(result3));  // Expected: [1, 0]

        // Test case 4 (no match)
        int[] result4 = solver.twoSum(new int[]{1, 2, 3}, 7);
        System.out.println("Test Case 4: " + Arrays.toString(result4));

    }
}
