/*
 * my solution for
 * Scrabble
 * https://www.codingame.com/training/medium/scrabble
 */
package mdv.codingame.mediumpuzzles.scrabble;

import java.util.*;
 
class Solution {
   
    static Map<Character, Integer> values = new HashMap<>();
    static List<Word> words = new ArrayList<Word>();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            words.add(new Word(in.nextLine()));
        }
        String LETTERS = in.nextLine();
        setVals();
                
        for (Word w : words) {
            String allowed = LETTERS;
            for (char c : w.word.toCharArray()) {
                System.err.println(c);
                if(allowed.indexOf(c) == -1) {
                    w.value = -1;
                    break;
                } else {
                    w.value += values.get(c);
                    allowed = allowed.replaceFirst(Character.toString(c),"");
                }
            }
        }
        Collections.sort(words);
       
        System.err.println(words);
       
        System.out.println(words.get(0).word);
    }
   
    private static void setVals() {
        values.put('a', 1);
        values.put('b', 3);
        values.put('c', 3);
        values.put('d', 2);
        values.put('e', 1);
        values.put('f', 4);
        values.put('g', 2);
        values.put('h', 4);
        values.put('i', 1);
        values.put('j', 8);
        values.put('k', 5);
        values.put('l', 1);
        values.put('m', 3);
        values.put('n', 1);
        values.put('o', 1);
        values.put('p', 3);
        values.put('q', 10);
        values.put('r', 1);
        values.put('s', 1);
        values.put('t', 1);
        values.put('u', 1);
        values.put('v', 4);
        values.put('w', 4);
        values.put('x', 8);
        values.put('y', 4);
        values.put('z', 10);
    }
}
 
class Word implements Comparable<Word>{
    String word;
    Integer value = 0;
 
    public Word(String w) {
        word = w;
    }
   
    @Override
    public int compareTo(Word o) {
        return o.value.compareTo(value);
    }
   
    @Override
    public String toString() {
        return word +" " + value;
    }
}