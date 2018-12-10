package defaultpackage;

import java.io.IOException;

public class Deck {

    private defaultpackage.Card[] deck;
    private int cardsUsed;

    public Deck() throws IOException {

        deck = new defaultpackage.Card[52];
        int cardsCreated = 0;
        for (int suit = 0; suit <= 3; suit++) {

            for (int value = 1; value <= 13; value++) {

                deck[cardsCreated] = new defaultpackage.Card(value, suit); //Cards are initially added to the deck in order of suit and value
                cardsCreated++;

            }
        }
        cardsUsed = 0;
    }

    public defaultpackage.Card dealCard () {

        if (cardsUsed == 52) {

            deckShuffle();
        }

        cardsUsed++;

        return deck[cardsUsed - 1];
    }

    public void deckShuffle () {

        for ( int i = 51; i > 0; i-- ) {
            int r = (int)(Math.random()*(i+1)); //Cards are randomly shuffled around within the array
            defaultpackage.Card x = deck[i];
            deck[i] = deck[r];
            deck[r] = x;
        }

        cardsUsed = 0;
    }

}
