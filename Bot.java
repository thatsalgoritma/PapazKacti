import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {
    private String name;
    private List<Card> bDeck = new ArrayList<>();

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setBDeck(List<Card> deck) {

        this.bDeck = deck;
    }

    public List<Card> getBDeck() {

        return bDeck;
    }
    public void BotMove(List<Card> currentPlayer, List<Card> h2) {
        Random random = new Random();
        int index;
        if (currentPlayer.size() !=0) {
            if (h2.size() > 1) {
                index = random.nextInt(h2.size());
            } else {
                index = 0;
            }

            Card card;
            if (index == 0) {
                card = h2.get(index);
            } else {
                card = h2.get(index - 1);
            }

            h2.remove(card);
            currentPlayer.add(card);
        }
    }
    public void removeCardsWithSameNumber() {
        List<List<Card>> cardsByNumber = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            cardsByNumber.add(new ArrayList<>());
        }
        for (Card card : bDeck) {
            int numberIndex = getNumberIndex(card.getNumber());
            cardsByNumber.get(numberIndex).add(card);
        }
        for (List<Card> cards : cardsByNumber) {
            while (cards.size() >= 2) {
                cards.remove(0);
                cards.remove(0);
            }
        }
        bDeck.clear();
        for (List<Card> cards : cardsByNumber) {
            bDeck.addAll(cards);
        }
    }
    private int getNumberIndex(String number) {
        return switch (number) {
            case "2" -> 0;
            case "3" -> 1;
            case "4" -> 2;
            case "5" -> 3;
            case "6" -> 4;
            case "7" -> 5;
            case "8" -> 6;
            case "9" -> 7;
            case "10" -> 8;
            case "J" -> 9;
            case "Q" -> 10;
            case "K" -> 11;
            case "A" -> 12;
            default -> -1;
        };
    }





}
