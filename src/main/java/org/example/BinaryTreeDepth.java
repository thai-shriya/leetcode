package org.example;

public class BinaryTreeDepth {
    
     public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
         
     TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
     }
  }
    public int maxDepth(TreeNode root) {
        /*
        We need to take the maximum depth of the tree. So, we recursiverly
        traverse in both left and right directions.

        */
        if(root==null)
            return 0;
        int lh=maxDepth(root.left);
        int rh=maxDepth(root.right);
        return 1+ Math.max(lh,rh);

    }

    public static void main(String[] args) {
        BinaryTreeDepth btd = new BinaryTreeDepth();

        TreeNode root1 = null;
        System.out.println("Test Case 1: " + btd.maxDepth(root1)); // Expected: 0

        // Test case 2: Single node tree
        TreeNode root2 = btd.new TreeNode(1);
        System.out.println("Test Case 2: " + btd.maxDepth(root2)); // Expected: 1

        // Test case 3: Full binary tree with 3 levels
        TreeNode root3 = btd.new TreeNode(1,
                btd.new TreeNode(2, btd.new TreeNode(4), btd.new TreeNode(5)),
                btd.new TreeNode(3)
        );
        System.out.println("Test Case 3: " + btd.maxDepth(root3)); // Expected: 3

        // Test case 4: Left-skewed tree
        TreeNode root4 = btd.new TreeNode(1,
                btd.new TreeNode(2,
                        btd.new TreeNode(3,
                                btd.new TreeNode(4), null), null), null);
        System.out.println("Test Case 4: " + btd.maxDepth(root4)); // Expected: 4

        // Test case 5: Right-skewed tree
        TreeNode root5 = btd.new TreeNode(1, null,
                btd.new TreeNode(2, null,
                        btd.new TreeNode(3, null,
                                btd.new TreeNode(4))));
        System.out.println("Test Case 5: " + btd.maxDepth(root5)); // Expected: 4

        // Test case 6: Unbalanced tree
        TreeNode root6 = btd.new TreeNode(1,
                btd.new TreeNode(2, null, btd.new TreeNode(4)),
                btd.new TreeNode(3));
        System.out.println("Test Case 6: " + btd.maxDepth(root6)); // Expected: 3
    }
}
