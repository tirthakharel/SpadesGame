import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
    public void run(){

        final JFrame frame = new JFrame("Spades");
        frame.setLocation(600, 450);

        //Puts the frame on screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(new Game()); }
}
