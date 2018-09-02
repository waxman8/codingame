/*
 * my solution for
 * ANEO: Cruise control
 * https://www.codingame.com/training/medium/aneo
 */
package mdv.codingame.mediumpuzzles.cruisecontrolaneo;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        
        List<Light> lights = new ArrayList<>();
        
        Scanner in = new Scanner(System.in);
        int speed = in.nextInt();
        int lightCount = in.nextInt();
        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
            lights.add(new Light(distance, duration));
        }

        int max = 0;
        for (int s=speed;s>0;s--) {
            boolean ok = false;
            for (Light l : lights) {
                if(!l.greenAtSpeed(s)) { ok=false; break; } //hit a red
                else ok=true;
            }
            if (ok) { max=s; break; }
        }
        System.out.println(max);
    }
}

class Light {
    int distance = 0;
    int duration = 0;
    
    public Light(int di, int du) {
        distance = di;
        duration = du;
    }
    
    public boolean greenAtSpeed(int s) {
        Double seconds = ((double)distance / ((double)s * 5 / 18));
        seconds = Math.round (seconds * 1000000.0) / 1000000.0;  
        int e = seconds.intValue();
        int interval = (int)Math.floor( e / duration );
        boolean green = (interval%2==0);
        //System.err.println("For speed "+s+" you reach me at "+seconds+" "+e+" "+interval+" ");
        //System.err.println("For speed "+s+" you reach me at "+green);
        return green;
    }
}