package com.example.groupprojectcardgame;

public class Player {
    private String name;
    private double healthPoints;

    Player(String name, double healthPoints){}

    public String getName() {return this.name;}

    public double getHealthPoints() {return this.healthPoints;}

    public void setName(String name) {this.name = name;}

    public void setHealthPoints(double health) {this.healthPoints = health;}

    @Override
    public String toString() {
        return String.format("%s%s%n%s%s",
                "Player name: ", this.name,
                "Current health: ", this.healthPoints);
    }
}
