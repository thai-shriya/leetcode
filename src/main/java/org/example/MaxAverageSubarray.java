package org.example;

public class MaxAverageSubarray {
    public double findMaxAverage(int[] nums, int k) {
        int sum =0;

        for(int i=0;i<k;i++){
            sum+= nums[i];
        }

        int maxSum =sum;

        for(int i =k; i< nums.length; i++){
            sum += nums[i] - nums[i-k];
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        MaxAverageSubarray solver = new MaxAverageSubarray();

        // Test Case 1
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println("Test Case 1 Result: " + solver.findMaxAverage(nums1, k1)); // Expected: 12.75

        // Test Case 2
        int[] nums2 = {5};
        int k2 = 1;
        System.out.println("Test Case 2 Result: " + solver.findMaxAverage(nums2, k2)); // Expected: 5.0

        // Test Case 3
        int[] nums3 = {-1, -12, -5, -6, -50, -3};
        int k3 = 2;
        System.out.println("Test Case 3 Result: " + solver.findMaxAverage(nums3, k3)); // Expected: -4.0

        // Test Case 4
        int[] nums4 = {0, 4, 0, 3, 2};
        int k4 = 1;
        System.out.println("Test Case 4 Result: " + solver.findMaxAverage(nums4, k4));
    }
}
