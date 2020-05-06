public class Card {
    private final int value;
    private final String suit;
    private final String name;

    public Card(int value, String suit, String name) {
        this.value = value;
        this.suit = suit;
        this.name = name;
    }

    public String toString() {
        return getName();
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the suit
     */
    public String getSuit() {
        return suit;
    }
}