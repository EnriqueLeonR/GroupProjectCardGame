package com.example.groupprojectcardgame;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
    private StackPane rightStackPane;

    private Deck fullDeck = new Deck(); //reference full card deck
    private Deck deck = new Deck(); //deck used for game
    private ArrayList<Card> selectedHand = new ArrayList<>(); //selected hand to keep track of cards

    //init players
    private Player user = new Player("User", 1000);
    private Computer comp = new Computer("CPU", 1000);


    @FXML
    public void initialize() {
        // Resizes the backgroud image to the size of the window ie- allows fullscreen
        imagePane.fitWidthProperty().bind(rootPane.widthProperty());
        imagePane.fitHeightProperty().bind(rootPane.heightProperty());

        // Initializes the 5 card buttons to the top and bottom rows
        addButtons(topRow);
        addButtons(bottomRow);
        addDeck(rightStackPane);

        dealCards(topRow); //give shuffled cards to player
        dealCards(bottomRow);
        //dealCards(top); //uncomment when deck is complete

        // Create separate containers for health bars
        StackPane computerHealthBarContainer = new StackPane();
        StackPane playerHealthBarContainer = new StackPane();

        Rectangle computerHealthBar = comp.getHealthBar();
        Rectangle playerHealthBar = user.getHealthBar();

        // Add health bars to containers
        computerHealthBarContainer.getChildren().add(computerHealthBar);
        playerHealthBarContainer.getChildren().add(playerHealthBar);

        computerHealthBar.widthProperty().bind(rootPane.widthProperty().multiply(0.2));
        playerHealthBar.widthProperty().bind(rootPane.widthProperty().multiply(0.2));

        // Set mouseTransparent property to true
        computerHealthBarContainer.setMouseTransparent(true);
        playerHealthBarContainer.setMouseTransparent(true);

        // Add containers to game screen
        rootPane.getChildren().add(computerHealthBarContainer);
        rootPane.getChildren().add(playerHealthBarContainer);

        // Position health bar containers
        computerHealthBarContainer.setTranslateX(10);
        computerHealthBarContainer.setTranslateY(topRow.getBoundsInParent().getMinY() - 120); // Move up by 80 pixels
        playerHealthBarContainer.setTranslateX(10);
        playerHealthBarContainer.setTranslateY(bottomRow.getBoundsInParent().getMaxY() + 120); // Move down by 80 pixels
    }


    public void addDeck(StackPane location) {
        deck.shuffle();
        for (int i = 0; i <= deck.size(); i++) {
            Button cardButton = new Button("Card "); // Representing a card with a button
            cardButton.setPrefSize(80, 120);
            cardButton.setId("null"); //card id pairs with card label
            cardButton.setText(""); //remove text for only image
            cardButton.setDisable(true); //disable user control

            //attach img to button
            Image img = new Image("com/example/groupprojectcardgame/images/Card Folder/1CardBackDesignCardDesigns.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(cardButton.getPrefHeight());
            view.setFitWidth(cardButton.getPrefWidth());
            cardButton.setGraphic(view);

            location.getChildren().add(cardButton);
        }
    }


    public void addButtons(HBox location) {
        // Add 5 cards to the row
        Button[] buttons = new Button[5];
        for (int i = 1; i <= 5; i++) {
            Button cardButton = new Button("Card " + i); // Representing a card with a button
            cardButton.setPrefSize(80, 120);
            cardButton.setId("null"); //card id pairs with card label
            cardButton.setText(""); //remove text for only image
            cardButton.setDisable(true); //disable user control

            //attach img to button
            Image img = new Image("com/example/groupprojectcardgame/images/Card Folder/1CardBackDesignCardDesigns.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(cardButton.getPrefHeight());
            view.setFitWidth(cardButton.getPrefWidth());
            cardButton.setGraphic(view);

            //add button to button array
            buttons[i-1] = cardButton;

            cardButton.setOnAction(actionEvent -> {
                        selectCard(cardButton);

                        //the following is for testing. Pick 2 random cards to replace
                        if (selectedHand.size() == 2) {
                            testHand(buttons);
                        }
                    });

            //add button
            location.getChildren().add(cardButton);
        }
    }


    //method to take a Card's src and display on button
    public void setImage(Button button, Card card){
        Image img = new Image(card.getSrc());
        ImageView view = new ImageView(img);
        view.setFitHeight(button.getPrefHeight());
        view.setFitWidth(button.getPrefWidth());
        button.setGraphic(view);
    }


    //method to deals cards in the testHand method, may try to fix testhand to use the same method
    // (but it's working and don't wanna touch it lol)
    public void dealCards(Button[] row){
        for (Button button : row) {
            if(button.getId().equals("null")) { //for every empty button, add Card vars to button
                Card card = deck.draw();
                button.setDisable(false);
                button.setId(card.getLabel());
                setImage(button, card);
            }
        }
    }


    // Method deals cards to each specific hand/row ie- either the top or bottom row
    public void dealCards(HBox location) {
        ObservableList<Node> buttons = location.getChildren();
        for (Node button : buttons) {
            if(button.getId().equals("null")) { //for every empty button, add Card vars to button
                Card card = deck.draw();
                System.out.print(deck.size());
                button.setDisable(false);
                button.setId(card.getLabel());
                setImage((Button) button, card);
            }
        }
    }


    //method to find the Card associated with the button. Allows user to select/deselect cards
    public void selectCard(Button button){
        Card card = fullDeck.getCard(button.getId());
        //System.out.println(card.getSrc());
        if(selectedHand.contains(card)){
            selectedHand.remove(card);
        } else{
            selectedHand.add(card);
        }
        System.out.println(selectedHand); //for testing
    }


    //method that test selected cards
    public void testHand(Button[] hand){
        //use cards in selected hand to do actions here

        //if a hand is valid
        for(Button button:hand){ //find cards in players hand and remove them
            Card card = fullDeck.getCard(button.getId());
            if(selectedHand.contains(card)){
                button.setId("null");
                button.setDisable(true);
                Card blank = new Card("none", 0, "na",
                        "com/example/groupprojectcardgame/images/Card Folder/1CardBackDesignCardDesigns.png");
                setImage(button, blank);
            }
        }

        selectedHand.clear(); //clear selected cards
        dealCards(hand); //deal new cards to player
    }


}
