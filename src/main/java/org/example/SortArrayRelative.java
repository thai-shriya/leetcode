package org.example;

import java.util.TreeMap;

public class SortArrayRelative {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        /*
        Treemap helps to store the elements in ascending order. So, first we store the freq of
        all the numbers of nums1 array in treemap.
        Then, iterate through arr2 and change the order of elements of arr1 by using treemap after replacing
        each number, remove that from treemap simultaneously.
        At the end, we remain with elements that are present in arr1 and not present in arr2, in ascending order.
        Paste the remaining elements in the arr1.
        */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int n : arr1)
            map.put(n, map.getOrDefault(n, 0) + 1);
        int i = 0;
        for(int n : arr2) {
            for(int j = 0; j < map.get(n); j++) {
                arr1[i++] = n;
            }
            map.remove(n);
        }
        for(int n : map.keySet()){
            for(int j = 0; j < map.get(n); j++) {
                arr1[i++] = n;
            }
        }
        return arr1;
    }
}
