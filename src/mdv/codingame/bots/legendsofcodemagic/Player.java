/*
 * my bot for
 * Legends of Code and Magic
 * https://www.codingame.com/leaderboards/challenge/legends-of-code-and-magic-marathon/global
 * challenge complete [position 1419]
 */
package mdv.codingame.bots.legendsofcodemagic;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class Player {

    // from the engine
    static int playerHealth = 0;
    static int playerDeck = 0;
    static int playerMana = 0;
    static int playerRune = 0;
    static int opponentHand = 0;
    static int cardCount = 0;
    static String command = " ";
    static int iterations = 0;
    
    //picking
    static int biggies = 0;
    static int mid = 0;
    
    static List<Card> handCards     = new CopyOnWriteArrayList<>();
    static List<Card> summonedCards = new CopyOnWriteArrayList<>();
    static List<Card> opponentCards = new CopyOnWriteArrayList<>();
    
    // -----------------------------------//
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int turns = 0;
        // game loop
        while (true) {
            //init per turn
            handCards     = new CopyOnWriteArrayList<>();
            summonedCards = new CopyOnWriteArrayList<>();
            opponentCards = new CopyOnWriteArrayList<>();
            command = " ";
            iterations = 0;
            turns++;
            
            for (int i = 0; i < 2; i++) {
                playerHealth = in.nextInt();
                playerMana   = in.nextInt();
                playerDeck   = in.nextInt();
                playerRune   = in.nextInt();
            }
            
            opponentHand = in.nextInt();
            cardCount = in.nextInt();
            
            //System.err.println("<cardCount>" + cardCount + " <mana>" + playerMana);
            
            for (int i = 0; i < cardCount; i++) {
                Card card = new Card(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(),
                                     in.nextInt(), in.nextInt(), in.nextInt(), in.next(),
                                     in.nextInt(), in.nextInt(), in.nextInt());
                
                if (turns > 30) {  //in battle
                    if (card.location == 0)  handCards.add(card);
                    if (card.location == 1)  summonedCards.add(card);
                    if (card.location == -1) opponentCards.add(card);
                }  else {  // pick cards
                    handCards.add(card);
                }
                
            }
            
            // still drafting
            //System.err.println(" player deck "+playerDeck);
            if (turns < 31) {
                System.out.println(pickCard());
            } else {  // play - core logic
                //System.err.println("START " + playerMana + " l " + getLowestCost(handCards));
                while (playerMana >= getLowestCost(handCards)) {
                    //System.err.println("IN " + playerMana);
                    if (summonedCards.size() > 0) {
                        command = command + itemActions().trim();
                        command = command + summonCards().trim();      
                    }  else {
                        command = command + summonCards().trim();  
                        command = command + itemActions().trim();
                    }
                    
                    iterations++;
                    if (iterations > 8) break;
                }
                
                if (summonedCards.size() > 0) command = command + attack();
                if (command.trim().length() == 0) {
                    System.out.println("PASS");
                } else {
                    command = command + attack();
                    command = command.trim();
                    if (command.endsWith(";")) command = command.substring(0, command.length()-1);
                    //System.err.println("*"+command+"*");
                    System.out.println(command);
                }
            }
            
        }
    }
    
    
    // decide which card(s) to summon - only summon creatures (0)
    // check if a guard is available , check if I have a guard - if not summon if possible
    static String summonCards() {
        String summonAction = " ";
        
        //summon a guard if one is available and I dont have one summoned
        for (Card c : handCards) {
            if (playerMana >= c.cost && c.cardType == 0 && c.abilities.contains("G") && getGuard(summonedCards).instanceId == -1) {
                summonAction = summonAction + " SUMMON " + c.instanceId + ";";
                playerMana = playerMana - c.cost;
                //add chargers to be available now
                if (!c.abilities.contains("C")) c.isBusy = true;
                summonedCards.add(c);
                handCards.remove(c);
                
                return summonAction;
            }
        }
        for (Card c : handCards) {
            if (playerMana >= c.cost && c.cardType == 0) {
                summonAction = summonAction + " SUMMON " + c.instanceId + ";";
                playerMana = playerMana - c.cost;
                //add chargers to be available now
                if (!c.abilities.contains("C")) c.isBusy = true;
                summonedCards.add(c);
                handCards.remove(c);
                return summonAction;
            }
        }
        return summonAction;
    }
    
    // decide which attacks to invoke
    // troublemakers first, then guards, then opponent 
    static String attack() {
        String attackAction = " ";
        Collections.sort(summonedCards);
        for (Card c : summonedCards) {
            
            //now if there is a guard, attack it
            Card oGuard = getGuard(opponentCards);
            if (!c.isBusy && oGuard.instanceId != -1 && oGuard.defense > 0 && c.defense > 0) {
                attackAction = attackAction + " ATTACK " + c.instanceId + " " + oGuard.instanceId + " en garde!; ";
                oGuard.defense = oGuard.defense - c.attack;
                c.defense = c.defense - oGuard.attack;
                c.isBusy = true;
            }
            
            //look for trouble makers second
            for (Card o : opponentCards) {
                if (o.attack > 4 && o.defense > 0 && c.defense > 0 && !c.isBusy) {
                    attackAction = attackAction + " ATTACK " + c.instanceId + " " + o.instanceId + " en garde!; ";
                    o.defense = o.defense - c.attack;
                    c.defense = c.defense - o.attack;
                    c.isBusy = true;
                }
            }
            
            //now if there is an opponent attack him
            if (!c.isBusy && c.defense > 0) { 
                attackAction = attackAction + " ATTACK " + c.instanceId + " -1 smack!;";
            }
        }
        return attackAction;
    }
    
    // decide which items to use
    static String itemActions() {
        String itemAction = " ";
        Card opponentGuard;
        int attack;
        int attackId;
        int defense;
        int defenseId;
        for (Card c : handCards) {
            //System.err.println("find BUG items " + c.defense + " mana:" + playerMana);
            if (playerMana >= c.cost) {
                attack = 0; attackId = -1;
                defense = 0; defenseId = -1;
                
                
                // seek out the opponent's guard
                opponentGuard = getGuard(opponentCards);
                
                if (c.cardType == 1) {  //green - target my creatures - pick the best one to improve
                    for (Card s : summonedCards) {
                        if (s.attack > attack) { attackId = s.instanceId; attack = c.attack; }
                        if (s.defense > defense) { defenseId = s.instanceId; defense = c.defense; }
                    }
                    if (c.attack > c.defense && defenseId != -1 && !c.isBusy) {
                        itemAction = itemAction + " USE " + c.instanceId + " " + defenseId + "; ";
                        playerMana = playerMana - c.cost;
                        c.isBusy = true;
                        handCards.remove(c);
                    } else if (attackId != -1 && !c.isBusy) {
                        itemAction = itemAction + " USE " + c.instanceId + " " + attackId + "; ";
                        playerMana = playerMana - c.cost;
                        c.isBusy = true;
                        handCards.remove(c);
                    }
                    
                
                } else if (c.cardType == 2) {  //red - target enemy creatures - guards first if they are there
                    if (opponentGuard.instanceId != -1 && !c.isBusy) {
                        itemAction = itemAction + " USE " + c.instanceId + " " + opponentGuard.instanceId + "; ";
                        playerMana = playerMana - c.cost;
                        handCards.remove(c);
                        opponentGuard.defense = opponentGuard.defense - c.attack;
                        c.isBusy = true;
                    }  else {
                        for (Card o : opponentCards) {
                            if (o.defense > 0 && !c.isBusy) {
                                itemAction = itemAction + " USE " + c.instanceId + " " + o.instanceId + "; ";
                                playerMana = playerMana - c.cost;
                                handCards.remove(c);
                                o.defense = o.defense - c.attack;
                                c.isBusy = true;
                                break;
                            }
                        }
                    }
                        
                } else if (c.cardType == 3) {  //blue - target me, enemy and in future enemy creatures
                    //if a blue card has negative defense it can also be used against enemy creatures
                    //System.err.println("find BUG blue " + c.defense);
                    if (c.defense < 0 && opponentGuard.instanceId != -1 && !c.isBusy) {
                        itemAction = itemAction + " USE " + c.instanceId + " " + opponentGuard.instanceId + "; ";
                        playerMana = playerMana - c.cost;
                        handCards.remove(c);
                        c.isBusy = true;
                    }  else if (!c.isBusy) {
                        itemAction = itemAction + " USE " + c.instanceId + " -1; ";
                        playerMana = playerMana - c.cost;
                        handCards.remove(c);
                        c.isBusy = true;
                    }
                    
                }
            }    
        }
        return itemAction;
    }
    
    //decide which card to pick
    static String pickCard() {
        float weighting = -100;
        int pick = 0;
        int id = 0;
        int thisWeighting = 0;
        for (Card c : handCards) {
            System.err.println("biggies "+biggies);
            if (c.weighting > 6) c.weighting -= biggies;
            if ((c.weighting) > weighting) {
                pick = id;
                weighting = c.weighting;
            } 
            id++;
        }
        System.err.println("pick "+pick);
        if (weighting > 6) biggies += 2;
        return "PICK " + pick;
    }
    
    //is there a guard
    static Card getGuard(List<Card> cards) {
        for (Card c : cards) {
            if (c.abilities.contains("G") && c.defense > 0) return c;
        }
        return new Card();
    }
    
    //lowest card to still play
    static int getLowestCost(List<Card> cards) {
        int cost = 100;
        for (Card c : cards) {
            if (c.cost < cost && !c.isBusy) cost = c.cost;
        }
        return cost;
    }
    
}


