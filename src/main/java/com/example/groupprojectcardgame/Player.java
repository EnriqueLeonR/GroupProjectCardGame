package com.example.groupprojectcardgame;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;

public class Player {
    private String name;
    private double health;
    private Rectangle healthBar;

    Player(String name, double health){
        this.name = name;
        this.health = health;
        this.healthBar = createHealthBar();

    }

    public String getName() {return this.name;}

    public double getHealthPoints() {return this.health;}

    public void setName(String name) {this.name = name;}

    public void setHealth(double health) {this.health = health;}

    public Rectangle getHealthBar() {return this.healthBar;}

    private Rectangle createHealthBar() {
        Rectangle healthBar = new Rectangle(0, 0, 100, 20);
        healthBar.setFill(Color.DARKRED);

        // Bind health bar width to player's health
        healthBar.widthProperty().bind(healthProperty().multiply(100.0 / 1000.0));

        return healthBar;
    }

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
