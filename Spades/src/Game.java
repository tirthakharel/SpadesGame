import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.RandomAccessFile;
import java.util.Collection;

public class Game extends JFrame implements Runnable {


    public String name1, name2;
    public static JPanel currentScoresPanel;

    public void run(){
    try {
        final JFrame frame = new JFrame("Spades");
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null);

        BufferedImage logo_image = ImageIO.read(new File("resources/game_files/spadesgamelogo.png"));
        BufferedImage start_image = ImageIO.read(new File("resources/game_files/start_button.png"));
        JLabel logo = new JLabel(new ImageIcon(logo_image));

        final JPanel startButtonPanel = new JPanel();
        final JPanel imagePanel = new JPanel();
        final JButton startButton = new JButton();
        final JPanel controlPanel = new JPanel();
        currentScoresPanel = new JPanel();
        final JButton startGame = new JButton("New Game");
        final JButton instructionsButton = new JButton("Instructions");
        final GameArea g = new GameArea();

        String instructions = "In this version of spades, there are 2 players. Each player\n" +
                "gets 13 randomized cards. Each player sets a bid for how many rounds they think they can win.\n" +
                "In order to win a round, one must have the higher value card in the round. \n" +
                "Also, whatever suit player 1 leads with, player 2 has to follow - if plyaer 2 does\n" +
                "not have that suit in their deck, they can throw whatever\n" +
                "card they wish. If the card they throw down is a spades, then they\n" +
                "win that round, unless the other player threw down a higher value spades.\n" +
                "At the end, each person has a tally of how many rounds they won and \n" +
                "if they matched their bid, they get those points added. If they didn't \n" +
                "match their bid, they get those points subtracted. Whoever has the higher \n" +
                "amount of points wins the game. Enjoy!";
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, instructions,
                        "Instructions", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(logo_image));
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(g, "Are you sure?");
                final GameArea g2 = new GameArea();
                frame.remove(g);
                frame.add(g2);
                frame.revalidate();
                frame.repaint();
            }
        });
        //final JLabel currentPlayer = new JLabel("");


        controlPanel.add(startGame);
        controlPanel.add(instructionsButton);

        startButton.setIcon(new ImageIcon(start_image));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name1 = JOptionPane.showInputDialog(frame, "Please enter a nickname for Player 1:", null);
                name2 = JOptionPane.showInputDialog(frame, "Please enter a nickname for Player 2:", null);
                JLabel playerOne = new JLabel(name1 + ":");
                JLabel playerTwo = new JLabel(name2 + ":");
                g.setPlayerOneText(name1); g.setPlayerTwoText(name2);
                currentScoresPanel.add(playerOne); currentScoresPanel.add(playerTwo);
                currentScoresPanel.setBackground(Color.GREEN);
                controlPanel.setBackground(Color.GREEN);
                imagePanel.setVisible(false);
                startButtonPanel.setVisible(false);
                frame.add(controlPanel, BorderLayout.SOUTH);
                frame.add(g);
                frame.add(currentScoresPanel, BorderLayout.WEST);
            }
        });

        imagePanel.setBackground(new Color(244, 139, 0));
        startButtonPanel.setBackground(new Color(244, 139, 0));
        startButtonPanel.add(startButton);
        imagePanel.add(logo, BorderLayout.CENTER);

        frame.add(imagePanel);
        frame.add(startButtonPanel, BorderLayout.SOUTH);


        //Puts the frame on screen
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
      } catch (IOException e){
        System.out.println("boob");
      }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}

