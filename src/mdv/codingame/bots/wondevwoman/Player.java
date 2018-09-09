/*
 * my bot for
 * Wondev Woman
 * https://www.codingame.com/multiplayer/bot-programming/wondev-woman
 */
package mdv.codingame.bots.wondevwoman;

import java.util.*;

class Player {

    int size;
    int unitsPerPlayer;
    Position me;
    Position[][] map;
    String strategyMove = "X";
    String strategyBuild = "X";
    List<Action> actions = new ArrayList<>();
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        //construct with size and unitsPerPlayer
        Player player=new Player(in.nextInt(), in.nextInt());
        // game loop
        while (true) {
            //refresh map
            player.populateMap(in);
            
            //refresh my positions
            for (int i=0;i<player.unitsPerPlayer;i++) {
                int myX = in.nextInt();
                int myY = in.nextInt();
                System.err.println("ME at "+myX+"/"+myY);
                player.map[myY][myX].me=true;
                player.me=player.map[myY][myX];
            }
            
            //refresh enemy positions
            for (int i=0;i<player.unitsPerPlayer;i++) {
                int otherX = in.nextInt();
                int otherY = in.nextInt();
                player.map[otherY][otherX].enemy=true;
            }
            
            //refresh my legal actions
            player.actions.clear();
            int legalActions = in.nextInt();
            for (int i=0;i<legalActions;i++) {
                String atype = in.next();
                int index = in.nextInt();
                String dir1 = in.next();
                String dir2 = in.next();
                player.actions.add(new Action(atype, index, dir1,dir2));
            }
            
            
            //player.printMap();
            player.strategyOne();
            
            //if no strategy present, use one of the available actions
            if (player.strategyMove.compareTo("X")==0) {
                System.out.println("TEST?");
                System.out.println("MOVE&BUILD "+player.actions.get(0).botId+" "+
                     player.actions.get(0).dirMove+" "+player.actions.get(0).dirBuild);
            } else {
                //TODO: make this work for multiple of my bots
                System.out.println("MOVE&BUILD 0 "+player.strategyMove+" "+player.strategyBuild);
            }
        }
    }
    
    
    //strategy 1
    void strategyOne() {
        strategyMove="X";
        Position nextPosition = new Position();
        //TODO: prevent building myself into a corner
        //TODO: take over full control of all next moves
        
        //if a 3 is accessible, use it
        if (me.height>=2) {
            nextPosition = getThreePos();
            //if a valid threePos found
            if (!(nextPosition.x==me.x && nextPosition.y==me.y)) {
                strategyMove = direction(me, nextPosition);
                Position buildAfterThree=lowestTarget(nextPosition);
                strategyBuild = direction(nextPosition, buildAfterThree);
            }
            //do not leave a 3 in range for an enemy, rather then build on it
            if (enemyInRange(me) && me.height==3) {
                strategyBuild = direction(nextPosition, me);
                //System.err.println("then build on origin (enemy in range of the three) "+direction(nextPosition, me));
            } 
        }
        
        //if I am moving off a 3 build on the next highest
        if (me.height==3) {
            nextPosition = highestTarget(me);
            strategyMove = direction(me, nextPosition);
            Position buildAfterMove = lowestTarget(nextPosition);
            strategyBuild = direction(nextPosition, buildAfterMove);
        }
        
        //pull the plug if going to run into a corner
        if (nextPosition.x!=-1) {
            if (lowestTarget(nextPosition).compareTo(me)==0 && highestTarget(nextPosition).compareTo(me)==0) {
                strategyMove="X";
                strategyBuild="X";
            }
        }
    
    }
    
    //get the position of a 3(score) accessible to me
    Position getThreePos() {
        for (int xd=-1;xd<2;xd++) {
            for (int yd=-1;yd<2;yd++) {
                try {
                    if (map[me.y+yd][me.x+xd].height==3 && !map[me.y+yd][me.x+xd].enemy) return map[me.y+yd][me.x+xd]; 
                } catch (Exception e) { }
            }
        }
        return map[me.y][me.x];
    }
    
    //get the position with the lowest height accessible to the position in question
    Position lowestTarget(Position q) {
        int test=4;
        Position testPos = q;
        for (int xd=-1;xd<2;xd++) {
            for (int yd=-1;yd<2;yd++) {
                try {
                    if (xd==0&&yd==0) continue; //skip the position in question
                    if (!map[q.y+yd][q.x+xd].enemy && map[q.y+yd][q.x+xd].height<test) {
                        test=map[q.y+yd][q.x+xd].height;
                        testPos=map[q.y+yd][q.x+xd];
                    }
                } catch (Exception e) { }
            }
        }
        return testPos;
    }
    
    //get the position with the highest height accessible to the position in question
    Position highestTarget(Position q) {
        int test=-1;
        Position testPos = q;
        for (int xd=-1;xd<2;xd++) {
            for (int yd=-1;yd<2;yd++) {
                try {
                    if (xd==0&&yd==0 || map[q.y+yd][q.x+xd].height>3) continue; //skip the position in question
                    if (!map[q.y+yd][q.x+xd].enemy && map[q.y+yd][q.x+xd].height>test) {
                        test=map[q.y+yd][q.x+xd].height;
                        testPos=map[q.y+yd][q.x+xd];
                    }
                } catch (Exception e) { }
            }
        }
        return testPos;
    }
    
    //get the direction for a given position from a position in question
    String direction(Position q, Position pos) {
        String dir = "";
        if (pos.y<q.y) dir="N";
        if (pos.y>q.y) dir="S";
        if (pos.x<q.x) dir+="W";
        if (pos.x>q.x) dir+="E";
        return dir;
    }
    
    //enemy within range of a position in question
    boolean enemyInRange(Position q) {
        for (int xd=-1;xd<2;xd++) {
            for (int yd=-1;yd<2;yd++) {
                try {
                    if (map[q.y+yd][q.x+xd].enemy && map[q.y+yd][q.x+xd].height>=q.height-1) {
                        return true;
                    }
                } catch (Exception e) { }
            }
        }
        return false;
    }
    
    //constructor
    Player(int s, int u) {
        size = s;
        unitsPerPlayer = u;
        map = new Position[size][size];
    }
    
    //populate the map
    void populateMap(Scanner in) {
        for (int y=0;y<size;y++) {
            int x=-1;
            String row = in.next();
            for (Character c : row.toCharArray()) {
                x++;
                if (c=='.') map[y][x]=new Position(x,y,5);
                else map[y][x]=new Position(x,y,((int)c)-48);
            }
        }
    }
    
    //test
    void printMap() {
        for (int y=0;y<size;y++) {
            for (int x=0;x<size;x++) {
                if (map[y][x].me) System.err.print(map[y][x].height+"M");
                else if (map[y][x].enemy) System.err.print(map[y][x].height+"E");
                else System.err.print(map[y][x].height+" ");
            }
            System.err.println("");
        }
    }
}

