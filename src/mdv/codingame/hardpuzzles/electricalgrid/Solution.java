/*
 * my solution [75%] for 
 * Electrical grid
 * https://www.codingame.com/training/hard/electrical-grid
 */
package mdv.codingame.hardpuzzles.electricalgrid;

import java.util.*;

class Solution {

    private ArrayList<Connection> conns = new ArrayList<>();
    private ArrayList<Integer> houses = new ArrayList<>();
    private int numberConnections = 0;
    private int totalCost = 0;
    private ArrayList<Connection> connsUsed = new ArrayList<>();;
    
    public static void main(String args[]) {
        ArrayList<Connection> tmp = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        for (int i = 0; i < M; i++) {
            int house1 = in.nextInt();
            int house2 = in.nextInt();
            int cost = in.nextInt();
            tmp.add(new Connection(house1, house2, cost));
        }
          
            Solution s = new Solution(tmp);
            s.solve();
            s.printOutput();
            
    }
    
    public Solution(ArrayList<Connection> c) {
        conns = c;
    }
    
    //solve the puzzle and print the answer
    public void solve() {
        listHouses();
        for (Integer h : houses) {
            ArrayList<Integer> ignoreList = new ArrayList<>();
            ArrayList<Connection> thePath = new ArrayList<>();
            cheapestRoute(h, 0, ignoreList, thePath);
            addToUsed(thePath);
        }
    }
    
    //get the distinct list of houses in the puzzle
    public void listHouses() {
        for (Connection c : conns) {
            if (!houses.contains(c.house1)) houses.add(c.house1);
            if (!houses.contains(c.house2)) houses.add(c.house2);
        }
    }
    
    //add everything up
    private void addToUsed(ArrayList<Connection> theConns) {
        for (Connection c : theConns) {
            if (!connsUsed.contains(c)) {
                connsUsed.add(c);
                totalCost += c.cost;
            }
        }
    }
    
    //print me
    public void printOutput() {
        System.out.println(connsUsed.size()+ " " + totalCost);
        Collections.sort(connsUsed);
        for (Connection c : connsUsed) {
            System.out.println(c);
        }
    }
    
    //find the cheapest path from a node back to (1)
    public int cheapestRoute(int house, int costSoFar, ArrayList<Integer> ignoreList, ArrayList<Connection> thePath) {
        
        int cost = 0;
        
        int cheapestCost = -1;
        int cheapestVia = -1;
        ArrayList<Connection> theFollowingPath = new ArrayList<>();
        ArrayList<Connection> theCheapestPath = new ArrayList<>();
        
        //root node to return as such
        if (house == 1) {
            ignoreList.remove((Integer)house);
            return costSoFar;
        }
                    
        //get all options from this location
        for (Connection o : conns) {
            int next = -1;
            if (o.house1 == house) next = o.house2;
            else if (o.house2 == house) next = o.house1;
            
            //if (next != -1) System.out.println("consider from " +house+ " to " +next);
            if (next != -1 && !ignoreList.contains(next)) {
                //System.out.println("check from "+house+" to "+next+" cheapest so far "+cheapestCost);
                ignoreList.add(next);
                theFollowingPath = new ArrayList<>();
                theFollowingPath.add(o);
                int checkCost = cheapestRoute(next, o.cost, ignoreList, theFollowingPath);
                //System.out.println(" > > " + checkCost);
                //ignore branches that end in leaf nodes
                if (checkCost != -1) {
                    if (cheapestCost == -1 || checkCost < cheapestCost) {
                        cheapestCost = checkCost;
                        cheapestVia = next;
                        theCheapestPath = theFollowingPath;
                    }
                }  
            }
        }
        //wrap things up and return
        ignoreList.remove((Integer)house);
        if (cheapestCost == -1) return -1;
        else {
            for (Connection c : theCheapestPath) {
                thePath.add(c);
            }
            return cheapestCost + costSoFar;
        }
    }
    
    //TO DO : model around houses rather - then find all connections and their cost
    
    //print the sorted list
    public void printSorted(ArrayList<Connection> connections) {
        Collections.sort(connections);
        connections.forEach((c) -> {
            System.out.println(c);
        });
    }
}

class Connection implements Comparable<Connection> {
    int house1;
    int house2;
    int cost;
    
    public Connection(int h1, int h2, int c) {
        house1 = h1;
        house2 = h2;
        cost = c;
    }
    
    @Override
    public int compareTo(Connection a) {
        if (this.house1 > a.house1) return 1;
        else if (this.house1 == a.house1 && this.house2 > a.house2) return 1;
        else if (this.house1 < a.house1) return -1;
        else if (this.house1 == a.house1 && this.house2 < a.house2) return -1;
        else return 0;
    }
    
    @Override
    public String toString() {
        return this.house1+" "+this.house2+" "+this.cost;
    }
    
}