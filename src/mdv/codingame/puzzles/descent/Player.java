/*
 * my solution for
 * The Descent
 * https://www.codingame.com/training/easy/the-descent
 */
package mdv.codingame.puzzles.descent;

import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int h = -1;
        int id = -1;
        
        while (true) {
            h = -1;
            id = -1;
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); // represents the height of one mountain.
                if (mountainH > h) {
                    //System.err.println("TEST "+i);
                    h = mountainH;
                    id = i;
                }
            }
            System.out.println(id); // The index of the mountain to fire on.
        }
    }
}