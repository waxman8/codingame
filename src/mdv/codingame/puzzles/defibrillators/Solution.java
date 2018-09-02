/*
 * my solution for
 * defibrillators
 * https://www.codingame.com/training/easy/defibrillators
 */
package mdv.codingame.puzzles.defibrillators;

import java.util.*;

class Solution {

    public static void main(String args[]) {
        List<Defib> defibs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String LON = in.next().replace(',','.');
        String LAT = in.next().replace(',','.');
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            defibs.add(new Defib(in.nextLine()));
        } 

        String answer = "";
        Double check = -1d;
        
        for (Defib d : defibs) {
            Double thisDist = d.getDist(Double.parseDouble(LON), Double.parseDouble(LAT));
            if (check.compareTo(-1d)==0 || thisDist.compareTo(check)<0) {
                answer = d.name;
                check = thisDist;
            }
        }
        System.out.println(answer);
    }
}

class Defib {
    public String name;
    private Double longitude, latitude;
    
    public Defib(String s) {
        String[] result = s.split(";");
        int i = 0;
        for (String tmp : result) {
            i++;
            if (i==2) name =tmp;
            if (i==5) longitude = Double.parseDouble(tmp.replace(',', '.'));
            if (i==6) latitude = Double.parseDouble(tmp.replace(',', '.'));
        }
        toRadians();
    }
    
    private void toRadians() {
        longitude = longitude * Math.PI/180;
        latitude = latitude * Math.PI/180;
    }
    
    public Double getDist(Double longitudeB, Double latitudeB) {
        longitudeB = longitudeB * Math.PI/180;
        latitudeB = latitudeB * Math.PI/180;
        return Math.sqrt(  Math.pow(  (longitudeB-longitude) * Math.cos((latitude+latitudeB)/2) , 2) + Math.pow( (latitudeB-latitude) , 2) ) * 6371;
        
    }
    
}