/*
 * my solution for
 * Brackets, extreme edition.
 * https://www.codingame.com/training/easy/brackets-extreme-edition
 */
package mdv.codingame.puzzles.brackets;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        Deque<Character> stack = new ArrayDeque<>();
        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put(']','[');
        pairs.put('}','{');
        pairs.put(')','(');
        Scanner in = new Scanner(System.in);
        String expression = in.next();
        boolean correct = true;
        for (char c : expression.toCharArray()) {
            System.err.println(c);
            if (c=='('||c=='{'||c=='[') {
                stack.push(c);
            } else if (c==')'||c=='}'||c==']') {
                if (stack.peek()==pairs.get(c)) {
                    stack.pop();
                } else {
                    correct = false;
                }
            }
        }
        if (stack.size() > 0) correct=false;
        if (correct) System.out.println("true");
        else System.out.println("false");
    }
}