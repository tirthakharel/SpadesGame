import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.Collection;

public class myTests {
    @Test
    public void firstTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void deckTest(){
        Deck d = new Deck();
        Collection<Card> playingCards = d.getPlayingCards();
        for (Card c: playingCards) {
            System.out.println(c.getCardType() + " " + c.getValue());
        }
        Collection<Card> playingCards2 = d.getPlayingCards();
        for (Card c: playingCards2) {
            System.out.println(c.getCardType() + " " + c.getValue());
        }
        Assert.assertEquals(26, d.size());
    }

    @Test
    public void checkForCorrectCard(){
        try {
            Card c = new Card(3, "spades");
            Card f = new Card(7, "hearts");
            Assert.assertEquals(c.getValue(), 3);
            Assert.assertEquals(f.getValue(), 7);
            Assert.assertEquals(c.getCardType(), "spades");
            Assert.assertEquals(f.getCardType(), "hearts");
            Assert.assertEquals(c.getImgFile(), "resources/card_deck/3_spades.png");
            Assert.assertEquals(f.getImgFile(), "resources/card_deck/7_hearts.png");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testForCovered(){
        try {
            Card c = new Card(0, "covered");
            Assert.assertEquals(c.getCardType(), "covered");
            Assert.assertEquals(c.getImgFile(), "resources/game_files/covered_deck.png");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void HandTest() {
        Deck d = new Deck();
        Collection<Card> c = d.getPlayingCards();
        Hand h = new Hand(c);
        for (Card cd: h.getCards()){
            System.out.println(cd.getCardType());
            if (cd.getCardType().equals("Diamonds")){
                h.removeCard(cd);
            }
        }
        Assert.assertTrue(h.anyDiamondsLeft());
    }

    @Test
    public void HandTest2() {
        Deck d = new Deck();
        Collection<Card> c = d.getPlayingCards();
        Hand h = new Hand(c);
        for (Card cd: h.getCards()){
            System.out.println(cd.getCardType());
            if (cd.getCardType().equals("Clubs")){
                h.removeCard(cd);
            }
        }
        Assert.assertTrue(h.anyClubsLeft());
    }

    @Test
    public void HandTest3() {
        Deck d = new Deck();
        Collection<Card> c = d.getPlayingCards();
        Hand h = new Hand(c);
        for (Card cd: h.getCards()){
            System.out.println(cd.getCardType());
            if (cd.getCardType().equals("Hearts")){
                h.removeCard(cd);
            }
        }
        Assert.assertTrue(h.anyHeartsLeft());
    }
}
