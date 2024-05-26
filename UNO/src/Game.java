import java.util.Scanner;
import java.util.Stack;

public class Game {

    Players[] players;
    CircularQ playersCircularQ;
    Stack<Cards> cardsStack = new Stack<>();

    public void InitializePlayers(Deck deck){


        Scanner user = new Scanner(System.in);
        Scanner names = new Scanner(System.in);

        System.out.println("Enter the number of players(min 2):");
        int input = user.nextInt();
        playersCircularQ = new CircularQ();

        players = new Players[input];


        for (int i = 0; i < input; i++) {
            System.out.println("Enter the name of the person: ");
            String name = names.nextLine();
            players[i] = new Players(i,name, deck);
        }


        for (int i=0; i<input;i++)
        {
            Players player = players[i];
            deck.serveCards(player);
        }

        for(int i=0; i<players.length;i++)
        {
            playersCircularQ.enQueue(players[i]);

        }
        playersCircularQ.displayQueue();

        cardsStack.push(deck.drawCard());

    }



    public void turn(){

        Players player;
        Cards card;
        Cards playerCard;
        Players winner = new Players();
        int size = playersCircularQ.size();

            CircularQ.Node temp = playersCircularQ.rear;
            while (!(size - playersCircularQ.size() == 1))
            {
                temp =  playersCircularQ.next(temp);
                player = temp.data;
                System.out.println(player.name);

                card = cardsStack.peek();
                System.out.println("\nLast Thrown Card: " + card.toString() + "\n");

                player.ShowCards();
                playerCard = player.input();
                if (playerCard == null) continue;
                while (!legalCard(card, playerCard)) {
                   playerCard = player.input();
                }
                cardsStack.push(playerCard);
                switch (playerCard.type) {
                    case SKIP:
                        temp = playersCircularQ.next(temp);
                        break;
                    case DRAW_TWO:
                        temp = playersCircularQ.next(temp);
                        temp.data.drawCard();
                        temp.data.drawCard();
                        temp = playersCircularQ.previous(temp);
                        break;
                    case DRAW_FOUR:
                        temp = playersCircularQ.next(temp);
                        temp.data.drawCard();
                        temp.data.drawCard();
                        temp.data.drawCard();
                        temp.data.drawCard();
                        temp = playersCircularQ.previous(temp);
                        break;
                    case REVERSE:
                        playersCircularQ.reverseQ();
                        //issue with the double reverse consec
                        break;
                }
                player.removeCard(playerCard);

                if (player.cards.isEmpty()) {
                    winner = playersCircularQ.deleteNode(temp).data;
                    break;
                }
           }
        System.out.println("Winner: " + winner.name);
    }

    public boolean legalCard(Cards match, Cards playerCard){
        if ((match.color.equals(playerCard.color) && (playerCard.type == CardType.BASIC || playerCard.type == CardType.DRAW_TWO)) || (match.number == playerCard.number && playerCard.type == CardType.BASIC) || playerCard.type == CardType.WILD || playerCard.type == CardType.DRAW_FOUR || playerCard.type == CardType.DRAW_TWO || ((match.type == CardType.SKIP && playerCard.type == CardType.SKIP) || match.color.equals(playerCard.color)) || ((match.type == CardType.REVERSE && playerCard.type == CardType.REVERSE) || match.color.equals(playerCard.color))) {
            return true;
        }
        return false;
    }
}
