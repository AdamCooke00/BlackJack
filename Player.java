public class Player {
    private String name;
    private double coins;

    public Player() {
        this.name = "Guest Player";
        this.coins = 0;
    }

    public Player(String name, double coins) {
        this.name = name;
        this.coins = coins;
    }

    // add coins to players total
    public void depositCoins(double deposit) {
        this.coins += deposit;
    }

    // subtract coins from players total
    public void withdrawCoins(double withdrawAmount) {
        this.coins -= withdrawAmount;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the coins
     */
    public double getCoins() {
        return coins;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}