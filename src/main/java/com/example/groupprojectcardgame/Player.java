package com.example.groupprojectcardgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class Player {
    private String name;
    private double health;
    private ProgressBar healthBar;
    private Text playerHealthText;

    Player(String name, double health){
        this.name = name;
        this.health = health;
        this.healthBar = new ProgressBar();
        this.healthBar.setProgress(1.0);// Initialize progress to 1.0 (full health)
        this.healthBar.setPrefWidth(400);// Set the preferred width to 200
        this.playerHealthText = new Text();
        this.playerHealthText.setText(String.valueOf(health));
    }

    public static void updateHealthBar(Player player, double damage) {
        double newHealth = player.getHealthPoints() - damage;
        player.setHealth(newHealth);
        player.updateHealthBar();
    }

    public static void updateHealthText(Player player) {
        player.updateHealthText();
    }

    public String getName() {return this.name;}

    public double getHealthPoints() {return this.health;}

    public void setName(String name) {this.name = name;}

    public void setHealth(double health) {this.health = health;}

    public ProgressBar getHealthBar() {return this.healthBar;}

    public Text getPlayerHealthText() {return this.playerHealthText;}

    public void updateHealthBar() {
        healthBar.setProgress(getHealthPoints() / 100.0);
    }

    public void updateHealthText() {
        playerHealthText.setText(String.valueOf(health));
    }

    @Override
    public String toString() {
        return String.format("%s%s%n%s%s",
                "Player name: ", this.name,
                "Current health: ", this.health);
    }
}
