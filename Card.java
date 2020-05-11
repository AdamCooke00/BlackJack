public class Card {
    private final int value;
    private final String suit;
    private final String name;
    private final String imagePath;

    public Card(int value, String suit, String name, String imagePath) {
        this.value = value;
        this.suit = suit;
        this.name = name;
        this.imagePath = imagePath;
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
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
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