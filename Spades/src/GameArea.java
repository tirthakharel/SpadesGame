import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;

public class GameArea extends JPanel {
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_WIDTH = 800;
    private JLabel p1, p1ScoreLabel;
    private JLabel p2, p2ScoreLabel;
    private static boolean playerOneTurn = true;
    private static boolean playerTwoTurn = false;
    private static Hand p1Hand;
    private static Hand p2Hand;
    private static boolean hidden1 = true;
    private static boolean hidden2 = true;
    private JPanel player1Area; private JPanel player2Area;
    private static int turnCount = 0;
    public static Card currentp1Card;
    public static Card currentp2Card;
    public int p1Bid, p2Bid;
    public int p1score, p2score;

    public GameArea(){
        p1score = 0; p2score = 0;
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createRaisedBevelBorder());
        setFocusable(true);
        player1Area = new JPanel();
        player2Area = new JPanel();
        player1Area.setBorder(BorderFactory.createRaisedBevelBorder());
        player2Area.setBorder(BorderFactory.createRaisedBevelBorder());
        player1Area.setBackground(Color.yellow);
        p1 = new JLabel();
        p2 = new JLabel();
        JButton p1ShowHide = new JButton("Show/Hide");
        JButton p2ShowHide = new JButton("Show/Hide");
        Deck d = new Deck();
        p1Hand = new Hand(d.getPlayingCards());
        p2Hand = new Hand(d.getPlayingCards());

