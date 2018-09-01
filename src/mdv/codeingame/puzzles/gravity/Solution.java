/*
my solution for
gravity tumbler
*/
package mdv.codeingame.puzzles.gravity;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        int count = in.nextInt();
        int[][] grid = new int[height][width];
        int[][] gridOdd = new int[width][height];
        
        if (in.hasNextLine()) {
            in.nextLine();
        }
        //populate the input grid
        for (int i = 0; i < height; i++) {
            String raster = in.nextLine();
            int j = -1;
            for (char c : raster.toCharArray()) {
                j++;
                if (c=='#') grid[i][j] = 0; 
                else grid[i][j] = 1;
            }
        }

        for (int i=1;i<=count;i++)
        {
            if(i%2==0) {
                sortGrid(gridOdd, height, width);
                packOver(gridOdd, grid, height, width);
            } else {
                sortGrid(grid, width, height);
                packOver(grid, gridOdd, width, height);
            }
        }
        
        if (count%2==0) {
            printGrid(grid, width, height);
        } else {
            printGrid(gridOdd, height, width);
        }
        
    }
    
    static void printGrid(int[][] grid, int w, int h) {
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                if (grid[i][j]==0) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println("");
        }
    }
    
    static void sortGrid(int[][] grid, int w, int h) {
        for (int i=0;i<h;i++) {
            Arrays.sort(grid[i]);
        }
    }
    
    static void packOver(int[][] gridFrom, int[][] gridTo, int w, int h) {
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                gridTo[w-1-j][i]=gridFrom[i][j];
            }
        }
    }
}
