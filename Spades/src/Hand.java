import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Hand extends JPanel {
    Collection<Card> cards;

    public Hand(Collection<Card> c){
        this.setLayout(new GridLayout(1, 13));
        cards = c;
        for (Card card: c){
            this.add(card);
        }
    }

    public void removeCard(Card c){
        cards.remove(c);
    }

    public Collection<Card> getCards(){
        return cards;
    }

    public Collection<Card> hiddenCards(){
        Collection<Card> hiddenCards = new ArrayList<>();
        for(Card c: this.getCards()){
            try {
                Card cd = new Card(0, "covered");
                hiddenCards.add(cd);
            } catch (IOException e){
                System.out.println("pooey");
            }
        }
        return hiddenCards;
    }

    public boolean onlySpadesLeft(){
        for (Card c : cards){
            if(!c.getCardType().equals("spades"))
                return false;
        }
        return true;
    }

    public boolean anyDiamondsLeft(){
        for (Card c : cards){
            if(c.getCardType().equals("diamonds"))
                return true;
        }
        return false;
    }

    public boolean anyHeartsLeft(){
        for (Card c : cards){
            if(c.getCardType().equals("hearts"))
                return true;
        }
        return false;
    }

    public boolean anyClubsLeft(){
        for (Card c : cards){
            if(c.getCardType().equals("clubs"))
                return true;
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
