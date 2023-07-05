import java.util.*;

public class Player {
    private String name;
    private List<Card> pDeck = new ArrayList<>();

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setPDeck(List<Card> deck) {

        this.pDeck = deck;
    }

    public List<Card> getPDeck() {

        return pDeck;
    }

    public Player(){

    }

    public void removeCardsWithSameNumber() {
        List<List<Card>> cardsByNumber = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            cardsByNumber.add(new ArrayList<>());
        }
        for (Card card : pDeck) {
            int numberIndex = getNumberIndex(card.getNumber());
            cardsByNumber.get(numberIndex).add(card);
        }
        for (List<Card> cards : cardsByNumber) {
            while (cards.size() >= 2) {
                cards.remove(0);
                cards.remove(0);
            }
        }
        pDeck.clear();
        for (List<Card> cards : cardsByNumber) {
            pDeck.addAll(cards);
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

    public void PlayerMove(List<Card> currentPlayer,List<Card> h2){
        System.out.println("Choose a card from the next player it has "+h2.size()+" cards");
        Scanner sc=new Scanner(System.in);
        int chosen=0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                chosen = sc.nextInt();

                if (chosen <= 0 || chosen > h2.size()) {
                    throw new IllegalArgumentException("Invalid player count. Please enter a number between 2 and 4.");
                }
                isValidInput = true;

            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter a valid number:");
            }
        }
        Card card=h2.get(chosen-1);

        h2.remove(card);
        currentPlayer.add(card);

    }

}
