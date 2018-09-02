/*
 * my solution [95%] for
 * Code vs Zombies
 * https://www.codingame.com/multiplayer/optimization/code-vs-zombies
 */
package mdv.codingame.optimisation.codevzombies;

import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int tx = 0, ty = 0;
        // game loop
        while (true) {
            
            int c = 99999;
            int x = in.nextInt();
            int y = in.nextInt();
            int humanCount = in.nextInt();
            for (int i = 0; i < humanCount; i++) {
                int humanId = in.nextInt();
                int humanX = in.nextInt();
                int humanY = in.nextInt();
                if ((Math.abs(x-humanX) + Math.abs(y-humanY)) < c) {
                  c = Math.abs(x-humanX) + Math.abs(y-humanY);
                  tx = humanX; ty = humanY;
                }
            }
            int zombieCount = in.nextInt();
            for (int i = 0; i < zombieCount; i++) {
                int zombieId = in.nextInt();
                int zombieX = in.nextInt();
                int zombieY = in.nextInt();
                int zombieXNext = in.nextInt();
                int zombieYNext = in.nextInt();
            }

            System.out.println(tx+" "+ty); // Your destination coordinates
        }
    }
}