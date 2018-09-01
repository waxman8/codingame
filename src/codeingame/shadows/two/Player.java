/*
my solution for
Shadows of the night E2
*/
package codeingame.shadows.two;


import java.util.*;

class Player {
    static Scanner in = new Scanner(System.in);
    public static void main(String args[]) {
        
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int x = in.nextInt();
        int y = in.nextInt();
        
        int minY = -1, minX = -1;
        int maxY = H, maxX = W;
        int a = 0, b = 0;
        
        String myBat = "X";
        // game loop
        while (true) {
            
            // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            String bat = in.next(); 
            if (bat.compareTo("UNKNOWN")==0) {
                System.out.println(W/2 +" "+ H/2);
                continue;
            }
            
            myBat=getDirection(x,y);
            System.out.println("GO: "+myBat);
            
            //System.err.println(bat+" "+minY+" "+maxY);
            if (myBat.contains("U"))  { maxY=y; y-=(y-minY)/2; }
            else if (myBat.contains("D"))  { minY=y; y+=(maxY-y)/2; }
           
            if (myBat.contains("L"))  { maxX=x; x-=(x-minX)/2; }
            else if (myBat.contains("R"))  { minX=x; x+=(maxX-x)/2; }
            
            System.out.println(x+" "+y);
        }
    }
    
    static String getDirection(int x, int y) {
        String a = "";
        String res = "";
        for(int i=0;i<2;i++) {
            if (i%2==0) {
                System.out.println((x+1)+" "+y);
                a=in.next();
                if (a.compareTo("WARMER")==0) res=res+"R";
                else if (a.compareTo("COLDER")==0) res=res+"L";
            } else  {
                System.out.println(x+" "+(y+1));
                a=in.next();
                if (a.compareTo("WARMER")==0) res=res+"D";
                else if (a.compareTo("COLDER")==0) res=res+"U";
            }
        }
        return res;
    }
}