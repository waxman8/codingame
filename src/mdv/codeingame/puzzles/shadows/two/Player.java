/*
my solution for
Shadows of the night E2
*/
package mdv.codeingame.puzzles.shadows.two;

import java.util.*;

class Player {
    static Scanner in = new Scanner(System.in);
    static int x=0, y=0;
    public static void main(String args[]) {
        
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        x = in.nextInt();
        y = in.nextInt();
        
        int minY = -1, minX = -1;
        int maxY = H, maxX = W;
        int a = 0, b = 0;
        
        String myBat = "X";
        // game loop
        while (true) {
            
            // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            String bat = in.next(); 
            /*if (bat.compareTo("UNKNOWN")==0) {
                x=W/2; y=H/2;
                System.out.println(x +" "+ y);
                continue;
            }*/
            
            myBat=getDirection(W,H);
            System.err.println("GO: "+myBat);
            
            //System.err.println(bat+" "+minY+" "+maxY);
            if (myBat.contains("U"))  { maxY=y; y-=(y-minY)/2; }
            else if (myBat.contains("D"))  { minY=y; y+=(maxY-y)/2; }
           
            if (myBat.contains("L"))  { maxX=x; x-=(x-minX)/2; }
            else if (myBat.contains("R"))  { minX=x; x+=(maxX-x)/2; }
            
            System.out.println(x+" "+y);
        }
    }
    
    static String getDirection(int W, int H) {
        String a = "";  
        String res = "";
        for(int i=0;i<2;i++) {
            if (i%2==0 && x < W-1) {
                System.out.println((x+1)+" "+y);
                a=in.next();
                if (a.compareTo("WARMER")==0) res=res+"R";
                else if (a.compareTo("COLDER")==0) res=res+"L";
            } else if (i%2==0 && x > 0) {
                System.out.println((x-1)+" "+y);
                a=in.next();
                if (a.compareTo("WARMER")==0) res=res+"L";
                else if (a.compareTo("COLDER")==0) res=res+"R";    
            } else if (i%2!=0 && y < H-1)  {
                System.out.println(x+" "+(y+1));
                a=in.next();
                if (a.compareTo("WARMER")==0) res=res+"D";
                else if (a.compareTo("COLDER")==0) res=res+"U";
            } else if (i%2!=0 && y > 0)  {
                System.out.println(x+" "+(y-1));
                a=in.next();
                if (a.compareTo("WARMER")==0) res=res+"U";
                else if (a.compareTo("COLDER")==0) res=res+"D";
            }
        }
        return res;
    }
}