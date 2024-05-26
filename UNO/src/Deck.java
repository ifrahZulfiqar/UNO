import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Deck {
    Stack<Cards> deck = new Stack<>();

    public void fillDeck() {
        for (CardColors cc : CardColors.values()) {
            for (int i = 0; i < 10; i++) {
                deck.push(new Cards(i, cc, CardType.BASIC));
            }
            
            for (CardType ct : CardType.values()) {
                if(ct != CardType.BASIC)
                {
                for (int i = 0; i < 2; i++) {
                    deck.push(new Cards(i,cc, ct));
                }

                }
            }
        }

    }

    public Cards drawCard() {
            return deck.pop();
    }

    public void shuffle() {

        Collections.shuffle(deck);
    }

    public LinkedList<Cards> serveCards(Players players) {


             Players player = players;

            for(int j=0;j<2;j++)
            {
            player.cards.add(deck.pop());

            }
            System.out.println(player.id + "  " + player.name);


            System.out.println("Player cards: ");

            for (int k=0;k<player.cards.size();k++)
            {
                System.out.println(player.cards.get(k).number + " " + player.cards.get(k).color + " " + player.cards.get(k).type);
            }


        return new LinkedList<>();
    }
}
