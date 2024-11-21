package com.example.groupprojectcardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private ArrayList<Card> cardList = new ArrayList<>();

    public Deck() { //ArrayList<Card> cardList


        ValueCard h2 = new ValueCard("hearts", 2, "h2",
                "com/example/groupprojectcardgame/images/Card Folder/2HeartCardDesigns.png");
        cardList.add(h2);
        ValueCard h3 = new ValueCard("hearts", 3, "h3",
                "com/example/groupprojectcardgame/images/Card Folder/3HeartCardDesigns.png");
        cardList.add(h3);
        ValueCard h4 = new ValueCard("hearts", 4, "h4",
                "com/example/groupprojectcardgame/images/Card Folder/4HeartCardDesigns.png");
        cardList.add(h4);
        ValueCard h5 = new ValueCard("hearts", 5, "h5",
                "com/example/groupprojectcardgame/images/Card Folder/5HeartCardDesigns.png");
        cardList.add(h5);
        ValueCard h6 = new ValueCard("hearts", 6, "h6",
                "com/example/groupprojectcardgame/images/Card Folder/6HeartCardDesigns.png");
        cardList.add(h6);
        ValueCard h7 = new ValueCard("hearts", 7, "h7",
                "com/example/groupprojectcardgame/images/Card Folder/7HeartCardDesigns.png");
        cardList.add(h7);
        ValueCard h8 = new ValueCard("hearts", 8, "h8",
                "com/example/groupprojectcardgame/images/Card Folder/8HeartCardDesigns.png");
        cardList.add(h8);
    }

    public void shuffle() {
        Collections.shuffle(cardList); //built-in method that shuffles arraylists
    }

    public void removeCard(Card card) {cardList.remove(card);}

    public void addCard(Card card) {cardList.add(card);}

    public Card draw() {return cardList.removeLast();}

    public int size() {return cardList.size();}

    //returns the card associated with a button id
    public Card getCard(String buttonId){
        for(Card card: cardList){
            if(buttonId.equals(card.getLabel())){
                return card;
            }
        }
        return null; //if no card is found
    }

    @Override
    public String toString() {
        return String.format("not sure");
    }

}
