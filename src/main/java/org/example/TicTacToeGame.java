package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a simple implementation of the Tic-Tac-Toe game using Java Swing.
 */
public class TicTacToeGame extends JFrame {
    // Use of arrays to represent the game board
    private final JButton[][] buttons;

    // Record to store the current player and game status
    private char currentPlayer;
    private boolean gameEnded;

    /**
     * Constructs a new TicTacToeGame instance.
     */
    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameEnded = false;

        initializeButtons();
    }

    /**
     * Initializes the game board buttons.
     */
    private void initializeButtons() {
        setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);

                final int finalRow = row;
                final int finalCol = col;

                buttons[row][col].addActionListener(e -> buttonClicked(finalRow, finalCol));

                add(buttons[row][col]);
            }
        }
    }

    /**
     * Handles button click events.
     *
     * @param row The row index of the clicked button.
     * @param col The column index of the clicked button.
     */
    private void buttonClicked(int row, int col) {
        if (!gameEnded && buttons[row][col].getText().isEmpty()) {
            buttons[row][col].setText(Character.toString(currentPlayer));

            if (checkForWinner(row, col)) {
                gameEnded = true;
                int option = showGameOverDialog("Player " + currentPlayer + " wins! Do you want to play again?");
                handleGameOverOption(option);
            } else if (isBoardFull()) {
                gameEnded = true;
                int option = showGameOverDialog("It's a tie! Do you want to play again?");
                handleGameOverOption(option);
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    /**
     * Displays a game over dialog with the specified message.
     *
     * @param message The message to be displayed in the dialog.
     * @return An option indicating the player's choice.
     */
    private int showGameOverDialog(String message) {
        return JOptionPane.showOptionDialog(
                this,
                message,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Play Again", "Exit"},
                "Play Again"
        );
    }

    /**
     * Handles the player's choice after a game over.
     *
     * @param option The player's choice.
     */
    private void handleGameOverOption(int option) {
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * Resets the game state.
     */
    private void resetGame() {
        // Reset game state
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
        gameEnded = false;
    }

    /**
     * Checks for a winner based on the last move's position.
     *
     * @param row The row index of the last move.
     * @param col The column index of the last move.
     * @return True if there is a winner, false otherwise.
     */
    private boolean checkForWinner(int row, int col) {
        return checkRow(row) || checkColumn(col) || checkDiagonals(row, col);
    }

    /**
     * Checks if a row has the same player's symbol.
     *
     * @param row The row index to check.
     * @return True if the row has the same player's symbol, false otherwise.
     */
    private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[row][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[row][2].getText().equals(Character.toString(currentPlayer));
    }

    /**
     * Checks if a column has the same player's symbol.
     *
     * @param col The column index to check.
     * @return True if the column has the same player's symbol, false otherwise.
     */
    private boolean checkColumn(int col) {
        return buttons[0][col].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][col].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][col].getText().equals(Character.toString(currentPlayer));
    }

    /**
     * Checks if either of the diagonals has the same player's symbol.
     *
     * @param row The row index of the last move.
     * @param col The column index of the last move.
     * @return True if either diagonal has the same player's symbol, false otherwise.
     */
    private boolean checkDiagonals(int row, int col) {
        return checkMainDiagonal() || checkAntiDiagonal();
    }

    /**
     * Checks if the main diagonal has the same player's symbol.
     *
     * @return True if the main diagonal has the same player's symbol, false otherwise.
     */
    private boolean checkMainDiagonal() {
        return buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][2].getText().equals(Character.toString(currentPlayer));
    }

    /**
     * Checks if the anti-diagonal has the same player's symbol.
     *
     * @return True if the anti-diagonal has the same player's symbol, false otherwise.
     */
    private boolean checkAntiDiagonal() {
        return buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][0].getText().equals(Character.toString(currentPlayer));
    }

    /**
     * Checks if the game board is full.
     *
     * @return True if the board is full, false otherwise.
     */
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}
