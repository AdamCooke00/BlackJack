import java.util.*;

public class Game {
    private Player currentPlayer;
    private Deck currentDeck;
    private double totalGameBet;
    private ArrayList<Card> playerCards = new ArrayList<Card>(2);
    private ArrayList<Card> dealerCards = new ArrayList<Card>(2);

    public Game(Player currentPlayer, Deck currentDeck, double initialBet) {
        this.currentPlayer = currentPlayer;
        this.currentDeck = currentDeck;
        this.totalGameBet = initialBet;
        // withdraw the initialBet from the players total coins
        this.currentPlayer.withdrawCoins(initialBet);
    }

    // double the players bet and withdraw the amount bet from the currentPlayer
    public void doubleDown() {
        currentPlayer.withdrawCoins(totalGameBet);
        totalGameBet *= 2;
    }

    /**
     * @return the playerCards
     */
    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    /**
     * @return the dealerCards
     */
    public ArrayList<Card> getDealerCards() {
        return dealerCards;
    }

    /**
     * @return the totalGameBet
     */
    public double getTotalGameBet() {
        return totalGameBet;
    }

    public void printCards(ArrayList<Card> currentCards) {
        for (Card card : currentCards) {
            System.out.println(card);
        }
    }

    // if player wins this function is to be called and double the totalGameBet is
    // added to their total coins
    public void playerWon() {
        System.out.println("In playerWon() ... " + totalGameBet * 2);
        currentPlayer.depositCoins(2 * totalGameBet);
    }

    // gives player and dealer 2 cards each
    public void getInitialCards() {
        playerCards.add(currentDeck.withdrawCard());
        playerCards.add(currentDeck.withdrawCard());
        dealerCards.add(currentDeck.withdrawCard());
        dealerCards.add(currentDeck.withdrawCard());
    }

    /**
     * @return the currentDeck
     */
    public Deck getCurrentDeck() {
        return currentDeck;
    }

    // gives calling card array 2 cards
    public void getInitialCards(ArrayList<Card> currentCards) {
        currentCards.add(currentDeck.withdrawCard());
        currentCards.add(currentDeck.withdrawCard());
    }

    // Add another card to card array
    public void hit(ArrayList<Card> currentCards) {
        currentCards.add(currentDeck.withdrawCard());
    }

    public int sumHandValue(ArrayList<Card> currentCards) {
        int cardValueSum = 0;
        // initially set to be a bust
        int cardValueSumWithAce = 22;
        boolean firstOccuranceOfAce = true;
        // iterates over each card in hand
        for (Card card : currentCards) {
            // if an ace is in the hand and its the first occorance of an ace give value to
            // aceSum and add 11
            if (card.getValue() == 1 && firstOccuranceOfAce) {
                cardValueSumWithAce = cardValueSum + 11;
                firstOccuranceOfAce = false;
            } else if (!firstOccuranceOfAce) {
                // this loop happen once an ace has been seen and occurs on each subsequent
                // iteration adding to the aceSum
                cardValueSumWithAce += card.getValue();
            }

            cardValueSum += card.getValue();
        }

        // check which sum is closer to 21 without going over, return that sum. if both
        // are over return lowest

        // if either are greater than 21, return the smallest one as the smallest one
        // will be less than 21 or closer to it
        if (cardValueSum > 21 || cardValueSumWithAce > 21) {
            return cardValueSum < cardValueSumWithAce ? cardValueSum : cardValueSumWithAce;
        } else if (cardValueSum == 21 || cardValueSumWithAce == 21) {
            return 21;
        } else {
            // both are less than 21 and neither equals 21 return the greatest value
            return cardValueSum > cardValueSumWithAce ? cardValueSum : cardValueSumWithAce;
        }
    }

    // equates the totals of the players cards and the dealers cards and prints who
    // won
    public String didWin(ArrayList<Card> player, ArrayList<Card> dealer) {

        int playerCardValueSum = sumHandValue(player);
        int dealerCardValueSum = sumHandValue(dealer);

        if (playerCardValueSum > 21) {
            return ("Player Busted");
        }

        if (dealerCardValueSum > 21) {
            playerWon();
            return ("Dealer Busted");
        } else if (playerCardValueSum > dealerCardValueSum) {
            playerWon();
            return ("Player Won");
        } else if (playerCardValueSum == dealerCardValueSum) {
            return ("Tie! Dealer Takes It");
        } else {
            return ("Dealer Won");
        }

    }
}