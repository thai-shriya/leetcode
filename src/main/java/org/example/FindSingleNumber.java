package org.example;

public class FindSingleNumber {
    public int singleNumber(int[] nums) {
        /* The problem asks to find the non duplicate number in the array. This can be done using xor operation.
        xor of same number results 0. This way we can find out the unique number in the array
        Time Complexity - O(n)
        Space Complexity - O(1) */
        int result=0;
        for(int i=0;i<nums.length;i++)
            result^=nums[i];
        return result;
    }
}
