/*
 * my solution for
 * MIME type
 * https://www.codingame.com/training/easy/mime-type
 */
package mdv.codingame.puzzles.mimetype;
import java.util.*;

class Solution {

    public static void main(String args[]) {
        Map<String, String> types = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        for (int i = 0; i < N; i++) {
            types.put(in.next().toUpperCase(), in.next());
        }
        in.nextLine();
        for (int i = 0; i < Q; i++) {
            String r = "";
            String f = in.nextLine(); // One file name per line.
            if (!f.contains(".")) r = "UNKNOWN";
            else {
                String k = f.substring(f.lastIndexOf(".")+1, f.length()).toUpperCase();
                System.err.println(k);
                if (types.containsKey(k)) r = types.get(k);
                else r = "UNKNOWN";
            }
            System.out.println(r);
        }
    }
}