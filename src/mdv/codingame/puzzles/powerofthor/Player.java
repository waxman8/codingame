/*
 * my solution for 
 * The Power of Thor
 * https://www.codingame.com/training/easy/power-of-thor-episode-1
 * this is also my code golf version [as little code as possible] 
 */
package mdv.codingame.puzzles.powerofthor;

import java.util.*;
class Player{
public static void main(String z[]){
Scanner i=new Scanner(System.in);
int a=i.nextInt(),b=i.nextInt(),x=i.nextInt(),y=i.nextInt();
for(;;){i.nextInt();String m="";if(x>a){m="W";x--;}if(x<a){m="E";x++;}if(y>b){m="N"+m;y--;}if(y<b){m="S"+m;y++;}
System.out.println(m);}}} 