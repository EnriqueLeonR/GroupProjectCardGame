package com.example.groupprojectcardgame;

import javafx.scene.text.Text;

public class Player {
    private String name;
    private double health;
    private Text playerHealthText;

    Player(String name, double health){
        this.name = name;
        this.health = health;
    }

    public String getName() {return this.name;}

    public double getHealthPoints() {return this.health;}

    public void setName(String name) {this.name = name;}

    public void updateHealthText() {
        playerHealthText.setText(String.valueOf(health));
    }

    public Text getPlayerHealthText() {return this.playerHealthText;}

    public void setHealth(double health) {this.health = health;}

    @Override
    public String toString() {
        return String.format("%s%s%n%s%s",
                "Player name: ", this.name,
                "Current health: ", this.health);
    }
}
