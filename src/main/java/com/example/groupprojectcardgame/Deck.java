package com.example.groupprojectcardgame;

import java.io.File;
import java.util.*;


public class Deck {
    private ArrayList<Card> cardList = new ArrayList<>();

    public Deck() { //ArrayList<Card> cardList
        String[] suitList = {"Heart", "Diamond", "Spade", "Club"};
        Map<String, Integer> faceList = Map.of(
                "Ace", 1, "Jack", 11, "Queen", 12, "King", 13);

        int[] rankList = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        File folder = new File("src/main/resources/com/example/groupprojectcardgame/images/Card Folder");
        String rootPath = "com/example/groupprojectcardgame/images/Card Folder/";

        // Iterates through the card png folder to match each png with the associated card.
        for (String fileName : folder.list()) {

            // Matches faceCard
            for (String f : faceList.keySet()) {
                if (fileName.startsWith(f)) {
                    for (String s : suitList) {
                        if (fileName.contains(f + s)) {
                            String cardLabel = f + s;
                            FaceCard card = new FaceCard(s, faceList.get(f), cardLabel, rootPath + fileName);
                            cardList.add(card);
                            break;
                        }
                    }
                }
            }

            // Matches valueCard
            for (String s : suitList) {
                for (int r : rankList) {
                    String rString = String.valueOf(r);
                    if (fileName.contains(rString + s)) {
                        String cardLabel = rString + s;
                        ValueCard card = new ValueCard(s, r, cardLabel, rootPath + fileName);
                        cardList.add(card);
                        break;
                    }
                }
            }
        }
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
