/*
 * my solution for
 * Chuck Norris
 * https://www.codingame.com/training/easy/chuck-norris
 */
package mdv.codingame.puzzles.chucknorris;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();

        Character x = '2';
        String u = "";
        String st = "";
        for (Character ch : MESSAGE.toCharArray()) {
            
            String bin = Integer.toString((int)ch,2);
            while(bin.length()<7) bin = "0"+bin;
            st+=bin;
        }
        System.err.println("{"+st);
        //System.out.println("."+ch);
        for (Character b : st.toCharArray()) {
            if (b != x) {
                if (b=='0') u = u + " 00 0";
                if (b=='1') u = u + " 0 0";
                x = b;
            } else {
                u = u + "0";
            }
        }
        
        System.err.println("--"+u);
        System.out.println(u.trim());
    }
}