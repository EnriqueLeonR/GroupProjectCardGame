package com.example.groupprojectcardgame;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ProgressBar;

public class Player {
    private String name;
    private double health;
    private ProgressBar healthBar;

    Player(String name, double health){
        this.name = name;
        this.health = health;
        this.healthBar = new ProgressBar();
        this.healthBar.setProgress(1.0);// Initialize progress to 1.0 (full health)
        this.healthBar.setPrefWidth(400); // Set the preferred width to 200
    }

    public String getName() {return this.name;}

    public double getHealthPoints() {return this.health;}

    public void setName(String name) {this.name = name;}

    public void setHealth(double health) {this.health = health;}

    public ProgressBar getHealthBar() {return this.healthBar;}

    public DoubleProperty healthProperty() {
        return new SimpleDoubleProperty(health);
    }

    public void updateHealthBar() {
        healthBar.setProgress(getHealthPoints() / 100.0);
    }

    @Override
    public String toString() {
        return String.format("%s%s%n%s%s",
                "Player name: ", this.name,
                "Current health: ", this.health);
    }
}
