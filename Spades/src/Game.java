import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.RandomAccessFile;

public class Game extends JFrame implements Runnable {


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
        final JPanel currentScoresPanel = new JPanel();
        final JButton startGame = new JButton("New Game");
        final JButton instructionsButton = new JButton("Instructions");
        final GameArea g = new GameArea(new JLabel("Playing"));
        String instructions = "[Put instructions here]";
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
                g.playing = true;

            }
        });
        //final JLabel currentPlayer = new JLabel("");
        final JPanel scorePanel = new JPanel();

        controlPanel.add(startGame);
        controlPanel.add(instructionsButton);

        startButton.setIcon(new ImageIcon(start_image));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name1 = JOptionPane.showInputDialog(frame, "Please enter a nickname for Player 1:", null);
                    String name2 = JOptionPane.showInputDialog(frame, "Please enter a nickname for Player 2:", null);

                    File f = new File("resources/game_files/usernames.txt");
                    RandomAccessFile r = new RandomAccessFile(f, "rw");
                    r.setLength(0);
                    r.close();
                    FileWriter fw = new FileWriter(f);
                    fw.write(name1 + "\n" + name2);
                    fw.close();

                    JLabel playerOne = new JLabel(name1 + ":");
                    JLabel playerTwo = new JLabel(name2 + ":");
                    scorePanel.add(playerOne); scorePanel.add(playerTwo); playerOne.setText("hes");
                    currentScoresPanel.add(playerOne); currentScoresPanel.add(playerTwo);
                    currentScoresPanel.setBackground(Color.GREEN);
                } catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                controlPanel.setBackground(Color.GREEN);
                imagePanel.setVisible(false);
                startButtonPanel.setVisible(false);
                frame.add(controlPanel, BorderLayout.SOUTH);
                frame.add(scorePanel, BorderLayout.WEST);
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

