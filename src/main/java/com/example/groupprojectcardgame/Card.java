package com.example.groupprojectcardgame;

public class Card {
    private String suit;
    private int rank;
    private String label;

    public Card(String suit, int rank, String label) {
        this.suit = suit;
        this.rank = rank;
        this.label = label;
    }

    public String getSuit() {return this.suit;}

    public int getRank() {return this.rank;}

    public String getLabel() {return this.label;}

    public void setSuit(String suit){
        this.suit = suit;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setLabel(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%s%s%n%s%s%n%s%s",
                "Suit: ", this.suit,
                "Rank: ", this.rank,
                "label: ", this.label);
    }
}
