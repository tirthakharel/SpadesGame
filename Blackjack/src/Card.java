package defaultpackage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

    //Suits are set to numerical values
    public static int Spades = 0;
    public static int Hearts = 1;
    public static int Clubs = 2;
    public static int Diamonds = 3;

    private final int suit;
    private final int value;

    private final Image cardImage;
    private static final int CARD_WIDTH = 75;
    private static final int CARD_HEIGHT = 100;


    public Card(int value, int suit) throws IOException {

        this.value = value;
        this.suit = suit;

        BufferedImage image = null;
        try {

            image = ImageIO.read(getClass().getResource(getFileName())); //Card read in as the Suit + Underscore + Value
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cardImage = image == null ? null : image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH); //cardImage final image set equal to the read in buffered image and scaled down to the appropriate size
    }


    private String getFileName() {

        return getSuitAsString() + "_" + getValueAsString() + ".jpeg"; //Returns picture file name according to drawn card
    }

    public int getValue() {

        return value;
    }

    public Image getImage() {

        return cardImage;
    }

    public String getSuitAsString() {

        if (suit == Spades) {

            return "Spades";
        } else if (suit == Hearts) {

            return "Hearts";
        } else if (suit == Clubs) {

            return "Clubs";
        } else if (suit == Diamonds) {

            return "Diamonds";
        } else {

            return "Invalid Suit";
        }
    }

    public String getValueAsString() {

        if (value == 1) {

            return "Ace";
        } else if (value == 11) {

            return "Jack";
        } else if (value == 12) {

            return "Queen";
        } else if (value == 13) {

            return "King";
        } else if (value > 1 && value < 11) {

            return String.valueOf(value);
        } else {

            return "Invalid Value";
        }

    }

}


