import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Driver {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.fillDeck();

        deck.shuffle();
        Game game = new Game();
        game.InitializePlayers(deck);
        game.turn();
    }
}
