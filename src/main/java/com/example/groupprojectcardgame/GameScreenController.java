package com.example.groupprojectcardgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GameScreenController {

    @FXML
    private HBox topRow;

    @FXML
    private HBox bottomRow;

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView imagePane;

    @FXML
    public void initialize() {
        // Resizes the backgroud image to the size of the window
        imagePane.fitWidthProperty().bind(rootPane.widthProperty());
        imagePane.fitHeightProperty().bind(rootPane.heightProperty());

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
