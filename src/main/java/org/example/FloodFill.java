package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        /*
        To change the color of the cells that are directly adjacent and share the same color as the starting pixel, 
        we can use either bfs or dfs to solve this problem. 
        I am using bfs. So, first we change the color of the starting node and find all the adjacent nodes to the starting node which are of same old color of the node.
        Then change the color of those nodes which satisfy the condition and repeat the same process for all these nodes. 
        For that, we use a queue, to add the adjacent nodes and poll each node and find the neighbors. 
        After the end of the process and queue is empty, we would have reached to the solution and finally return the modfified array
        
        Time complexity - O(m * n).
        Space Complexity - O(m * n).
        */

        int m = image.length;
        int n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int prev_color = image[sr][sc];
        image[sr][sc] = color;
        queue.add(new int[]{sr, sc});

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            for(int i=0;i<directions.length; i++){
                int r = directions[i][0] + temp[0];
                int c = directions[i][1] + temp[1];
                if(r >= 0 && c >= 0 && r < m && c < n && image[r][c]==prev_color && image[r][c]!=color){
                    image[r][c] = color;
                    queue.offer(new int[]{r,c});

                }

            }
        }
        return image;
    }


    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();

        // Test case 1: Simple 3x3 matrix
        int[][] image1 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] result1 = floodFill.floodFill(image1, 1, 1, 2);
        for (int[] row : result1) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Test case 2: Single pixel
        int[][] image2 = {{0}};
        int[][] result2 = floodFill.floodFill(image2, 0, 0, 2);
        for (int[] row : result2) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Test case 3: Non-square matrix
        int[][] image3 = {{1, 1, 1, 1}, {1, 1, 0, 0}, {1, 0, 1, 1}};
        int[][] result3 = floodFill.floodFill(image3, 0, 0, 3);
        for (int[] row : result3) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Test case 4: No change needed
        int[][] image4 = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        int[][] result4 = floodFill.floodFill(image4, 1, 1, 2); // New color is the same as the old color
        for (int[] row : result4) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Test case 5: Large matrix with isolated regions
        int[][] image5 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] result5 = floodFill.floodFill(image5, 1, 1, 3);
        for (int[] row : result5) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
