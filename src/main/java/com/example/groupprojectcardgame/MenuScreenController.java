package com.example.groupprojectcardgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuScreenController {

    @FXML
    private void handleStartGame(ActionEvent event) {
        try {
            // Load the Game Screen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/groupprojectcardgame/GameScreen.fxml"));
            Parent gameScreenRoot = loader.load();

            // Get the current stage from the button event
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the Game Screen
            stage.setScene(new Scene(gameScreenRoot));
            stage.setTitle("Game Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHowToPlay(ActionEvent event) {
        try {
            // Load the How to Play Screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/groupprojectcardgame/HowToPlayScreen.fxml"));
            Parent howToPlayScreenRoot = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the How to Play Screen scene
            stage.setScene(new Scene(howToPlayScreenRoot));
            stage.setTitle("How to Play Rumik");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
