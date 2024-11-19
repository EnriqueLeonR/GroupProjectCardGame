package com.example.groupprojectcardgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class GameScreenController {

    @FXML
    private HBox topRow;

    @FXML
    private HBox bottomRow;

    @FXML
    public void initialize() {
        // Add 5 cards to the top row
        for (int i = 1; i <= 5; i++) {
            Button card = new Button("Card " + i); // Representing a card with a button
            card.setPrefSize(80, 120); // Set size of the card
            topRow.getChildren().add(card);
        }

        // Add 5 cards to the bottom row
        for (int i = 1; i <= 5; i++) {
            Button card = new Button("Card " + i); // Representing a card with a button
            card.setPrefSize(80, 120); // Set size of the card
            bottomRow.getChildren().add(card);
        }
    }
}
