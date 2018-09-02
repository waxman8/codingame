/*
 * my bot for  [Bronze League]
 * Coders Strike Back
 * https://www.codingame.com/multiplayer/bot-programming/coders-strike-back
 */
package mdv.codingame.bots.codersstrikeback;

import java.util.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int bcX = 0, bcY = 0, longest = 0;
        int fcX = -1, fcY = -1;
        int lcX = -1, lcY = -1;
        boolean secondRound=false, boost=false, boostUsed=false;
        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int ncX = in.nextInt(); // x position of the next check point
            int ncY = in.nextInt(); // y position of the next check point
            int nd = in.nextInt(); // distance to the next checkpoint
            int na = in.nextInt(); // angle between your pod orientation and the direction of the next checkpoint
            int opponentX = in.nextInt();
            int opponentY = in.nextInt();
            
            if (na < 0) na = na * -1;

            //flag first check point 
            if (fcX == -1) {
                //System.err.println("---S "+ncX+" "+ncY+" I "+x+" "+y);
                fcX = ncX; fcY = ncY;
            } 
            
            //flag second round
            if (ncX == fcX && ncY == fcY && lcY!=-1 && lcX!=ncX && lcY!=ncY) {
                //System.err.println("SECOND ROUND *** ");
                secondRound = true;
            }
            
            //get weighting for each target - decelerate before target strategy
            if (lcY!=-1 && lcX!=ncX && lcY!=ncY) {
                System.err.println("Screech "+nd+" "+na);
            }
                    
            //on first round get best place to use boost
            if (!secondRound) {
                if (na == 0) {
                    if (nd > longest) {
                        longest = nd;
                        bcX = ncX; bcY = ncY;
                    }
                }
            }
            
            if (secondRound) {
                if (na==0 && ncX==bcX && ncY==bcY) {
                    System.err.println("BOOOOOOOSSST!!");
                    boost = true;
                }
            }
            
            int thrust = 0;
            
            System.err.println("ANGLEEEEEE "+na);
            
            if (na > 150) {
                thrust = 10;
            }  else if (na > 120) {
                thrust = 20;
            }  else if (na > 90) {
                thrust = 70;   
            }  else if (na > 60) {
                if (nd > 2000) thrust = 100;
                else thrust = 60;
            } else {
                if (nd > 200) thrust = 100;
                else thrust = 90;
            }
            
            if (thrust > 50 && nd < 1200) thrust = 50;
            if (thrust > 30 && nd < 900) thrust = 20;
            //if (nd < 5000 && na > 0) thrust = 20;
            
            //set last
            lcX=ncX;
            lcY=ncY;

            if (!boost || boostUsed) System.out.println(ncX + " " + ncY + " " + thrust);
            else {
                System.out.println(ncX + " " + ncY + " BOOST");
                boostUsed = true;
            }
            
        }
    }
}