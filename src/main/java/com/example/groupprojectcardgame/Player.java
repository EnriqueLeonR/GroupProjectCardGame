package com.example.groupprojectcardgame;

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

    @Override
    public String toString() {
        return String.format("%s%s%n%s%s",
                "Player name: ", this.name,
                "Current health: ", this.health);
    }
}
