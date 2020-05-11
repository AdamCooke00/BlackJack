import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deckCards = new ArrayList<Card>(52);
    private String[] cardPaths = { "2H.png", "3H.png", "4H.png", "5H.png", "6H.png", "7H.png", "8H.png", "9H.png",
            "10H.png", "JH.png", "QH.png", "KH.png", "AH.png", "2D.png", "3D.png", "4D.png", "5D.png", "6D.png",
            "7D.png", "8D.png", "9D.png", "10D.png", "JD.png", "QD.png", "KD.png", "AD.png", "2S.png", "3S.png",
            "4S.png", "5S.png", "6S.png", "7S.png", "8S.png", "9S.png", "10S.png", "JS.png", "QS.png", "KS.png",
            "AS.png", "2C.png", "3C.png", "4C.png", "5C.png", "6C.png", "7C.png", "8C.png", "9C.png", "10C.png",
            "JC.png", "QC.png", "KC.png", "AC.png" };

    Random rand = new Random();

    public Deck() {
        initializeDeck();
    }

    // remove random card in deck and return it
    public Card withdrawCard() {
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
                deckCards.add(new Card(i, suitArray[suitArrCounter], i + " of " + suitArray[suitArrCounter],
                        cardPaths[deckCards.size()]));
            }

            // Adds Ace and Face Cards to deck
            deckCards.add(new Card(10, suitArray[suitArrCounter], "Jack of " + suitArray[suitArrCounter],
                    cardPaths[deckCards.size()]));
            deckCards.add(new Card(10, suitArray[suitArrCounter], "Queen of " + suitArray[suitArrCounter],
                    cardPaths[deckCards.size()]));
            deckCards.add(new Card(10, suitArray[suitArrCounter], "King of " + suitArray[suitArrCounter],
                    cardPaths[deckCards.size()]));
            deckCards.add(new Card(1, suitArray[suitArrCounter], "Ace of " + suitArray[suitArrCounter],
                    cardPaths[deckCards.size()]));

            // increment suit counter to access different suit in suitArray
            suitArrCounter++;
        }

    }

    /**
     * @return the deckCards
     */
    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }
}