//model class Action
class Action {
    String type;
    int botId;
    String dirMove;
    String dirBuild;
    
    public Action(String t, int b, String dm, String db) {
        type=t;
        botId=b;
        dirMove=dm;
        dirBuild=db;
    }
}

//model class Position
class Position implements Comparable<Position> {
    int x,y;
    int height;
    boolean enemy;
    boolean me;
    boolean access;
    
    public Position() {
        x=-1; y=-1; height=-1;
    }
    
    public Position(int xp, int yp, int h) {
        x=xp;
        y=yp;
        height=h;
        access=(h<4); 
    }
    
    @Override
    public String toString() {
        return x+"/"+y;
    }

    @Override
    public int compareTo(Position o) {
        if (o.x==x && o.y==y) return 0;
        else return 1;
    }
}












/*
 OLD VERSION

import java.util.*;




class Player {



    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int unitsPerPlayer = in.nextInt();

        int[][] map = new int[size][size];

        int act = 0;
        String action = "";
        boolean northSouth=false, southNorth=false, eastWest=false, westEast=false;
        String prev = "X";
        String safetyNet = "X";
        // game loop
        while (true) {
            act++;
            northSouth=false; southNorth=false; eastWest=false; westEast=false;
            for (int i = 0; i < size; i++) {
                String row = in.next();
                System.err.println(row);
            }
            for (int i = 0; i < unitsPerPlayer; i++) {
                int unitX = in.nextInt();
                int unitY = in.nextInt();
            }
            
            for (int i = 0; i < unitsPerPlayer; i++) {
                int otherX = in.nextInt();
                int otherY = in.nextInt();
            }
            int legalActions = in.nextInt();
            for (int i = 0; i < legalActions; i++) {
                String atype = in.next();
                int index = in.nextInt();
                String dir1 = in.next();
                String dir2 = in.next();
                //System.err.println(atype+" "+index+" "+dir1+" "+dir2);
                
                if (dir1.compareTo("W")==0 && dir2.compareTo("E")==0) westEast=true;
                if (dir1.compareTo("E")==0 && dir2.compareTo("W")==0) eastWest=true;
                if (dir1.compareTo("N")==0 && dir2.compareTo("S")==0) northSouth=true;
                if (dir1.compareTo("S")==0 && dir2.compareTo("N")==0) southNorth=true;
                safetyNet = dir1+" "+dir2;
            }

            action="X";
            if (prev.compareTo("X")==0) {
                if (westEast) action="W E";
                else if (eastWest) action="E W";
                else if (northSouth) action="N S";
                else if (southNorth) action="S N";
            } else {
                if (westEast && prev.charAt(0)=='E') action="W E";
                if (eastWest && prev.charAt(0)=='W') action="E W";
                if (northSouth && prev.charAt(0)=='S') action="N S";
                if (southNorth && prev.charAt(0)=='N') action="S N";
            } 
            
            System.err.println("act "+action+" saf "+safetyNet);
            if (action.compareTo("X")==0) action=safetyNet;
            
            prev=action;
            System.out.println("MOVE&BUILD 0 "+action);
        }
    }
}
*/