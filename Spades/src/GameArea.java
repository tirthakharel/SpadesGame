import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class GameArea extends JPanel {
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_WIDTH = 800;
    private JLabel p1;
    private JLabel p2;
    private static boolean playerOneTurn = true;
    private static boolean playerTwoTurn = false;
    private static Hand p1Hand;
    private static Hand p2Hand;
    private static boolean hidden1 = false;
    private static boolean hidden2 = false;
    private JPanel player1Area; private JPanel player2Area;
    private static int turnCount = 0;
    public static Card currentp1Card;
    public static Card currentp2Card;


    public GameArea(){
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createRaisedBevelBorder());
        setFocusable(true);
        player1Area = new JPanel(); player2Area = new JPanel();
        //player1Area.setLayout(new GridLayout(2,13));player2Area.setLayout(new GridLayout(2,13));
        player1Area.setBorder(BorderFactory.createRaisedBevelBorder());
        player2Area.setBorder(BorderFactory.createRaisedBevelBorder());
        p1 = new JLabel(); p2 = new JLabel();
        Deck d = new Deck();
        /**
        Collection<Card> coveredCards = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            try {
                Card c = new Card(0, "covered");
                coveredCards.add(c);
            } catch (IOException e){
                System.out.println("pooey");
            }
        }
        Collection<Card> coveredCards2 = new ArrayList<>();
        for(int i = 0; i < 13; i++){
            try {
                Card c = new Card(20, "covered");
                coveredCards2.add(c);
            } catch (IOException e){
                System.out.println("pooey");
            }
        }
        Hand notVisible1 = new Hand(coveredCards2);
        Hand notVisible = new Hand(coveredCards);
         */
        p1Hand = new Hand(d.getPlayingCards());
        p2Hand = new Hand(d.getPlayingCards());
        for (Card c: p1Hand.getCards()){
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (turnCount % 2 == 0) {
                        currentp1Card = c;
                        if(isValidMove(p1Hand, p2Hand)) {
                            p1Hand.removeCard(c);
                            p1Hand.remove(c);
                            player1Area.revalidate();
                            player1Area.repaint();
                            switchTurn();
                            turnCount++;
                        } else {
                            JOptionPane.showMessageDialog(c,"invalid move, try again");
                        }
                    }
                }
            });
        }
        for (Card c: p2Hand.getCards()){
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (turnCount % 2 == 1 && turnCount != 0) {
                        currentp2Card = c;
                        if(isValidMove(p1Hand, p2Hand)) {
                            if (turnCount == 25) {
                                currentp1Card = c;
                                p2Hand.removeCard(c);
                                p2Hand.remove(c);
                            }

                            p2Hand.removeCard(c);
                            p2Hand.remove(c);
                            player2Area.revalidate();
                            player2Area.repaint();
                            switchTurn();
                            turnCount++;
                        } else {
                            JOptionPane.showMessageDialog(c,"invalid move, try again");
                        }
                    }
                }
            });
        }
        player1Area.add(p1); player1Area.add(p1Hand);
        player2Area.add(p2); player2Area.add(p2Hand);
        this.add(player1Area); this.add(player2Area);

    }

    public void setPlayerOneText(String text){
        p1.setText(text);
    }

    public void setPlayerTwoText(String text){
        p2.setText(text);
    }

    public void switchTurn(){
        if(playerOneTurn){
            playerOneTurn = false;
            playerTwoTurn = true;
            JOptionPane.showMessageDialog(this,"Player 2's turn now!");
            player1Area.setBackground(Color.white);
            player2Area.setBackground(Color.YELLOW);
        }
        else {
            playerOneTurn = true;
            playerTwoTurn = false;
            JOptionPane.showMessageDialog(this,"Player 1's turn now!");
            player2Area.setBackground(Color.white);
            player1Area.setBackground(Color.YELLOW);
        }
    }

    public boolean isValidMove(Hand pOne, Hand pTwo){
        System.out.println(currentp1Card.getCardType());
        if(currentp1Card.getCardType().equals("spades")){
            System.out.println("break 1");
            if (pOne.onlySpadesLeft()){
                return true;
            }
            return false;
        }

        else if(turnCount != 0) {
            System.out.println("break 2");
         if (currentp2Card.getCardType().equals("spades")) {
                if (!pTwo.onlySpadesLeft()) {
                    return true;
                }
                return false;
            } else if (!currentp2Card.equalsType(currentp1Card)) {
                if (currentp1Card.getCardType().equals("diamonds")) {
                    if (!pTwo.anyDiamondsLeft())
                        return true;
                    return false;
                }
                if (currentp1Card.getCardType().equals("clubs")) {
                    if (!pTwo.anyClubsLeft())
                        return true;
                    return false;
                }
                if (currentp1Card.getCardType().equals("hearts")) {
                    if (!pTwo.anyHeartsLeft())
                        return true;
                    return false;
                }
            }
        }
        System.out.println("break 3");
        return true;

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }
}
