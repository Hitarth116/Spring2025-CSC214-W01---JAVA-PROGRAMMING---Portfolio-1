import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (playAgain) {
            // Initialize board (cells 1-9)
            char[] board = {'1','2','3','4','5','6','7','8','9'};
            char currentPlayer = 'X';
            boolean gameOver = false;
            int movesMade = 0;

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
                    gameOver = true;
                } else if (movesMade == 9) {
                    // If 9 moves have been made, it's a draw
                    System.out.println("\nIt's a draw!");
                    gameOver = true;
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            // Prompt to play again
            playAgain = askToPlayAgain(scanner);
        }

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
}
