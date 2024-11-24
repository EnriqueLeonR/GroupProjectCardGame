package com.example.groupprojectcardgame;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Action {
    public double calculateDamage(ArrayList<Card> cards) {
        Map<Integer, Integer> valueCounts = new HashMap<>();
        Map<String, Integer> suitCounts = new HashMap<>();

        for (Card card : cards) {
            int value = card.getRank();
            String suit = card.getSuit();

            valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
            suitCounts.put(suit, suitCounts.getOrDefault(suit, 0) + 1);
        }

        double damage = 0;

        // Check for pairs, trios, and runs
        for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (count == 2) {
                damage += getPairDamage(value, cards);
            } else if (count == 3) {
                damage += getTrioDamage(value, cards);
            } else if (count == 4) {
                damage += getFourOfAKindDamage(value, cards);
            }
        }

        // Check for runs
        damage += getRunDamage(valueCounts, cards);

        return damage;
    }

    private double getPairDamage(int value, ArrayList<Card> cards) {
        double damage = 0;
        int pairCount = 0;
        String suit = "";

        for (Card card : cards) {
            if (card.getRank() == value) {
                pairCount++;
                if (suit.isEmpty()) {
                    suit = card.getSuit();
                } else if (!suit.equals(card.getSuit())) {
                    suit = "mixed";
                }
            }
        }

        if (pairCount == 2) {
            if (suit.equals("mixed")) {
                damage = value * 2 * 1.5;
            } else {
                damage = value * 2 * 1.8;
            }
        }

        return damage;
    }

    private double getTrioDamage(int value, ArrayList<Card> cards) {
        double damage = 0;
        int trioCount = 0;
        String suit = "";

        for (Card card : cards) {
            if (card.getRank() == value) {
                trioCount++;
                if (suit.isEmpty()) {
                    suit = card.getSuit();
                } else if (!suit.equals(card.getSuit())) {
                    suit = "mixed";
                }
            }
        }

        if (trioCount == 3) {
            if (suit.equals("mixed")) {
                damage = value * 3 * 1.8;
            } else {
                damage = value * 3 * 2.0;
            }
        }

        return damage;
    }

    private double getFourOfAKindDamage(int value, ArrayList<Card> cards) {
        double damage = 0;
        int fourOfAKindCount = 0;

        for (Card card : cards) {
            if (card.getRank() == value) {
                fourOfAKindCount++;
            }
        }

        if (fourOfAKindCount == 4) {
            damage = value * 4 * 2.5;
        }

        return damage;
    }

    private double getRunDamage(Map<Integer, Integer> valueCounts, ArrayList<Card> cards) {
        double damage = 0;
        int runCount = 0;
        int runValue = 0;
        int previousValue = 0;

        for (Map.Entry<Integer, Integer> entry : valueCounts.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (count >= 1 && value == previousValue + 1) {
                runCount++;
                runValue += value;
            } else {
                if (runCount >= 3) {
                    if (runCount == 3) {
                        damage += runValue * 1.5;
                    } else if (runCount == 4) {
                        damage += runValue * 1.8;
                    } else if (runCount == 5) {
                        damage += runValue * 2.0;
                    }
                }
                runCount = 0;
                runValue = value;
            }
            previousValue = value;
        }

        if (runCount >= 3) {
            if (runCount == 3) {
                damage += runValue * 1.5;
            } else if (runCount == 4) {
                damage += runValue * 1.8;
            } else if (runCount == 5) {
                damage += runValue * 2.0;
            }
        }

        return damage;
    }
}
