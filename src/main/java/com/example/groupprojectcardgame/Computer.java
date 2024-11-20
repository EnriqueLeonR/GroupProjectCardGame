package com.example.groupprojectcardgame;

public class Computer extends Player{

    public Computer(String name, int health){
        super(name, health);
    }

    public Card pickRandom(Card[] cards){
        return cards[(int)(Math.random()*cards.length)];
    }
}
