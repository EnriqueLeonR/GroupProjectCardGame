package com.example.groupprojectcardgame;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.scene.control.ProgressBar;
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
    private BorderPane borderPane;

    @FXML
    private StackPane rightStackPane;

    @FXML
    private Button submit;

    private Deck fullDeck = new Deck(); //reference full card deck
    private Deck deck = new Deck(); //deck used for game
    private ArrayList<Card> selectedHand = new ArrayList<>(); //selected hand to keep track of cards

    //init players
    private Player user = new Player("User", 100);
    private Computer comp = new Computer("CPU", 100);

    private enum Status{
        START,
        END,
        P1,
        P2
    }

    private Status gameStatus = Status.START;

    @FXML
    public void initialize() {
        // Resizes the backgroud image to the size of the window ie- allows fullscreen
        imagePane.fitWidthProperty().bind(rootPane.widthProperty());
        imagePane.fitHeightProperty().bind(rootPane.heightProperty());

        // Initializes the 5 card buttons to the top and bottom rows
        Button[] topButtons = addButtons(topRow);
        Button[] bottomButtons = addButtons(bottomRow);
        addDeck(rightStackPane);


        //dealCards(top); //uncomment when deck is complete

        createHealthBars();

        //setup submit hand button
        submit.setVisible(false);
        submit.setOnAction(actionEvent -> {
            testHand(bottomRow);
        });

        dealCards(topRow, true); //second parameter hides the deck
        dealCards(bottomRow, false);

        pickStarter();
        //gameStatus = Status.P1; //test code

        //while(gameStatus != Status.END){
          if(gameStatus == Status.P1){ //run for user turn
              turn(user);
          } else if(gameStatus == Status.P2){ //run for cpu 1 turn
              turn(comp);
          }
        //}
    }

    private void createHealthBars() {
        // Create separate containers for health bars
        StackPane computerHealthBarContainer = new StackPane();
        StackPane playerHealthBarContainer = new StackPane();

        // Add health bars to containers
        computerHealthBarContainer.getChildren().add(comp.getHealthBar());
        playerHealthBarContainer.getChildren().add(user.getHealthBar());

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


    public Button[] addButtons(HBox location) {
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
                    });

            //add button
            location.getChildren().add(cardButton);
        }
        return buttons;
    }


    //method to take a Card's src and display on button
    public void setImage(Button button, Card card){
        Image img = new Image(card.getSrc());
        ImageView view = new ImageView(img);
        view.setFitHeight(button.getPrefHeight());
        view.setFitWidth(button.getPrefWidth());
        button.setGraphic(view);
    }


    public void dealAnimation(Card card, StackPane startLocation, Button endLocation) {
        Platform.runLater(() -> {
            System.out.println("StackPane Size: " + rootPane.getWidth() + " x " + rootPane.getHeight());
            Path path = new Path();
            Button placeholder = new Button();

            ObservableList<Node> StackPaneChild = startLocation.getChildren();
            Node button = StackPaneChild.getFirst();
            double startX = button.localToScene(0, 0).getX();
            double startY = button.localToScene(0, 0).getY();

            // Get the end position in scene coordinates
            double endX = endLocation.localToScene(0, 0).getX();
            double endY = endLocation.localToScene(0, 0).getY();

            // Define the animation path
            path.getElements().add(new MoveTo(startX, startY)); // Starting point
            path.getElements().add(new LineTo(endX, endY));
            placeholder.setPrefSize(80, 120);
            setImage(placeholder, card);
            placeholder.setStyle("-fx-background-color: transparent;"); // Match the look

            // THIS NEEDS TO BE BORDERPANE AND NOTHING ELSE, ROOTPANE WAS MY WHOLE ISSUE
            borderPane.getChildren().add(placeholder);
            PathTransition transition = new PathTransition(Duration.seconds(2), path, placeholder);
            transition.setInterpolator(Interpolator.LINEAR);
            System.out.println("Animation Start: " + startX + ", " + startY);
            System.out.println("Animation End: " + endX + ", " + endY);
            transition.play();
        });
    }


    // Method deals cards to each specific hand/row ie- either the top or bottom row
    public void dealCards(HBox location, boolean disable) {
        ObservableList<Node> buttons = location.getChildren();
        for (Node button : buttons) {
            if(button.getId().equals("null")) { //for every empty button, add Card vars to button
                Card card = deck.draw();
                System.out.print(deck.size()); //testing
                button.setId(card.getLabel());
                if(!disable){
                    button.setDisable(false);
                    setImage((Button) button, card);
                }
                dealAnimation(card, rightStackPane, (Button) button);
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

        if(!selectedHand.isEmpty()){
            submit.setVisible(true);
        } else{
            submit.setVisible(false);
        }
        System.out.println(selectedHand); //for testing
    }


    //method that test selected cards
    public void testHand(HBox location){
        //use cards in selected hand to do actions here

        //if a hand is valid
        ObservableList<Node> hand = location.getChildren();
        for(Node button:hand){ //find cards in players hand and remove them
            Card card = fullDeck.getCard(button.getId());
            if(selectedHand.contains(card)){
                button.setId("null");
                button.setDisable(true);
                Card blank = new Card("none", 0, "na",
                        "com/example/groupprojectcardgame/images/Card Folder/1CardBackDesignCardDesigns.png");
                setImage((Button) button, blank);
            }
        }

        submit.setVisible(false);
        selectedHand.clear(); //clear selected cards
        dealCards(location, false); //deal new cards to player

        if(gameStatus == Status.P1) {
            turn(comp);
        }
    }

    public void pickStarter(){
        int random = (int)(Math.random()*2);
        //System.out.println(random);
        switch (random){
            case 0:
                gameStatus = Status.P1;
                System.out.println(user.getName()+" starts!");
                break;
            case 1:
                gameStatus = Status.P2;
                System.out.println(comp.getName()+ " starts!");
                break;
            default:
                break;
        }
    }

    public void turn(Player player){
        if (player.getName().equals("User")) {
            gameStatus = Status.P1;
            dealCards(bottomRow, false);
        } else {
            if (comp.getHealthPoints() > 0) {
                gameStatus = Status.P2;
                dealCards(bottomRow, true);
                dealCards(topRow, true);
                System.out.println("comp round"); // for testing

                // convert comp buttons to cards
                ObservableList<Node> hand = topRow.getChildren();
                ArrayList<Card> cards = new ArrayList<>();
                for (int index = 0; index < 5; index++) {
                    cards.add(fullDeck.getCard(hand.get(index).getId())); // <<
                }
                System.out.println(cards); // returns comp cards for testing

                // pass cards and return hand dmg
                Action action = new Action();
                double dmg = action.calculateDamage(cards);
                user.setHealth(user.getHealthPoints() - dmg);
                System.out.println(user.getHealthPoints());

                // update health bar for user
                updateHealthBar(user, dmg);

                // remove cards from comp hand
                System.out.println(cards);
                ObservableList<Node> top = topRow.getChildren();
                for (Node button : top) { // find cards in players hand and remove them
                    Card card = fullDeck.getCard(button.getId());
                    if (cards.contains(card)) {
                        button.setId("null");
                        button.setDisable(true);
                        Card blank = new Card("none", 0, "na",
                                "com/example/groupprojectcardgame/images/Card Folder/1CardBackDesignCardDesigns.png");
                        setImage((Button) button, blank);
                    }
                }
                dealCards(topRow, true); // for testing

                if (user.getHealthPoints() > 0) {
                    turn(user);

                } else {
                    announceWinner(comp);
                }
            } else {
                announceWinner(user);
            }
        }
    }

    public void updateHealthBar(Player player, double damage) {
        double newHealth = player.getHealthPoints() - damage;
        player.setHealth(newHealth);
        player.updateHealthBar();
    }

    public void announceWinner(Player player){
        //change endscreen
        gameStatus = Status.END;
        System.out.println(player.getName()+ " is the winner!");
    }


}