//
// classes
//

class Card implements Comparable<Card> {
    public int cardNumber;
    public int instanceId;
    public int location;
    public int cardType;
    public int cost;
    public int attack;
    public int defense;
    public String abilities;
    public int myHealthChange;
    public int opponentHealthChange;
    public int cardDraw;
    public boolean isBusy = false;
    public float weighting;

    public Card(int cardNumber, int instanceId, int location,
                int cardType, int cost, int attack, int defense, 
                String abilities, int myHealthChange, int opponentHealthChange,
                int cardDraw) {
        this.cardNumber = cardNumber;
        this.instanceId = instanceId;
        this.location = location;
        this.cardType = cardType;
        this.cost = cost;
        this.attack = attack;
        this.defense = defense;
        this.abilities = abilities;
        this.myHealthChange = myHealthChange;
        this.opponentHealthChange = opponentHealthChange;
        this.cardDraw = cardDraw;
        this.setWeighting();
    }

    Card() {
        this.instanceId = -1;
    }
    
    private void setWeighting() {
        if (cardType == 0) {
            
            weighting = (float) attack*2+defense*1-cost*1;
            //if (cost == 2) weighting = weighting + 5;
            //if (cost == 3) weighting = weighting + 6;
            
            if (abilities.contains("G")) weighting = (float) (weighting + 2);
            if (abilities.contains("D")) weighting = (float) (weighting + 1);
            if (abilities.contains("L")) weighting = (float) (weighting + 1);
            if (abilities.contains("C")) weighting = (float) (weighting + 2);
            
        } else {
        
            //1,2,3 green,red,blue
            if (cardType == 1) weighting = (float) (attack*1 + defense*1 - cost);
            if (cardType == 2) weighting = (float) (attack*-1 + defense*-1 - cost);
            if (cardType == 3) weighting = (float) (1);
        }
        //add for health change
        weighting += myHealthChange;
        weighting += opponentHealthChange*-1;
        //System.err.println("w(#"+cardNumber+") :"+weighting);
    }

    @Override
    public int compareTo(Card o) {
        if (this.attack > o.attack) return 1;
        else if (this.attack == o.attack) return 0;
        else return -1;
    }
    
}
