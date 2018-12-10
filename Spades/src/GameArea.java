import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_WIDTH = 800;
    public boolean playing = false;
    private JLabel status;


    public GameArea(JLabel status){
        setBorder(BorderFactory.createRaisedBevelBorder());
        setFocusable(true);
        this.status = status;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }
}
