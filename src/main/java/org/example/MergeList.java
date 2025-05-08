package org.example;

public class MergeList {

    public static class ListNode {
     int val;
     ListNode next;
      ListNode() {}
        ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if(list1!= null && list2!= null){
            if(list1.val < list2.val){
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }
            else{
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
        if(list1==null){
            return list2;
        }
        return list1;

    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);

        // List 2: 2 -> 4 -> 6
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);

        MergeList solution = new MergeList();
        ListNode merged = solution.mergeTwoLists(list1, list2);

        // Print merged list
        ListNode current = merged;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : ""));
            current = current.next;
        }


    }
}
