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
        for (int i = 0; i < 5; i++) {
            Button card = new Button("Top Card " + (i + 1));
            card.setPrefSize(80, 120); // Set card size
            topRow.getChildren().add(card);
        }

        // Add 5 cards to the bottom row
        for (int i = 0; i < 5; i++) {
            Button card = new Button("Bottom Card " + (i + 1));
            card.setPrefSize(80, 120); // Set card size
            bottomRow.getChildren().add(card);
        }
    }
}