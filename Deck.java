import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deckCards = new ArrayList<Card>(52);

    public Deck() {
        initializeDeck();
    }

    // remove random card in deck and return it
    public Card withdrawCard() {
        Random rand = new Random();
        return deckCards.remove(rand.nextInt(deckCards.size()));
    }

    // adds 52 cards to array to for standard playing deck
    public void initializeDeck() {
        String[] suitArray = { "Hearts", "Diamonds", "Spades", "Clubs" };
        int suitArrCounter = 0;

        // loops 4 times, once per suit
        while (deckCards.size() != 52) {

            // Adds cards two-ten into deck
            for (int i = 2; i <= 10; i++) {
                deckCards.add(new Card(i, suitArray[suitArrCounter], i + " of " + suitArray[suitArrCounter]));
            }

            // Adds Ace and Face Cards to deck
            deckCards.add(new Card(1, suitArray[suitArrCounter], "Ace of " + suitArray[suitArrCounter]));
            deckCards.add(new Card(10, suitArray[suitArrCounter], "Jack of " + suitArray[suitArrCounter]));
            deckCards.add(new Card(10, suitArray[suitArrCounter], "Queen of " + suitArray[suitArrCounter]));
            deckCards.add(new Card(10, suitArray[suitArrCounter], "King of " + suitArray[suitArrCounter]));

            // increment suit counter to access different suit in suitArray
            suitArrCounter++;
        }

    }
}