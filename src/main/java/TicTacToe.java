import java.util.Scanner;

public class TicTacToe {
    private static GameLog gameLog = new GameLog();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (playAgain) {
            // Get player names
            System.out.print("Enter the name of Player X: ");
            String playerXName = scanner.nextLine().trim();
            System.out.print("Enter the name of Player O: ");
            String playerOName = scanner.nextLine().trim();

            Board board = new Board();
            Player playerX = new Player('X', playerXName);
            Player playerO = new Player('O', playerOName);
            Player currentPlayer = playerX;
            boolean gameOver = false;
            int movesMade = 0;

            board.printBoard();

            while (!gameOver) {
                System.out.print("\n" + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), choose a cell (1-9): ");
                int move = getValidMove(scanner);

                // Convert move to board index (0-based)
                int index = move - 1;

                if (board.isCellOccupied(index)) {
                    System.out.println("\nThat cell is already taken! Try again.");
                    board.printBoard();
                    continue;
                }

                // Make the move
                board.makeMove(index, currentPlayer.getSymbol());
                movesMade++;

                // Print updated board
                board.printBoard();

                // Check for winner
                int[] winningCombo = board.checkWinner(currentPlayer.getSymbol());
                if (winningCombo != null) {
                    System.out.println("\n" + currentPlayer.getName() + " wins!");
                    board.highlightWinningLine(winningCombo);  // Highlight the winning line
                    gameLog.incrementWins(currentPlayer.getSymbol());
                    gameOver = true;
                } else if (board.isFull()) {
                    System.out.println("\nIt's a draw!");
                    gameLog.incrementWins('D');  // 'D' for Draw
                    gameOver = true;
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            }

            // Print the current game log
            gameLog.printLog();

            // Prompt to play again
            playAgain = askToPlayAgain(scanner);
        }

        // Save game log to disk
        gameLog.saveLog();

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static int getValidMove(Scanner scanner) {
        int move = -1;
        while (true) {
            try {
                move = Integer.parseInt(scanner.nextLine().trim());
                if (move < 1 || move > 9) {
                    System.out.println("\nThat is not a valid move! Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nThat is not a valid move! Try again.");
            }
        }
        return move;
    }

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
