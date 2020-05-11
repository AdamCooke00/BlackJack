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

    public void printCards(ArrayList<Card> currentCards) {
        for (Card card : currentCards) {
            System.out.println(card);
        }
    }

    // if player wins this function is to be called and double the totalGameBet is
    // added to their total coins
    public void playerWon() {
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
        for (Card card : currentCards) {
            cardValueSum += card.getValue();
        }
        return cardValueSum;
    }

    // equates the totals of the players cards and the dealers cards and prints who
    // won
    public void didWin(ArrayList<Card> player, ArrayList<Card> dealer) {

        int playerCardValueSum = sumHandValue(player);
        int dealerCardValueSum = sumHandValue(dealer);

        if (playerCardValueSum > 21) {
            System.out.println("Player Busted");
            return;
        }

        if (dealerCardValueSum > 21) {
            System.out.println("Dealer Busted");
            playerWon();
        } else if (playerCardValueSum > dealerCardValueSum) {
            System.out.println("Player Won");
            playerWon();
        } else if (playerCardValueSum == dealerCardValueSum) {
            System.out.println("Tie! Dealer Takes It");
        } else {
            System.out.println("Dealer Won");
        }

    }
}