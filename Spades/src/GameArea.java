import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    public static final int GAME_HEIGHT = 450;
    public static final int GAME_WIDTH = 600;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public Dimension getPrefferedSize(){
        return new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }
}
