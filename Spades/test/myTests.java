import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class myTests {
    @Test
    public void firstTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void secondTest(){
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
}
