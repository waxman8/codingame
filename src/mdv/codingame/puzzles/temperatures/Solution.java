/*
 * my solution for
 * Temperatures
 * https://www.codingame.com/training/easy/temperatures
 * also my code golf version, as little as possible code
 */
package mdv.codingame.puzzles.temperatures;

import java.util.*;
class Solution {
public static void main(String args[]) {
Scanner in=new Scanner(System.in);
int n=in.nextInt(),m=10001;
System.err.println(">"+n);
boolean onlyOne = (n==0);
for (int i=0;i<n;i++) {
    int t=in.nextInt(); 
    if (Math.abs(t)<Math.abs(m) || onlyOne) m=t;
    else if (Math.abs(t)==Math.abs(m)) {
        if (t>m) m=t;
    }
}
if (onlyOne) m =0;
System.out.println(m);}}