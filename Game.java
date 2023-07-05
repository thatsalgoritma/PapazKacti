import java.util.*;
public class Game {
    private final Player player;
    private final List<Bot> bots;
    public Game(int playerCount) {
        player = new Player();
        bots = new ArrayList<>();
        System.out.println("Enter player's name:");
        Scanner sc = new Scanner(System.in);
        String pName = sc.nextLine();
        player.setName(pName);

        System.out.println("Enter bots name/names");
        for (int i = 1; i < playerCount; i++) {
            String bName = sc.nextLine();
            Bot bot = new Bot();
            bot.setName(bName);
            bots.add(bot);
        }


    }
    public void Play(List<Card> deck,int playerCount,boolean verbose) {

        switch (playerCount) {
            case 2:
                List<Card> playerDeck1 = player.getPDeck();
                List<Card> bot1Deck = bots.get(0).getBDeck();
                for (int i = 0; i < deck.size() / 2; i++) {
                    playerDeck1.add(deck.get(i));
                    Collections.shuffle(playerDeck1);
                }
                for (int i = deck.size() / 2; i < deck.size(); i++) {
                    bot1Deck.add(deck.get(i));
                    Collections.shuffle(bot1Deck);
                }
                player.removeCardsWithSameNumber();
                bots.get(0).removeCardsWithSameNumber();
                while (player.getPDeck().size() > 0 && bots.get(0).getBDeck().size() > 0) {
                    if (bots.get(0).getBDeck().size() >= 1) {
                        player.PlayerMove(player.getPDeck(), bots.get(0).getBDeck());
                        player.removeCardsWithSameNumber();
                    }
                    if (playerDeck1.size() >= 1) {
                        bots.get(0).BotMove(bots.get(0).getBDeck(), player.getPDeck());
                        bots.get(0).removeCardsWithSameNumber();
                    }
                    if (verbose) {
                        printDecks();
                    }
                    if (playerDeck1.size() == 0 || bots.get(0).getBDeck().size() == 0) {
                        if(playerDeck1.size() == 0){System.out.println(player.getName() + " Won!");}else System.out.println(player.getName() + " Lost!");
                        if(bots.get(0).getBDeck().size() == 0){System.out.println(bots.get(0).getName() + " Won!");}else System.out.println(bots.get(0).getName() + " Lost!");
                    }


                }
                break;

            case 3:
                playerDeck1 = player.getPDeck();
                List<Card> bot2Deck = bots.get(0).getBDeck();
                List<Card> bot3Deck = bots.get(1).getBDeck();
                for (int i = 0; i < deck.size() / 3; i++) {
                    playerDeck1.add(deck.get(i));
                    Collections.shuffle(playerDeck1);
                }

                for (int i = deck.size() / 3; i < 2 * (deck.size() / 3); i++) {
                    bot2Deck.add(deck.get(i));
                    Collections.shuffle(bot2Deck);
                }

                for (int i = 2 * (deck.size() / 3); i < deck.size(); i++) {
                    bot3Deck.add(deck.get(i));
                    Collections.shuffle(bot3Deck);
                }
                player.removeCardsWithSameNumber();
                bots.get(0).removeCardsWithSameNumber();
                bots.get(1).removeCardsWithSameNumber();
                System.out.println();
                System.out.println("You have " + player.getPDeck().size() + "cards left");
                System.out.println();
                int counter = 0;
                if (playerDeck1.size() == 0) {
                    counter++;
                }
                if (bots.get(0).getBDeck().size() == 0) {
                    counter++;
                }
                if (bots.get(1).getBDeck().size() == 0) {
                    counter++;
                }

                while (counter < 2) {

                    if (playerDeck1.size() != 0) {
                        if (bots.get(0).getBDeck().size() >= 1 && bots.get(1).getBDeck().size() >= 1) {
                            player.PlayerMove(player.getPDeck(), bots.get(0).getBDeck());
                            player.removeCardsWithSameNumber();
                        } else if (bots.get(0).getBDeck().size() == 0) {
                            player.PlayerMove(player.getPDeck(), bots.get(1).getBDeck());
                            player.removeCardsWithSameNumber();
                        }
                        player.setPDeck(playerDeck1);
                    }

                    if (bots.get(1).getBDeck().size() >= 1 && playerDeck1.size() >= 1) {
                        bots.get(0).BotMove(bots.get(0).getBDeck(), bots.get(1).getBDeck());
                        bots.get(0).removeCardsWithSameNumber();
                    } else if (bots.get(1).getBDeck().size() == 0) {
                        bots.get(0).BotMove(bots.get(0).getBDeck(), playerDeck1);
                        bots.get(0).removeCardsWithSameNumber();
                        player.setPDeck(playerDeck1);
                    }
                    if (playerDeck1.size() >= 1 && bots.get(0).getBDeck().size() >= 1) {//|| =or && =and
                        bots.get(1).BotMove(bots.get(1).getBDeck(), player.getPDeck());
                        bots.get(1).removeCardsWithSameNumber();
                    } else if (playerDeck1.size() == 0) {
                        bots.get(1).BotMove(bots.get(1).getBDeck(), bots.get(0).getBDeck());
                        bots.get(1).removeCardsWithSameNumber();
                    }
                    player.setPDeck(playerDeck1);
                    if (verbose) {
                        printDecks();
                    }
                    int c = 0;
                    if (playerDeck1.size() == 0) {
                        c++;
                    }
                    if (bots.get(0).getBDeck().size() == 0) {
                        c++;
                    }
                    if (bots.get(1).getBDeck().size() == 0) {
                        c++;
                    }
                    if (c >= 2) {
                        if (playerDeck1.size() == 0) {
                            System.out.println("You Won!");
                        } else {
                            System.out.println("You Lost!");
                        }
                        if (bots.get(0).getBDeck().size() == 0) {
                            System.out.println(bots.get(0).getName() + " Won!");
                        } else {
                            System.out.println(bots.get(0).getName() + " Lost!");
                        }
                        if (bots.get(1).getBDeck().size() == 0) {
                            System.out.println(bots.get(1).getName() + " Won!");
                        } else {
                            System.out.println(bots.get(1).getName() + " Lost!");
                        }
                        break;
                    }
                }
                break;
            case 4:
                playerDeck1 = player.getPDeck();
                List<Card> bot4Deck = bots.get(0).getBDeck();
                List<Card> bot5Deck = bots.get(1).getBDeck();
                List<Card> bot6Deck = bots.get(2).getBDeck();
                for (int i = 0; i < deck.size() / 4; i++) {
                    playerDeck1.add(deck.get(i));
                    Collections.shuffle(playerDeck1);
                }

                for (int i = deck.size() / 4; i < 2 * (deck.size() / 4); i++) {
                    bot4Deck.add(deck.get(i));
                    Collections.shuffle(bot4Deck);
                }

                for (int i = 2 * (deck.size() / 4); i < 3 * (deck.size() / 4); i++) {
                    bot5Deck.add(deck.get(i));
                    Collections.shuffle(bot5Deck);
                }

                for (int i = 3 * (deck.size() / 4); i < deck.size(); i++) {
                    bot6Deck.add(deck.get(i));
                    Collections.shuffle(bot6Deck);
                }
                player.removeCardsWithSameNumber();
                bots.get(0).removeCardsWithSameNumber();
                bots.get(1).removeCardsWithSameNumber();
                bots.get(2).removeCardsWithSameNumber();
                System.out.println();
                System.out.println("You have " + player.getPDeck().size() + "cards left");
                System.out.println();
                int counter2 = 0;
                if (playerDeck1.size() == 0) {
                    counter2++;
                }
                if (bots.get(0).getBDeck().size() == 0) {
                    counter2++;
                }
                if (bots.get(1).getBDeck().size() == 0) {
                    counter2++;
                }
                if (bots.get(2).getBDeck().size() == 0) {
                    counter2++;
                }

                while (counter2<3) {
                    if (playerDeck1.size() != 0) {
                        if (bots.get(0).getBDeck().size() >= 1 ) {
                            player.PlayerMove(player.getPDeck(), bots.get(0).getBDeck());
                            player.removeCardsWithSameNumber();
                        } else if (bots.get(0).getBDeck().size() == 0 && bots.get(1).getBDeck().size() != 0) {
                            player.PlayerMove(player.getPDeck(), bots.get(1).getBDeck());
                            player.removeCardsWithSameNumber();
                        } else if (bots.get(1).getBDeck().size() == 0) {
                            player.PlayerMove(player.getPDeck(), bots.get(2).getBDeck());
                            player.removeCardsWithSameNumber();
                        }
                        player.setPDeck(playerDeck1);
                    }

                    if (bots.get(1).getBDeck().size() >= 1 ) {
                        bots.get(0).BotMove(bots.get(0).getBDeck(), bots.get(1).getBDeck());
                        bots.get(0).removeCardsWithSameNumber();
                    } else if (bots.get(1).getBDeck().size() == 0 && bots.get(2).getBDeck().size() != 0) {
                        bots.get(0).BotMove(bots.get(0).getBDeck(), bots.get(2).getBDeck());
                        bots.get(0).removeCardsWithSameNumber();
                    } else if (bots.get(2).getBDeck().size() == 0){
                        bots.get(0).BotMove(bots.get(0).getBDeck(), player.getPDeck());
                        bots.get(0).removeCardsWithSameNumber();
                        player.setPDeck(playerDeck1);
                    }
                    if ( bots.get(2).getBDeck().size() >= 1 ) {
                        bots.get(1).BotMove(bots.get(1).getBDeck(), bots.get(2).getBDeck());
                        bots.get(1).removeCardsWithSameNumber();
                    } else if (bots.get(2).getBDeck().size() == 0 && player.getPDeck().size() != 0 ) {
                        bots.get(1).BotMove(bots.get(1).getBDeck(), player.getPDeck());
                        bots.get(1).removeCardsWithSameNumber();
                        player.setPDeck(playerDeck1);
                    } else if (player.getPDeck().size() == 0){
                        bots.get(1).BotMove(bots.get(1).getBDeck(),bots.get(0).getBDeck());
                        bots.get(1).removeCardsWithSameNumber();
                    }
                    if (player.getPDeck().size() >= 1 ) {
                        bots.get(2).BotMove(bots.get(2).getBDeck(), player.getPDeck());
                        bots.get(2).removeCardsWithSameNumber();
                        player.setPDeck(playerDeck1);
                    } else if ( player.getPDeck().size() == 0 && bots.get(0).getBDeck().size() != 0) {
                        bots.get(2).BotMove(bots.get(2).getBDeck(), bots.get(0).getBDeck());
                        bots.get(2).removeCardsWithSameNumber();
                    } else if (bots.get(0).getBDeck().size() == 0){
                        bots.get(2).BotMove(bots.get(2).getBDeck(), bots.get(1).getBDeck());
                        bots.get(2).removeCardsWithSameNumber();
                    }

                    if (verbose) {
                        printDecks();
                    }
                    int c = 0;
                    if (playerDeck1.size() == 0) {
                        c++;
                    }
                    if (bots.get(0).getBDeck().size() == 0) {
                        c++;
                    }
                    if (bots.get(1).getBDeck().size() == 0) {
                        c++;
                    }
                    if (bots.get(2).getBDeck().size()== 0){
                        c++;
                    }
                    if (c >= 3) {
                        if (playerDeck1.size() == 0) {
                            System.out.println("You Won!");
                        } else {
                            System.out.println("You Lost!");
                        }
                        if (bots.get(0).getBDeck().size() == 0) {
                            System.out.println(bots.get(0).getName() + " Won!");
                        } else {
                            System.out.println(bots.get(0).getName() + " Lost!");
                        }
                        if (bots.get(1).getBDeck().size() == 0) {
                            System.out.println(bots.get(1).getName() + " Won!");
                        } else {
                            System.out.println(bots.get(1).getName() + " Lost!");
                        }
                        if (bots.get(2).getBDeck().size() == 0){
                            System.out.println(bots.get(2).getName() + " Won!");
                        } else{
                            System.out.println(bots.get(2).getName() +" Lost!");
                        }
                        break;
                    }
                }
                break;

        }
    }
    public void printDecks() {
        System.out.println("Player Deck:");
        List<Card> playerDeck = player.getPDeck();
        for (Card card : playerDeck) {
            System.out.println(card.getNumber() + " " + card.getSuit());
        }

        System.out.println("Bots Decks:");
        for (int i = 0; i < bots.size(); i++) {
            Bot bot = bots.get(i);
            System.out.println("Bot " + (i + 1) + " (" + bot.getName() + ") Deck:");
            List<Card> botDeck = bot.getBDeck();
            for (Card card : botDeck) {
                System.out.println(card.getNumber() + " " + card.getSuit());
            }
        }
    }

}
