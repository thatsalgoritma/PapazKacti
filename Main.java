import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
        int playerCount=0;
        List<String> Names = new ArrayList<>();
        String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String number : numbers) {
            for (String suit : suits) {
                Card card = new Card();
                card.setNumber(number);
                card.setSuit(suit);
                deck.add(card);
            }
        }
        deck.remove(44);
        deck.remove(44);
        deck.remove(44);
        System.out.println("Welcome to the PapazKacti Game!");
        Collections.shuffle(deck);
        Scanner sc=new Scanner(System.in);
        int choice = 0;
        boolean verbose=false;
        boolean validChoice = false;

        while (!validChoice) {
            try {
                System.out.println("Choose verbose mode: press 1 for verbose, press 0 for normal mode");
                choice = sc.nextInt();

                if (choice != 0 && choice != 1) {
                    throw new IllegalArgumentException("Invalid choice. Please enter either 0 or 1.");
                }

                validChoice = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }

        if(choice==1){verbose=true;}

        boolean isValidInput = false;
        System.out.println("Enter the player number  it should be between 2-4");
        while (!isValidInput) {
            try {
                playerCount = sc.nextInt();

                if (playerCount < 2 || playerCount > 4) {
                    throw new IllegalArgumentException("Invalid player count. Please enter a number between 2 and 4.");
                }
                isValidInput = true;

            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Please enter a valid player number:");
            }
        }

        Game game=new Game(playerCount);
        game.Play(deck,playerCount,verbose);

    }

}