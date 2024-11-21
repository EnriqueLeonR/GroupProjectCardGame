package com.example.groupprojectcardgame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
        // Resizes the backgroud image to the size of the window
        imagePane.fitWidthProperty().bind(rootPane.widthProperty());
        imagePane.fitHeightProperty().bind(rootPane.heightProperty());

        // Adds the 5 cards to top and bottom rows
        Button[] buttons = new Button[5];
        addButtons(buttons, topRow);
        addButtons(buttons, bottomRow);
        addDeck(rightStackPane);

        dealCards(buttons); //give shuffled cards to player
        //dealCards(top); //uncomment when deck is complete
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


    public void addButtons(Button[] row, HBox location) {
        // Add 5 cards to the row
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
            row[i-1] = cardButton;

            cardButton.setOnAction(actionEvent -> {
                        selectCard(cardButton);

                        //the following is for testing. Pick 2 random cards to replace
                        if (selectedHand.size() == 2) {
                            testHand(row);
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


    //method to deal random cards (breaks if 7+ are dealt)
    public void dealCards(Button[] hand){
        for (Button button : hand) {
            if(button.getId().equals("null")) { //for every empty button, add Card vars to button
                Card card = deck.draw();
                button.setDisable(false);
                button.setId(card.getLabel());
                setImage(button, card);
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
