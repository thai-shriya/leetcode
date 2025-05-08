package org.example;

public class BinarySearch {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1,mid=0;
        while(low<=high)
        {
            mid = (low + high)/2;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid]> target)
                high = mid-1;
            else
                low = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();

        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        System.out.println(bs.search(nums1, 9));    // Expected: 4
        System.out.println(bs.search(nums1, 2));    // Expected: -1

        int[] nums2 = {1};
        System.out.println(bs.search(nums2, 1));    // Expected: 0
        System.out.println(bs.search(nums2, 0));    // Expected: -1

        int[] nums3 = {2, 4, 6, 8, 10};
        System.out.println(bs.search(nums3, 8));    // Expected: 3
        System.out.println(bs.search(nums3, 5));    // Expected: -1
    }
}