        p1ShowHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hidden1){
                    hidden1 = false;
                    player1Area.removeAll();
                    player1Area.add(p1);
                    player1Area.add(p1Hand);
                    player1Area.add(p1ShowHide);
                    player1Area.revalidate();
                    player1Area.repaint();
                } else {
                    hidden1 = true;
                    Hand coveredCards = new Hand(p1Hand.hiddenCards());
                    player1Area.removeAll();
                    player1Area.add(p1);
                    player1Area.add(coveredCards);
                    player1Area.add(p1ShowHide);
                    player1Area.revalidate();
                    player1Area.repaint();
                }
            }
        });
        p2ShowHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hidden2){
                    hidden2 = false;
                    player2Area.removeAll();
                    player2Area.add(p2);
                    player2Area.add(p2Hand);
                    player2Area.add(p2ShowHide);
                    player2Area.revalidate();
                    player2Area.repaint();
                } else {
                    hidden2 = true;
                    Hand coveredCards = new Hand(p2Hand.hiddenCards());
                    player2Area.removeAll();
                    player2Area.add(p2);
                    player2Area.add(coveredCards);
                    player2Area.add(p2ShowHide);
                    player2Area.revalidate();
                    player2Area.repaint();
                }
            }
        });
        for (Card c: p1Hand.getCards()){
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (turnCount == 0) {
                        p1Bid = Integer.parseInt(JOptionPane.showInputDialog("How much would " + p1.getText() + " like to bid?"));
                        p2Bid = Integer.parseInt(JOptionPane.showInputDialog("How much would " + p2.getText() + " like to bid?"));
                    }
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
                                p2Hand.removeCard(c);
                                p2Hand.remove(c);
                                keepScore(currentp1Card, currentp2Card);
                                JOptionPane.showMessageDialog(c, endGame());
                            }

                            p2Hand.removeCard(c);
                            p2Hand.remove(c);
                            player2Area.revalidate();
                            player2Area.repaint();
                            switchTurn();
                            turnCount++;
                            keepScore(currentp1Card, currentp2Card);
                        } else {
                            JOptionPane.showMessageDialog(c,"invalid move, try again");
                        }
                    }
                }
            });
        }
        player1Area.add(p1); player1Area.add(new Hand(p1Hand.hiddenCards()));
        player2Area.add(p2); player2Area.add(new Hand(p2Hand.hiddenCards()));
        player1Area.add(p1ShowHide); player2Area.add(p2ShowHide);
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
            if(currentp1Card.getValue() > 10) {
                if(currentp1Card.getValue() == 11)
                JOptionPane.showMessageDialog(this,
                        p2.getText() + "'s turn now!\n" + p1.getText() + " played: Jack of " + currentp1Card.getCardType());
                if(currentp1Card.getValue() == 12)
                    JOptionPane.showMessageDialog(this,
                            p2.getText() + "'s turn now!\n" + p1.getText() + " played: Queen of " + currentp1Card.getCardType());
                if(currentp1Card.getValue() == 13)
                    JOptionPane.showMessageDialog(this,
                            p2.getText() + "'s turn now!\n" + p1.getText() + " played: King of " + currentp1Card.getCardType());
                if(currentp1Card.getValue() == 14)
                    JOptionPane.showMessageDialog(this,
                            p2.getText() + "'s turn now!\n" + p1.getText() + " played: Ace of " + currentp1Card.getCardType());
            } else {
                JOptionPane.showMessageDialog(this,
                        p2.getText() + "'s turn now!\n" + p1.getText() + " played: " + currentp1Card.getValue() + " of " + currentp1Card.getCardType());
            }
            player1Area.setBackground(Color.white);
            player2Area.setBackground(Color.YELLOW);
        }
        else {
            playerOneTurn = true;
            playerTwoTurn = false;
            if(currentp2Card.getValue() > 10) {
                if(currentp2Card.getValue() == 11)
                    JOptionPane.showMessageDialog(this,
                            p1.getText() + "'s turn now!\n" + p2.getText() + " played: Jack of " + currentp2Card.getCardType());
                if(currentp2Card.getValue() == 12)
                    JOptionPane.showMessageDialog(this,
                            p1.getText() + "'s turn now!\n" + p2.getText() + " played: Queen of " + currentp2Card.getCardType());
                if(currentp2Card.getValue() == 13)
                    JOptionPane.showMessageDialog(this,
                            p1.getText() + "'s turn now!\n" + p2.getText() + " played: King of " + currentp2Card.getCardType());
                if(currentp2Card.getValue() == 14)
                    JOptionPane.showMessageDialog(this,
                            p1.getText() + "'s turn now!\n" + p2.getText() + " played: Ace of " + currentp2Card.getCardType());
            } else {
                JOptionPane.showMessageDialog(this,
                        p1.getText() + "'s turn now!\n" + p2.getText() + " played: " + currentp2Card.getValue() + " of " + currentp2Card.getCardType());
            }
            player2Area.setBackground(Color.white);
            player1Area.setBackground(Color.YELLOW);
        }
    }

    public boolean isValidMove(Hand pOne, Hand pTwo){
        if (turnCount%2 == 0) {
            if (currentp1Card.getCardType().equals("spades")) {
                if (pOne.onlySpadesLeft()) {
                    return true;
                }
                return false;
            }
        }
        else if(turnCount != 0) {
            if (currentp2Card.getCardType().equals("spades")) {
                if (pTwo.onlySpadesLeft()) {
                    return true;
                } else if (currentp1Card.getCardType().equals("diamonds")) {
                    if (!pTwo.anyDiamondsLeft())
                        return true;
                    return false;
                }
                else if (currentp1Card.getCardType().equals("clubs")) {
                    if (!pTwo.anyClubsLeft())
                        return true;
                    return false;
                }
                else if (currentp1Card.getCardType().equals("hearts")) {
                    if (!pTwo.anyHeartsLeft())
                        return true;
                    return false;
                }
                else return true;
            }
           else if (!currentp2Card.equalsType(currentp1Card)) {
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
        return true;
    }

    public void keepScore(Card one, Card two){
        try {
            File f = new File("resources/game_files/scores.txt");
            RandomAccessFile r = new RandomAccessFile(f, "rw");
            r.setLength(0);
            r.close();
            FileWriter fw = new FileWriter(f);
            if(one.getCardType().equals("spades")){
                if(two.getCardType().equals("spades")){
                    if(one.getValue() > two.getValue())
                        p1score++;
                    else
                        p2score++;
                }
            } else if(two.getCardType().equals("spades"))
                  p2score++;
              else if(one.getCardType().equals(two.getCardType())){
                  if(one.getValue() > two.getValue())
                      p1score++;
                  else
                      p2score++;
              }
                else
                    p1score++;
            fw.write(p1.getText()+ "'s score: " + p1score + "\n" + p2.getText()+ "'s score: " + p2score);
            fw.close();
            Game.currentScoresPanel.removeAll();
            p1ScoreLabel = new JLabel(": "  + p1score);
            p2ScoreLabel = new JLabel(": "  + p2score);
            JLabel bid1label = new JLabel(p1.getText() + "'s bid: " + p1Bid);
            JLabel bid2label = new JLabel(p2.getText() + "'s bid: " + p2Bid);
            Game.currentScoresPanel.add(p1, BorderLayout.NORTH);
            Game.currentScoresPanel.add(p1ScoreLabel, BorderLayout.NORTH);
            Game.currentScoresPanel.add(p2, BorderLayout.NORTH);
            Game.currentScoresPanel.add(p2ScoreLabel, BorderLayout.NORTH);
            Game.currentScoresPanel.add(bid1label, BorderLayout.SOUTH);
            Game.currentScoresPanel.add(bid2label, BorderLayout.SOUTH);
            Game.currentScoresPanel.revalidate();
            Game.currentScoresPanel.repaint();
        } catch (IOException e){
            System.out.println("pooey");
        }
    }

    public String endGame(){
        if (p1Bid == 0) {
            if (p1score == 0){
                p1score = 10;
            }
        }
        if (p2Bid == 0) {
            if (p2score == 0){
                p2score = 10;
            }
        }
        if (p1score >= p1Bid) {
            p1score = p1Bid;
        }
        if (p1score < p1Bid) {
            p1score = -p1Bid;
        }
        if (p2score >= p2Bid) {
            p2score = p2Bid;
        }
        if (p2score < p2Bid) {
            p2score = -p2Bid;
        }
        if (p1score > p2score)
            return p1.getText() + " is the winner!";
        else
            return  p2.getText() + " is the winner!";
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