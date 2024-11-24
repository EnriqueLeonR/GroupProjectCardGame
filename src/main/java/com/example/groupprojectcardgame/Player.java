package com.example.groupprojectcardgame;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ProgressBar;

public class Player {
    private String name;
    private double health;


    Player(String name, double health){
        this.name = name;
        this.health = health;

    }

    public String getName() {return this.name;}

    public double getHealthPoints() {return this.health;}

    public void setName(String name) {this.name = name;}

    public void setHealth(double health) {this.health = health;}



    public DoubleProperty healthProperty() {
        return new SimpleDoubleProperty(health);
    }



    @Override
    public String toString() {
        return String.format("%s%s%n%s%s",
                "Player name: ", this.name,
                "Current health: ", this.health);
    }
}
