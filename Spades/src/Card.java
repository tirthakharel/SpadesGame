import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card extends JButton {

    private int cardValue;
    private String cardType;
    private String IMG_FILE;
    private static BufferedImage img;


    public Card(int val, String type) throws IOException{
        if (type.equals("covered")){
            cardValue = val;
            cardType = type;
            IMG_FILE = "resources/game_files/covered_deck.png";
            img = ImageIO.read(new File(IMG_FILE));
            setIcon(new ImageIcon(img));

        } else {
            cardType = type.trim().replace("_", "");
            cardValue = val;
            IMG_FILE = "resources/card_deck/" + cardValue + "_" + cardType + ".png";
            img = ImageIO.read(new File(IMG_FILE));
            setIcon(new ImageIcon(img));
        }
    }

    public int getValue(){
        return cardValue;
    }

    public String getCardType(){
        //return Suit.valueOf(cardType);
        return cardType;
    }

    public String getImgFile(){
        return IMG_FILE;
    }

    public boolean equalsType(Card c){
        if( cardType.equals(c.getCardType())){
            return true;
        }
        return false;
    }

}
