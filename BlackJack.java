import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.*;

public class BlackJack extends Application {
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Deck gameDeck = new Deck();
        Game firstGame = new Game(new Player(), gameDeck, 5);
        firstGame.getInitialCards();

        // buttons
        Button exitBtn = new Button("Exit Game");
        Button hitButton = new Button("Hit");
        Button standButton = new Button("Stand");

        // button logic
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });

        standButton.setOnAction(e -> {
            // dealer hits until he reaches 17 or higher
            while (firstGame.sumHandValue(firstGame.getDealerCards()) < 17) {
                firstGame.hit(firstGame.getDealerCards());
            }

            firstGame.didWin(firstGame.getPlayerCards(), firstGame.getDealerCards());

        });

        hitButton.setOnAction(e -> {
            firstGame.hit(firstGame.getPlayerCards());
            Card newlyAddedCard = firstGame.getPlayerCards().get(firstGame.getPlayerCards().size() - 1);
            System.out.print(newlyAddedCard.getImagePath());
            Image addPlayerCard = new Image(new FileInputStream("imgs/cardFronts/" + newlyAddedCard.getImagePath()));
            ImageView newCardImage = new ImageView(addPlayerCard);
            newCardImage.setX(700 + (100 * (firstGame.getPlayerCards().size() - 3)));
            newCardImage.setY(500);
            newCardImage.setFitHeight(300);
            newCardImage.setFitWidth(100);
            newCardImage.setPreserveRatio(true);
            root.getChildren().add(newCardImage);

        });

        // button locations & Size
        hitButton.setLayoutX(500);
        hitButton.setLayoutY(700);
        hitButton.setMinSize(80, 40);

        standButton.setLayoutX(600);
        standButton.setLayoutY(700);
        standButton.setMinSize(80, 40);

        exitBtn.setLayoutX(20);
        exitBtn.setLayoutY(750);
        exitBtn.setMinSize(150, 40);

        // -----

        // welcome text
        Text welcomeText = new Text("Welcome to Blackjack! Created by Adam Cooke May2020");
        welcomeText.setFont(new Font(45));
        welcomeText.setFill(Color.WHITESMOKE);
        welcomeText.setX(25);
        welcomeText.setY(70);

        // set deck image
        Image cardBack = new Image(new FileInputStream("imgs/redCardBack.png"));
        ImageView cardBackImageView = new ImageView(cardBack);
        cardBackImageView.setX(100);
        cardBackImageView.setY(300);
        cardBackImageView.setFitHeight(300);
        cardBackImageView.setFitWidth(100);
        cardBackImageView.setPreserveRatio(true);

        // display players first 2 cards
        Image playerCardOne = new Image(
                new FileInputStream("imgs/cardFronts/" + firstGame.getPlayerCards().get(0).getImagePath()));
        Image playerCardTwo = new Image(
                new FileInputStream("imgs/cardFronts/" + firstGame.getPlayerCards().get(1).getImagePath()));

        ImageView playerCardOneImage = new ImageView(playerCardOne);
        playerCardOneImage.setX(500);
        playerCardOneImage.setY(500);
        playerCardOneImage.setFitHeight(300);
        playerCardOneImage.setFitWidth(100);
        playerCardOneImage.setPreserveRatio(true);

        ImageView playerCardTwoImage = new ImageView(playerCardTwo);
        playerCardTwoImage.setX(600);
        playerCardTwoImage.setY(500);
        playerCardTwoImage.setFitHeight(300);
        playerCardTwoImage.setFitWidth(100);
        playerCardTwoImage.setPreserveRatio(true);

        root.getChildren().addAll(exitBtn, welcomeText, cardBackImageView, playerCardOneImage, playerCardTwoImage,
                hitButton, standButton);
        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.BROWN);
        primaryStage.setTitle("BlackJack Application by Adam Cooke");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}