import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame implements ActionListener {
    private JLabel promptLabel, resultLabel;
    private JTextField guessField;
    private JButton guessButton;
    private int secretNumber;
    private int attemptsLeft;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(900, 140);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        secretNumber = (int) (Math.random() * 100) + 1;
        System.out.println(secretNumber);
        attemptsLeft = 5;


        promptLabel = new JLabel("Guess a number between 1 and 100 (" + attemptsLeft + " attempts left):");
        add(promptLabel);

        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        resultLabel = new JLabel("");
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());

                if (guess == secretNumber) {
                    resultLabel.setText("Congratulations!You have guessed the right number!");
                    guessButton.setEnabled(false);
                } else {
                    attemptsLeft--;
                    if (attemptsLeft > 0) {
                        if (guess < secretNumber) {
                            resultLabel.setText("Too low! (" + attemptsLeft + " attempts left)");
                        } else {
                            resultLabel.setText("Too high! (" + attemptsLeft + " attempts left)");
                        }
                    } else {
                        resultLabel.setText("Sorry,U have made use of all of our attempts.The number was: " + secretNumber);
                        guessButton.setEnabled(false);
                    }
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}