package com.example.groupprojectcardgame;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cardList = new ArrayList<>();

    public Deck(ArrayList<Card> cardList) {}

    public void shuffle() {}

    public void removeCard(Card card) {cardList.remove(card);}

    public void addCard(Card card) {cardList.add(card);}

    public Card draw() {return cardList.removeLast();}

    public int size() {return cardList.size();}

    @Override
    public String toString() {
        return String.format("not sure");
    }

}
