package com.example.groupprojectcardgame;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Hand extends Deck {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Map<String, Object> evaluateHand() {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        Map<String, Integer> suitCounts = new HashMap<>();

        for (Card card : cards) {
            int value = card.getRank();
            String suit = card.getSuit();

            valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
            suitCounts.put(suit, suitCounts.getOrDefault(suit, 0) + 1);
        }

        Map<String, Object> evaluation = new HashMap<>();
        evaluation.put("valueCounts", valueCounts);
        evaluation.put("suitCounts", suitCounts);

        return evaluation;
    }
}

