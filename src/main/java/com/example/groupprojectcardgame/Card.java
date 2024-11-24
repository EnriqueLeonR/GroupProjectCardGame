package com.example.groupprojectcardgame;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private String suit;
    private int rank;
    private String label;
    private String src;

    public Card(String suit, int rank, String label, String src) {
        this.suit = suit;
        this.rank = rank;
        this.label = label;
        this.src = src;
    }


    //method to take a Card's src and display on button
    public static void setImage(Button button, Card card){
        Image img = new Image(card.getSrc());
        ImageView view = new ImageView(img);
        view.setFitHeight(button.getPrefHeight());
        view.setFitWidth(button.getPrefWidth());
        button.setGraphic(view);
    }


    public String getSuit() {return this.suit;}

    public int getRank() {return this.rank;}

    public String getLabel() {return this.label;}

    public String getSrc(){
        return this.src;
    }

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
        return String.format("%s%s%n%s%s%n%s%s%n",
                "Suit: ", this.suit,
                "Rank: ", this.rank,
                "label: ", this.label);
    }
}
