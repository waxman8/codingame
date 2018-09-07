/*
 * my solution for 
 * Don't panic
 * https://www.codingame.com/multiplayer/codegolf/don%27t-panic
 */
package mdv.codingame.puzzles.dontpanic;
import java.util.*;
class Player {public static void main(String[]a){
Scanner in = new Scanner(System.in);
int n=in.nextInt(),w=in.nextInt(),r=in.nextInt(),x=in.nextInt(),t=in.nextInt(),j=in.nextInt(),q=in.nextInt(),l=in.nextInt();
int[] o=new int[l];
for (int i = 0; i < l; i++) {int F=in.nextInt(),P=in.nextInt(); o[F]=P;}
for (;;) { int f=in.nextInt(),p=in.nextInt(); String d=in.next(),z="WAIT";
if (f!=x){
if (d.compareTo("RIGHT")==0 && o[f]<p) z="BLOCK"; 
else if (d.compareTo("LEFT")==0 && o[f]>p) z="BLOCK"; 
else if (d.compareTo("RIGHT")==0 && p>w-2) z="BLOCK"; 
else if (d.compareTo("LEFT")==0 && p<=1 && o[f]!=1) z="BLOCK";}  
if (f==x){
if (d.compareTo("RIGHT")==0 && t<p) z="BLOCK";
else if (d.compareTo("LEFT")==0 && t>p) z="BLOCK";
else z="WAIT";}
System.out.println(z);}}}




/*  original .. 
class Player {public static void main(String[]a){
        Scanner in = new Scanner(System.in);
        int n=in.nextInt(),w=in.nextInt(),r=in.nextInt(),x=in.nextInt(),t=in.nextInt(),j=in.nextInt(),q=in.nextInt(),l=in.nextInt();
        int[] o=new int[l];
        for (int i = 0; i < l; i++) {
            int F = in.nextInt(), P = in.nextInt();
            o[F]=P;
        }
        for (;;) {
            int f = in.nextInt(), p = in.nextInt();
            String d = in.next(), z = "WAIT";
            if (f!=x) {
                if (f==-1 && p==-1 || p==o[f]) {
                    z = "WAIT";
                } else if (d.compareTo("RIGHT")==0 && o[f]<p) {
                    z = "BLOCK";
                } else if (d.compareTo("LEFT")==0 && o[f]>p) {
                    z = "BLOCK";
                } else if (d.compareTo("RIGHT")==0 && p>w-2) {
                    z = "BLOCK";
                } else if (d.compareTo("LEFT")==0 && p<=1) {
                    z = "BLOCK";
                } 
            }  
            if (f==x) {
                if (d.compareTo("RIGHT")==0 && t<p) z="BLOCK";
                else if (d.compareTo("LEFT")==0 && t>p) z="BLOCK";
                else z="WAIT";
            }
            System.out.println(z);
        }
    }
}

*/