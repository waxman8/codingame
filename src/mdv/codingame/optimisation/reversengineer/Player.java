/*
 * my solution for
 * CodinGame sponsored contest : reverse engineer
 * https://www.codingame.com/multiplayer/optimization/codingame-sponsored-contest
 */
package mdv.codingame.optimisation.reversengineer;

import java.util.*;

class Player {
    
    static HashMap<String, String> move = new HashMap<String, String>();
    
    public static void main(String args[]) {
        init();
        Scanner in = new Scanner(System.in);
        int maxX = in.nextInt();
        int maxY = in.nextInt();
        int numNodes = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        System.err.println("maxX:"+maxX+" maxY:"+maxY+ " numNodes:"+numNodes); 
        
        //state
        int[][] maze = new int[maxX+1][maxY+1];
        for (int x=0;x<maxX;x++) {
            for (int y=0;y<maxY;y++) {
                maze[x][y]=0;
            }
        }
                         
        while (true) {
            
            String above = in.nextLine();
            String right = in.nextLine();
            String below = in.nextLine();
            String left = in.nextLine();
            System.err.println("above: "  + above + " right: " + right + 
                               " below: " + below + " left: "  + left);
            
            int x=-1, y=-1;
            for (int i = 0; i < numNodes; i++) {
                x = in.nextInt();
                y = in.nextInt();
                //if (i<numNodes-1) maze[x][y]=7;
                System.err.println("5: " + x + " 6: " + y);
            }
            in.nextLine();
            
            if (above.compareTo("#") != 0 && y-1>=0 && maze[x][y-1]==0) {
                maze[x][y]++;
                System.out.println(move.get("UP"));
            }  else if (below.compareTo("#") != 0 && y+1<maxY && maze[x][y+1]==0) {
                maze[x][y]++;
                System.out.println(move.get("DOWN"));
            } else if (left.compareTo("#") != 0 && x-1>=0 && maze[x-1][y]==0) {
                maze[x][y]++;
                System.out.println(move.get("LEFT"));
            } else if (right.compareTo("#") != 0 && x+1<maxX && maze[x+1][y]==0) {
                maze[x][y]++;
                System.out.println(move.get("RIGHT"));
            }
            else if (above.compareTo("#") != 0 && maze[x][y-1]==1) {
                maze[x][y]++;
                System.out.println(move.get("UP"));
            } else if (left.compareTo("#") != 0 && maze[x-1][y]==1) {
                maze[x][y]++;
                System.out.println(move.get("LEFT"));
            } else if (below.compareTo("#") != 0 && maze[x][y+1]==1) {
                maze[x][y]++;
                System.out.println(move.get("DOWN"));
            } else if (right.compareTo("#") != 0 && maze[x+1][y]==1) {
                maze[x][y]++;
                System.out.println(move.get("RIGHT"));
            }else {
                System.out.println(move.get("STAY"));
            }
            
            for (int a=0;a<maxY;a++) {
                for (int b=0;b<maxX;b++) {
                    System.err.print(maze[b][a]+" ");
                }
                System.err.println("");
            }
        }
    }
    public static void init() {
        move.put("STAY",  "B");
        move.put("LEFT",  "E");
        move.put("RIGHT", "A");
        move.put("UP",    "C");
        move.put("DOWN",  "D");
    }
    public static boolean isOccupied() {
        return false;
    }
}