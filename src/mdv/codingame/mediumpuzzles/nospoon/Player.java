/*
 * my solution for
 * There is no Spoon - Ep 1
 * https://www.codingame.com/training/medium/there-is-no-spoon-episode-1
 */
package mdv.codingame.mediumpuzzles.nospoon;

import java.util.*;
/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        int[][] nodes = new int[height][width];
        //System.err.println("H"+height+"W"+width);
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            int w=0;
            for (Character c : line.toCharArray()) {
                //System.err.println("assign "+w+"/"+i+":"+c);
                if (c=='0') nodes[i][w] = 1;
                else nodes[i][w] = 0;
                w++;        
            }
        }

        String me = "";
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                
                //System.err.println("?? "+w+"/"+h+":"+nodes[h][w]);
                if (nodes[h][w]!=1) continue;
                me = w+" "+h;
                
                //look to the right
                int rw=-1, rh=-1;
                for (int tw=w+1;tw<=width-1;tw++) {
                    if (nodes[h][tw]==1) {
                        rw=tw;
                        rh=h;
                        break;
                    }
                }
                
                //look down
                int bw=-1, bh=-1;
                for (int th=h+1;th<=height-1;th++) {
                    if (nodes[th][w]==1) {
                        bw=w; 
                        bh=th;
                        break;
                    }
                }
                System.out.println(me+" "+rw+" "+rh+" "+bw+" "+bh);
            }
        }
    }
}