import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TicTacToe {

    // Track wins and ties
    private static int xWins = 0;
    private static int oWins = 0;
    private static int ties = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (playAgain) {
            // Initialize board (cells 1-9)
            char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
            char currentPlayer = 'X';  // Default starting player
            boolean gameOver = false;
            int movesMade = 0;

            // Prompt to rearrange turn order if it's not the first game
            if (xWins + oWins + ties > 0) {
                if ((xWins + oWins + ties) % 2 == 1) {
                    // Switch order: the loser of the previous round goes first
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    System.out.println("Great! This time " + currentPlayer + " will go first!");
                }
            }

            // Print initial board
            printBoard(board);

            while (!gameOver) {
                // Prompt for move
                System.out.print("\nPlayer " + currentPlayer + ", choose a cell (1-9): ");
                int move;
                try {
                    move = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    // Input was not a valid integer
                    System.out.println("\nThat is not a valid move! Try again.");
                    continue;
                }

                // Validate move
                if (move < 1 || move > 9) {
                    System.out.println("\nThat is not a valid move! Try again.");
                    continue;
                }

                // Convert move to board index (0-based)
                int index = move - 1;

                // Check if cell is already occupied
                if (board[index] == 'X' || board[index] == 'O') {
                    System.out.println("\nThat cell is already taken! Try again.");
                    continue;
                }

                // Make the move
                board[index] = currentPlayer;
                movesMade++;

                // Print updated board
                printBoard(board);

                // Check for winner
                if (checkWinner(board, currentPlayer)) {
                    System.out.println("\nPlayer " + currentPlayer + " wins!");
                    if (currentPlayer == 'X') {
                        xWins++;
                    } else {
                        oWins++;
                    }
                    gameOver = true;
                } else if (movesMade == 9) {
                    // If 9 moves have been made, it's a draw
                    System.out.println("\nIt's a draw!");
                    ties++;
                    gameOver = true;
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            // Print the current game log
            printGameLog();

            // Prompt to play again
            playAgain = askToPlayAgain(scanner);
        }

        // Save game log to disk
        saveGameLog();

        System.out.println("Goodbye!");
        scanner.close();
    }

    /**
     * Prints the 3x3 Tic-Tac-Toe board.
     */
    public static void printBoard(char[] board) {
        System.out.println();
        System.out.println("  " + board[0] + "  |  " + board[1] + "  |  " + board[2]);
        System.out.println(" -----+-----+-----");
        System.out.println("  " + board[3] + "  |  " + board[4] + "  |  " + board[5]);
        System.out.println(" -----+-----+-----");
        System.out.println("  " + board[6] + "  |  " + board[7] + "  |  " + board[8]);
    }

    /**
     * Checks whether the current player has a winning combination.
     */
    public static boolean checkWinner(char[] board, char player) {
        // All winning combinations
        int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] combo : winningPositions) {
            if (board[combo[0]] == player 
                && board[combo[1]] == player 
                && board[combo[2]] == player) {
                return true;
            }
        }

        return false;
    }

    /**
     * Asks the user if they'd like to play again, validates input, and returns true/false.
     */
    public static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("\nWould you like to play again (yes/no)? ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                return true;
            } else if (response.equals("no")) {
                return false;
            } else {
                System.out.println("That is not a valid entry!");
            }
        }
    }

    /**
     * Prints the current game log (wins, ties, etc.)
     */
    public static void printGameLog() {
        System.out.println("\nThe current log is:");
        System.out.println("Player X Wins   " + xWins);
        System.out.println("Player O Wins   " + oWins);
        System.out.println("Ties            " + ties);
    }

    /**
     * Saves the game log to disk in a file called "game.txt".
     */
    public static void saveGameLog() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Player X Wins   " + xWins + "\n");
            writer.write("Player O Wins   " + oWins + "\n");
            writer.write("Ties            " + ties + "\n");
            System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
