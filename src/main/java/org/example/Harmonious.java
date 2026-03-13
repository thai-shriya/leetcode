package org.example;

import java.util.Arrays;

public class Harmonious {
    public int findLHS(int[] nums) {
        /*
        First sort the array. Then take two pointers and check the difference between the
        nums array elements. If the difference is 1, then calculate the length and then move the right pointer
        forward until the difference is 1.
        If the difference is greater than 1 then move the left pointer.
        */

        Arrays.sort(nums);
        int l=0,r=1, res=0;

        while(r< nums.length){
            int diff = nums[r] - nums[l];
            if(diff == 1){
                res = Math.max(res, r-l+1);
            }
            if(diff <=1)
                r++;
            else
                l++;
        }
        return res;

    }
}
