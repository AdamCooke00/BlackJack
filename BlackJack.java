import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BlackJack extends Application {

    public void playGame(Stage primaryStage, Player currentPlayer) {
        Group root = new Group();
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setLayoutX(1000);
        playAgainButton.setLayoutY(400);
        playAgainButton.setMinSize(80, 40);
        playAgainButton.setVisible(false);
        root.getChildren().add(playAgainButton);

        playAgainButton.setOnAction(e -> {
            root.getChildren().clear();
            restart(primaryStage, currentPlayer);
        });

        Deck gameDeck = new Deck();
        Game firstGame = new Game(currentPlayer, gameDeck, 5);
        firstGame.getInitialCards();

        // buttons
        Button exitBtn = new Button("Exit Game");
        Button hitButton = new Button("Hit");
        Button standButton = new Button("Stand");

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

        // welcome text & Player Coins Text
        Text welcomeText = new Text("Welcome to Blackjack! Created by Adam Cooke May2020");
        welcomeText.setFont(new Font(45));
        welcomeText.setFill(Color.WHITESMOKE);
        welcomeText.setX(25);
        welcomeText.setY(70);

        Text playerCoins = new Text(currentPlayer.getName() + " has " + currentPlayer.getCoins() + " coins.");
        playerCoins.setFont(new Font(20));
        playerCoins.setFill(Color.WHITESMOKE);
        playerCoins.setX(300);
        playerCoins.setY(780);

        int initialPlayerHandValue = firstGame.sumHandValue(firstGame.getPlayerCards());

        Text playerScoreText = new Text("Player Total: " + initialPlayerHandValue);
        playerScoreText.setFont(new Font(30));
        playerScoreText.setFill(Color.WHITESMOKE);
        playerScoreText.setX(25);
        playerScoreText.setY(580);

        Text dealerScoreText = new Text("Dealer Total: ???");
        dealerScoreText.setFont(new Font(30));
        dealerScoreText.setFill(Color.WHITESMOKE);
        dealerScoreText.setX(25);
        dealerScoreText.setY(250);

        // display players first 2 cards
        Image cardBack = null;
        Image playerCardOne = null;
        Image playerCardTwo = null;
        Image dealerCardFaceDown = null;
        Image dealerCardFaceUp = null;
        try {
            playerCardOne = new Image(
                    new FileInputStream("imgs/cardFronts/" + firstGame.getPlayerCards().get(0).getImagePath()));
            playerCardTwo = new Image(
                    new FileInputStream("imgs/cardFronts/" + firstGame.getPlayerCards().get(1).getImagePath()));

            dealerCardFaceDown = new Image(new FileInputStream("imgs/redCardBack.png"));

            dealerCardFaceUp = new Image(
                    new FileInputStream("imgs/cardFronts/" + firstGame.getDealerCards().get(1).getImagePath()));
            cardBack = new Image(new FileInputStream("imgs/redCardBack.png"));

        } catch (Exception e) {
            System.out.println("Unable to find image");
            e.printStackTrace();
        }

        // set deck image
        ImageView cardBackImageView = new ImageView(cardBack);
        cardBackImageView.setX(100);
        cardBackImageView.setY(350);
        cardBackImageView.setFitHeight(300);
        cardBackImageView.setFitWidth(100);
        cardBackImageView.setPreserveRatio(true);

        ImageView playerCardOneImage = new ImageView(playerCardOne);
        playerCardOneImage.setX(300);
        playerCardOneImage.setY(500);
        playerCardOneImage.setFitHeight(300);
        playerCardOneImage.setFitWidth(100);
        playerCardOneImage.setPreserveRatio(true);

        ImageView playerCardTwoImage = new ImageView(playerCardTwo);
        playerCardTwoImage.setX(400);
        playerCardTwoImage.setY(500);
        playerCardTwoImage.setFitHeight(300);
        playerCardTwoImage.setFitWidth(100);
        playerCardTwoImage.setPreserveRatio(true);

        ImageView dealerCardFaceDownImage = new ImageView(dealerCardFaceDown);
        dealerCardFaceDownImage.setX(300);
        dealerCardFaceDownImage.setY(205);
        dealerCardFaceDownImage.setFitHeight(450);
        dealerCardFaceDownImage.setFitWidth(100);
        dealerCardFaceDownImage.setPreserveRatio(true);

        ImageView dealerCardFaceUpImage = new ImageView(dealerCardFaceUp);
        dealerCardFaceUpImage.setX(400);
        dealerCardFaceUpImage.setY(200);
        dealerCardFaceUpImage.setFitHeight(300);
        dealerCardFaceUpImage.setFitWidth(100);
        dealerCardFaceUpImage.setPreserveRatio(true);

        root.getChildren().addAll(exitBtn, welcomeText, playerCoins, cardBackImageView, playerCardOneImage,
                playerCardTwoImage, hitButton, standButton, dealerCardFaceDownImage, dealerCardFaceUpImage,
                playerScoreText, dealerScoreText);

        // button logic
        exitBtn.setOnAction(e -> {
            System.exit(0);
        });

        standButton.setOnAction(e -> {
            // dealer hits until he reaches 17 or higher
            root.getChildren().remove(dealerCardFaceDownImage);
            try {
                Image flipDealerCard = new Image(
                        new FileInputStream("imgs/cardFronts/" + firstGame.getDealerCards().get(0).getImagePath()));
                ImageView flipDealerCardImage = new ImageView(flipDealerCard);
                flipDealerCardImage.setX(300);
                flipDealerCardImage.setY(200);
                flipDealerCardImage.setFitHeight(300);
                flipDealerCardImage.setFitWidth(100);
                flipDealerCardImage.setPreserveRatio(true);
                root.getChildren().add(flipDealerCardImage);
            } catch (FileNotFoundException exception) {
                System.out.print("Unable to find image at path\n" + firstGame.getDealerCards().get(0).getImagePath());
                exception.printStackTrace();
                System.out.println("\n");
            }

            while (firstGame.sumHandValue(firstGame.getDealerCards()) < 17) {
                firstGame.hit(firstGame.getDealerCards());
                Card newlyAddedDealerCard = firstGame.getDealerCards().get(firstGame.getDealerCards().size() - 1);
                try {
                    Image addDealerCard = new Image(
                            new FileInputStream("imgs/cardFronts/" + newlyAddedDealerCard.getImagePath()));
                    ImageView newCardImage = new ImageView(addDealerCard);
                    newCardImage.setX(500 + (100 * (firstGame.getDealerCards().size() - 3)));
                    newCardImage.setY(200);
                    newCardImage.setFitHeight(300);
                    newCardImage.setFitWidth(100);
                    newCardImage.setPreserveRatio(true);
                    root.getChildren().add(newCardImage);
                } catch (FileNotFoundException exception) {
                    System.out.print("Unable to find image at path\n" + newlyAddedDealerCard.getImagePath());
                    exception.printStackTrace();
                    System.out.println("\n");
                }

            }

            dealerScoreText.setText("Dealer Total: " + firstGame.sumHandValue(firstGame.getDealerCards()));
            playAgainButton.setVisible(true);
            hitButton.setVisible(false);
            standButton.setVisible(false);
            welcomeText.setText(firstGame.didWin(firstGame.getPlayerCards(), firstGame.getDealerCards()));

        });

        hitButton.setOnAction(e -> {
            firstGame.hit(firstGame.getPlayerCards());
            Card newlyAddedCard = firstGame.getPlayerCards().get(firstGame.getPlayerCards().size() - 1);
            playerScoreText.setText("Player Total: " + firstGame.sumHandValue(firstGame.getPlayerCards()));
            try {
                Image addPlayerCard = new Image(
                        new FileInputStream("imgs/cardFronts/" + newlyAddedCard.getImagePath()));
                ImageView newCardImage = new ImageView(addPlayerCard);
                newCardImage.setX(500 + (100 * (firstGame.getPlayerCards().size() - 3)));
                newCardImage.setY(500);
                newCardImage.setFitHeight(300);
                newCardImage.setFitWidth(100);
                newCardImage.setPreserveRatio(true);
                root.getChildren().add(newCardImage);
            } catch (FileNotFoundException exception) {
                System.out.print("Unable to find image at path\n" + newlyAddedCard.getImagePath());
                exception.printStackTrace();
                System.out.println("\n");
            }
            if (firstGame.sumHandValue(firstGame.getPlayerCards()) > 21) {
                welcomeText.setText("Player Busted");
                playAgainButton.setVisible(true);
                hitButton.setVisible(false);
                standButton.setVisible(false);
            } else if (firstGame.sumHandValue(firstGame.getPlayerCards()) == 21) {
                welcomeText.setText("User has BlackJack");
                standButton.fire();
            }

        });

        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.BROWN);
        primaryStage.setTitle("BlackJack Application by Adam Cooke");
        primaryStage.setScene(scene);
        primaryStage.show();
        if (initialPlayerHandValue == 21) {
            standButton.fire();
        }
    }

    public void restart(Stage primaryStage, Player currentPlayer) {
        playGame(primaryStage, currentPlayer);
    }

    public void start(Stage primaryStage) throws Exception {
        Player currentPlayer = new Player();
        currentPlayer.depositCoins(20);
        playGame(primaryStage, currentPlayer);
    }

    public static void main(String[] args) {
        launch(args);
    }

}