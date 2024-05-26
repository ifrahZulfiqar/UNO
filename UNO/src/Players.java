import java.util.LinkedList;
import java.util.Scanner;

public class Players {

    int id;
    String name;
    LinkedList<Cards> cards = new LinkedList<>();
    Deck deck;
    public Players(int id, String name, Deck deck) {
        this.id = id;
        this.name = name;
        this.deck = deck;
    }
    public Players() {}
    public void ShowCards(){
        for(int i=-0;i<cards.size();i++) {
            System.out.println(i + " -> " + cards.get(i).number + " " +cards.get(i).color + " " + cards.get(i).type );
        }
        System.out.println("-1 -> Draw Card");
    }


    public Cards input(){
        Scanner input = new Scanner(System.in);
        System.out.println("Player: "+ this.name +" Choose your card by index: ");
        int inp = input.nextInt();

        if (inp == -1) {
            cards.add(deck.drawCard());
            return null;
        }

//        System.out.println("You chose: " + cards.get(inp));   //remove the card from the list
        return cards.get(inp);
    }

    public void drawCard() {
        cards.add(deck.drawCard());
    }

    public void removeCard(Cards card) {
        cards.remove(card);
    }
}
