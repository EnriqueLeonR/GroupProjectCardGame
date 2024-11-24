package com.example.groupprojectcardgame;

import java.util.ArrayList;

public class Computer extends Player {



    public Computer(String name, int health) {
        super(name, health);
    }

    //will fix this later...
    public int pickRandom(ArrayList<Card> cards) {
        int dmg;

        dmg = test(cards, 5);
        if (dmg > 0){
            return dmg;
        }

        for(int amount = 4; amount >0; amount--){
            cards.remove((int) ((Math.random()*(amount+1))));
            //System.out.println(cards);
            dmg = test(cards, amount);
            if(dmg > 0){
                return dmg;
            }
        }
        return 0;
    }

    public int test(ArrayList<Card> cards, int amount) {
        int dmg;
        switch (amount) {
            case 5:
                if(!true){ //test hands
                    dmg = 5;
                } else{
                    dmg = 0;
                }
                break;
            case 4:
                if(!true){
                    dmg = 4;
                } else{
                    dmg = 0;
                }
                break;
            case 3:
                if(!true){
                    dmg = 3;
                } else{
                    dmg = 0;
                }
                break;
            case 2:
                if(!true){
                    dmg = 2;
                } else{
                    dmg = 0;
                }
                break;
            case 1:
                dmg = cards.getFirst().getRank();
                break;
            default:
                dmg = 0;
                break;
        }
        return dmg;
    }
}
