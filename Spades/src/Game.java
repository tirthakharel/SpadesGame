import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
    public void run(){

        final JFrame frame = new JFrame("TOP LEVEL FRAME");
        frame.setLocation(600, 450);
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(new Game()); }
}
