/*
 * my solution for
 * ASCII art
 * https://www.codingame.com/training/easy/ascii-art
 */
package mdv.codingame.puzzles.asciiart;

import java.util.*;

class Solution {
    
    
        public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        String[] m=new String[H];
        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        String t = in.nextLine().toUpperCase();
        //System.err.println(t);
        
        for (int i = 0; i < H; i++) {
            m[i]=in.nextLine();
        }
        
        for (int x=0;x<H;x++) {
            String row = "";
            for (Character c : t.toCharArray()) {
                //character in the string
                int i = 0;
                if ((int)c < 65 || (int)c > 90) i = 26*L;
                else i = ((int)c-65)*L;
                //System.err.println("Char "+(int)c);
                
                row = row + m[x].substring(i, i+L);
            }
            System.out.println(row);
        }
        //System.out.println("");
    }
}