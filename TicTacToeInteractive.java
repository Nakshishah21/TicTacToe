import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class TicTacToeInteractive implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;
    private JLabel statusLabel;
    private JLabel xScoreLabel;
    private JLabel oScoreLabel;
    private int xScore = 0;
    private int oScore = 0;
    private JButton restartButton;
    ImageIcon logo=new ImageIcon("tic.jpg"); 
    public TicTacToeInteractive() {
        frame = new JFrame(" LET'S PLAY \n Tic-Tac-Toe ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(logo.getImage());

       // frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.BLACK); // Black background

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("calibri", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.yellow); // Darker button background
            buttons[i].setForeground(Color.red); // White text
            buttons[i].setBorder(new LineBorder(Color.pink, 2));
            panel.add(buttons[i]);
        }

        JPanel scorePanel = new JPanel(new GridLayout(1, 3));
        scorePanel.setBackground(Color.pink);

        xScoreLabel = new JLabel("X: 0");
        xScoreLabel.setForeground(Color.red);
        xScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        xScoreLabel.setFont(new Font("TT Lakes Neue", Font.PLAIN, 20));

        oScoreLabel = new JLabel("O: 0");
        oScoreLabel.setForeground(Color.blue);
        oScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        oScoreLabel.setFont(new Font("TT Lakes Neue", Font.PLAIN, 20));

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());
        restartButton.setBackground(Color.pink);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(new Font("Agency FB", Font.BOLD, 18));

        scorePanel.add(xScoreLabel);
        scorePanel.add(restartButton);
        scorePanel.add(oScoreLabel);

        statusLabel = new JLabel("X's turn");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(new Font("Freestyle Script", Font.PLAIN, 20));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(Color.pink);
        statusLabel.setOpaque(true);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(scorePanel, BorderLayout.NORTH);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setSize(450, 500);
        frame.getContentPane().setBackground(Color.pink); 
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (xTurn) {
            button.setText("X");
            button.setForeground(Color.red);
            statusLabel.setText("O's turn");
        } else {
            button.setText("O");
            button.setForeground(Color.blue);
            statusLabel.setText("X's turn");
        }
        button.setEnabled(false);
        xTurn = !xTurn;

        checkForWinner();
    }

    public void checkForWinner() {
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText()) && !buttons[i].isEnabled()) {
                announceWinner(buttons[i].getText());
                return;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText()) && !buttons[i].isEnabled()) {
                announceWinner(buttons[i].getText());
                return;
            }
        }
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
            announceWinner(buttons[0].getText());
            return;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
            announceWinner(buttons[2].getText());
            return;
        }

        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            announceTie();
        }
    }

    public void announceWinner(String winner) {
        if (winner.equals("X")) {
            xScore++;
            xScoreLabel.setText("X: " + xScore);
        } else {
            oScore++;
            oScoreLabel.setText("O: " + oScore);
        }
        JOptionPane.showMessageDialog(frame, winner + " wins!");
        resetBoard(); 
    }

    public void announceTie() {
        JOptionPane.showMessageDialog(frame, "Tie game!");
        resetBoard();
    }

    public void resetGame() {
        xScore = 0;
        oScore = 0;
        xScoreLabel.setText("X: 0");
        oScoreLabel.setText("O: 0");
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        xTurn = true;
        statusLabel.setText("X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
                new TicTacToeInteractive();
            }
        });
    }
}
