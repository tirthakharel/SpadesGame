import java.util.ArrayList;

public class Hand {

    private ArrayList hand;

    public Hand() {

        hand = new ArrayList();
    }

    public void addCard (defaultpackage.Card card) {

        hand.add(card); //Adds the random generated card to the arraylist "hand"
    }

    public int getCardCount() {

        return hand.size(); //Returns the number of cards in the current hand for both player and dealer
    }

    public defaultpackage.Card getPosition(int position) {

        return (defaultpackage.Card) hand.get(position); //Returns position in hand to create card spacing when displayed

    }

    public int getValue() {

        int totalHandValue;
        int numCards;
        totalHandValue = 0;
        numCards = getCardCount();

        for (int i = 0; i < numCards; i++) {

            defaultpackage.Card card;
            int cardValue;
            card = getPosition(i);
            cardValue = card.getValue();

            //If the card is a face card (King, Queen, Jack), the value is set to 10
            if (cardValue >= 10) {

                cardValue = 10;

            }

            //If the card is an ace, either is worth 1 or 11 based on the current hand value
            if (cardValue == 1) {

                if (totalHandValue + 11 <= 21) {

                    cardValue = 11;
                } else {

                    cardValue = 1;
                }
            }

            totalHandValue += cardValue;
        }

        return totalHandValue;
    }





}

