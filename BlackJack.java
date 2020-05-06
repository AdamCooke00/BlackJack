public class BlackJack {
    public static void main(String[] args) {
        Game firstGame = new Game(new Player(), new Deck(), 5);
        firstGame.getInitialCards();
        System.out.println("Player Cards");
        firstGame.printCards(firstGame.getPlayerCards());
        System.out.println("Dealer Cards");
        firstGame.printCards(firstGame.getDealerCards());
        firstGame.didWin(firstGame.getPlayerCards(), firstGame.getDealerCards());

    }

}