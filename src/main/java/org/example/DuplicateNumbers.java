package org.example;

import java.util.ArrayList;
import java.util.List;

public class DuplicateNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /*
        First we traverse through the array to negate all the numbers using the number-1
        as index. By doing this, we do not encounter the indexes/numbers which are not present in the array.

        To identify which are these missing numbers, we again traverse the loop and find out all
        indexes where the numbers are >0. These index+1 are the numbers which are missing.

        Time complexity - O(n)
        Space Complexity - O(1)
        */
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int num=Math.abs(nums[i]);
            if(nums[num-1]>0)
                nums[num-1]*=-1;
        }

        for(int i=1;i<=nums.length;i++)
            if(nums[i-1]>0)
                result.add(i);
        return result;

    }
}
