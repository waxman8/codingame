/*
 * my solution for 
 * mars lander Ep 1
 * https://www.codingame.com/training/easy/mars-lander-episode-1
 */
package mdv.codingame.puzzles.marslander;

import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        int landY = 0;
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
        }

        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).

            System.err.println("> "+power+" "+Y+" "+landY+" F"+fuel);
            
            if (vSpeed < -20 && vSpeed >= -30) power = 2;
            else if (vSpeed < -30 && vSpeed >= -35) power = 3;
            else if (vSpeed < -35) {
                power = 4;
            } else power = 0;
            
            // 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
            System.out.println("0 "+power);
        }
    }
}