package org.example;

public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        /* Use frequency array to count the freq of eeach element and then traverse over the freq array to find out the missing and repeated element based on the freq.
        if freq is 2 then element is duplicate and if freq is 0 then that element is missing*/

        int[] arr = new int[nums.length +1];
        int dup = -1, missing = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]] += 1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0)
                missing = i;
            else if (arr[i] == 2)
                dup = i;
        }
        return new int[]{dup, missing};
    }
}
