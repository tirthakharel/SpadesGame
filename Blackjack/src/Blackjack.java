
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.IOException;
import java.awt.Graphics;

public class Blackjack extends Applet {

    public Blackjack() throws IOException {

        //Creates an applet with these set specifications
        setBackground(Color.GREEN);
        setLayout(new BorderLayout());
        setFont(Font.getFont(Font.SANS_SERIF));

        Layout layout = new Layout();
        add(layout, BorderLayout.CENTER);

        Panel buttonPanel = new Panel();
        add(buttonPanel, BorderLayout.SOUTH);

        Button hit = new Button("Hit");
        hit.addActionListener(layout);
        buttonPanel.add(hit);

        Button stand = new Button("Stand");
        stand.addActionListener(layout);
        buttonPanel.add(stand);

        Button newGame = new Button("New Game");
        newGame.addActionListener(layout);
        buttonPanel.add(newGame);

    }

    public class Layout extends Canvas implements ActionListener {

        defaultpackage.Deck deck;
        Hand dHand;
        Hand pHand;
        String message;
        boolean playinggame;

        Layout() throws IOException {

            setBackground(new Color(30, 75, 25));
            NewGame();
        }

        public void actionPerformed(ActionEvent event) {


            String command = event.getActionCommand(); //Sets actions to the different buttons

            if (command.equals("Hit")) {

                Hit();
            } else if (command.equals("Stand")) {

                Stand();
            } else if (command.equals("New Game"))

                try {
                    NewGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        public void Hit() {

            if (playinggame == false) {
                message = "Click \"New Game\" to start a new game";
                repaint();
                return;
            }

            pHand.addCard(deck.dealCard()); //Deals one random card to the player when Hit button is pressed

            //Evaluation of player's hand as they generate more cards
            if (pHand.getValue() > 21) {

                message = "You have gone over 21 and therefore have lost";
                playinggame = false;

            } else if (pHand.getCardCount() == 5) {

                message = "You win because you got 5 cards without going above 21";
                playinggame = false;

            } else {

                message = "You have a score of " + pHand.getValue() + ". Would you like to Hit or Stand?";
            }

            repaint();
        }

        public void Stand() {

            if (playinggame == false) {
                message = "Click \"New Game\" to start a new game";
                repaint();
                return;
            }

            //Keeps dealing random cards to the dealer until they have reached a value over 17 or 5 cards
            while (dHand.getValue() <= 17 && dHand.getCardCount() <= 5) {

                dHand.addCard(deck.dealCard());

            }
            //Evaluation after the player hits stand
            if (dHand.getValue() > 21) {

                message = "You win! The dealer went over 21.";
                playinggame = false;

            } else if (dHand.getCardCount() == 5 && dHand.getValue() <= 21) {

                message = "You lose as the dealer got 5 cards without going over 21";
                playinggame = false;

            } else if (dHand.getValue() > pHand.getValue()) {

                message = "You lose " + dHand.getValue() + " to " + pHand.getValue();
                playinggame = false;

            } else if (dHand.getValue() == pHand.getValue()) {

                message = "You lose as the dealer always wins on a tie";
                playinggame = false;

            } else {

                message = "You win " + pHand.getValue() + " to " + dHand.getValue() + ".";
                playinggame = false;

            }

            repaint();
        }


        public void NewGame() throws IOException {

            deck = new defaultpackage.Deck();
            dHand = new Hand();
            pHand = new Hand();

            //Shuffles the deck and deals the initial cards
            deck.deckShuffle();
            pHand.addCard(deck.dealCard());
            pHand.addCard(deck.dealCard());
            dHand.addCard(deck.dealCard());


            //initial game evaluation
            if (dHand.getValue() == 21) {

                message = "You lose because the Dealer got Blackjack :(";
                playinggame = false;

            } else if (pHand.getValue() == 21) {

                message = "You win because you got Blackjack!";
                playinggame = false;

            } else {

                message = "You have " + pHand.getValue() + ". Would you like to Hit or Stand?";
                playinggame = true;

            }

            repaint();

        }

        public void paint(Graphics g) {

            //Creates string items for dealer and player's cards
            g.setColor(Color.RED);
            g.drawString(message, 9, 340);
            g.drawString("Dealer's Cards: ", 10, 23);
            g.drawString("Your Cards: ", 10, 153);

            //Algorithm for displaying cards for both the dealer and player, in an incremented and spaced out fashion
            for (int i = 0; i < dHand.getCardCount(); i++) {

                displayCards(g, dHand.getPosition(i), 10 + i * 90, 30);

            }

            for (int i = 0; i < pHand.getCardCount(); i++)

                displayCards(g, pHand.getPosition(i), 10 + i * 90, 160);

            }

        public void displayCards(Graphics g, defaultpackage.Card card, int x, int y) {

            //Calls the getImage() method from the Card.java class which returns which image to display
            Image cardImage = card.getImage();
            g.drawImage(cardImage, x, y, this);

        }
    }


}



