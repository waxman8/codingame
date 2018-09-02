/*
 * my solution for
 * horse racing duals
 * https://www.codingame.com/training/easy/horse-racing-duals
 */
package mdv.codingame.puzzles.horseracingduals;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        List<Integer> L = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            L.add(in.nextInt());
        }

        Collections.sort(L);
        int d = L.get(L.size()-1);
        int p = -1;
        for(Integer i : L) {
            if (p!=-1)  {
                if (Math.abs(p-i) < d) d = Math.abs(p-i);
            }
            p=i;
        }
        System.out.println(d);
    }
}