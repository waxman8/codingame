/*
my solution for
Shadows of the night E1
*/
package mdv.codeingame.puzzles.shadows.one;

import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int x = in.nextInt();
        int y = in.nextInt();
        
        int minY = -1, minX = -1;
        int maxY = H, maxX = W;

        int a = 0, b = 0;
        // game loop
        while (true) {
            
            // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            String bat = in.next(); 
            //System.err.println(bat+" "+minY+" "+maxY);
            if (bat.contains("U"))  { maxY=y; y-=(y-minY)/2; }
            else if (bat.contains("D"))  { minY=y; y+=(maxY-y)/2; }
           
            if (bat.contains("L"))  { maxX=x; x-=(x-minX)/2; }
            else if (bat.contains("R"))  { minX=x; x+=(maxX-x)/2; }
            
            System.out.println(x+" "+y);
        }
    }
}