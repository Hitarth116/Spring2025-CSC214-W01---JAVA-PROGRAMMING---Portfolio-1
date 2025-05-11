import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class TicTacToe {
    private static GameLog gameLog = new GameLog();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        
        System.out.println("Welcome to Tic-Tac-Toe!");
        
        while (playAgain) {
            int gameMode = selectGameMode(scanner);
            int difficultyLevel = 1; // Default is easy
            
            // Ask for difficulty level if computer is involved
            if (gameMode == 2 || gameMode == 3) {
                difficultyLevel = selectDifficultyLevel(scanner);
            }
            
            Board board = new Board();
            Player playerX;
            Player playerO;
            
            // The first player is X by default but can be changed
            char firstPlayerSymbol = selectFirstPlayerSymbol(scanner);
            char secondPlayerSymbol = (firstPlayerSymbol == 'X') ? 'O' : 'X';
            
            switch (gameMode) {
                case 1: // Human vs Human
                    System.out.print("Enter the name of Player " + firstPlayerSymbol + ": ");
                    String playerFirstName = scanner.nextLine().trim();
                    System.out.print("Enter the name of Player " + secondPlayerSymbol + ": ");
                    String playerSecondName = scanner.nextLine().trim();
                    
                    playerX = (firstPlayerSymbol == 'X') ? 
                        new Player('X', playerFirstName) : 
                        new Player('X', playerSecondName);
                    playerO = (firstPlayerSymbol == 'O') ? 
                        new Player('O', playerFirstName) : 
                        new Player('O', playerSecondName);
                    break;
                    
                case 2: // Human vs Computer
                    System.out.print("Enter your name: ");
                    String humanName = scanner.nextLine().trim();
                    
                    if (firstPlayerSymbol == 'X') {
                        playerX = new Player('X', humanName);
                        playerO = new ComputerPlayer('O', difficultyLevel);
                        System.out.println("Great! You will go first as X, and the computer will be O.");
                    } else {
                        playerX = new ComputerPlayer('X', difficultyLevel);
                        playerO = new Player('O', humanName);
                        System.out.println("Great! You will go second as O, and the computer will be X.");
                    }
                    break;
                    
                case 3: // Computer vs Human
                    System.out.print("Enter your name: ");
                    humanName = scanner.nextLine().trim();
                    
                    if (firstPlayerSymbol == 'X') {
                        playerX = new ComputerPlayer('X', difficultyLevel);
                        playerO = new Player('O', humanName);
                        System.out.println("Great! The computer will go first as X, and you will be O.");
                    } else {
                        playerX = new Player('X', humanName);
                        playerO = new ComputerPlayer('O', difficultyLevel);
                        System.out.println("Great! The computer will go second as O, and you will be X.");
                    }
                    break;
                    
                default:
                    playerX = new Player('X', "Player X");
                    playerO = new Player('O', "Player O");
                    break;
            }
            
            playGame(board, playerX, playerO, scanner);
            
            // Update player statistics in the game log
            if (playerX.getName() != null && !playerX.getName().equals("Computer")) {
                gameLog.updatePlayerStats(playerX.getName());
            }
            if (playerO.getName() != null && !playerO.getName().equals("Computer")) {
                gameLog.updatePlayerStats(playerO.getName());
            }
            
            // Print the current game log
            gameLog.printLog();
            
            // Print player statistics
            gameLog.printPlayerStats();
            
            // Prompt to play again
            playAgain = askToPlayAgain(scanner);
        }
        
        // Save game log to disk
        gameLog.saveLog();
        System.out.println("Goodbye!");
        scanner.close();
    }
    
    // Changed to public for testing purposes
    public static int selectGameMode(Scanner scanner) {
        System.out.println("\nWhat kind of game would you like to play?");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");
        
        System.out.print("What is your selection? ");
        
        int selection = -1;
        while (selection < 1 || selection > 3) {
            try {
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection < 1 || selection > 3) {
                    System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                    System.out.print("What is your selection? ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                System.out.print("What is your selection? ");
            }
        }
        
        return selection;
    }
    
    // New method to select difficulty level
    public static int selectDifficultyLevel(Scanner scanner) {
        System.out.println("\nSelect computer difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        
        System.out.print("What is your selection? ");
        
        int selection = -1;
        while (selection < 1 || selection > 3) {
            try {
                selection = Integer.parseInt(scanner.nextLine().trim());
                if (selection < 1 || selection > 3) {
                    System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                    System.out.print("What is your selection? ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                System.out.print("What is your selection? ");
            }
        }
        
        return selection;
    }
    
    // New method to allow players to choose their symbol
    public static char selectFirstPlayerSymbol(Scanner scanner) {
        System.out.println("\nWould you like the first player to be X or O?");
        System.out.print("Enter X or O: ");
        
        char symbol = ' ';
        while (symbol != 'X' && symbol != 'O') {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                symbol = input.charAt(0);
            } else {
                System.out.println("Invalid selection. Please enter X or O.");
                System.out.print("Enter X or O: ");
            }
        }
        
        return symbol;
    }
    
    private static void playGame(Board board, Player playerX, Player playerO, Scanner scanner) {
        Player currentPlayer = playerX;
        boolean gameOver = false;
        
        // Stack to store move history for undo feature
        Stack<Move> moveHistory = new Stack<>();
        
        board.printBoard();
        
        while (!gameOver) {
            int move = -1;
            
            if (currentPlayer instanceof ComputerPlayer) {
                // Computer's turn
                System.out.println("\n" + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") is thinking...");
                ComputerPlayer computerPlayer = (ComputerPlayer) currentPlayer;
                
                // Simulate thinking time based on difficulty
                simulateThinking(computerPlayer.getDifficultyLevel());
                
                move = computerPlayer.makeMove(board);
            } else {
                // Human's turn
                System.out.print("\n" + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), choose a cell (1-9) or 'u' to undo: ");
                String userInput = scanner.nextLine().trim().toLowerCase();
                
                // Check if the player wants to undo
                if (userInput.equals("u")) {
                    if (undoLastMoves(moveHistory, board)) {
                        System.out.println("Move undone.");
                        board.printBoard();
                        continue;
                    } else {
                        System.out.println("Cannot undo any further!");
                        System.out.print("Choose a cell (1-9): ");
                        move = getValidMove(scanner);
                        // Convert move to board index (0-based)
                        move = move - 1;
                    }
                } else {
                    try {
                        move = Integer.parseInt(userInput) - 1; // Convert to 0-based index
                        if (move < 0 || move > 8) {
                            System.out.println("\nThat is not a valid move! Try again.");
                            board.printBoard();
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\nThat is not a valid move! Try again.");
                        board.printBoard();
                        continue;
                    }
                }
            }
            
            // Check if the move is valid
            if (board.isCellOccupied(move)) {
                if (!(currentPlayer instanceof ComputerPlayer)) {
                    System.out.println("\nThat cell is already taken! Try again.");
                    board.printBoard();
                    continue;
                }
            }
            
            // Make the move
            board.makeMove(move, currentPlayer.getSymbol());
            
            // Store the move in history
            moveHistory.push(new Move(move, currentPlayer.getSymbol()));
            
            // Print updated board
            board.printBoard();
            
            // Check for winner
            int[] winningCombo = board.checkWinner(currentPlayer.getSymbol());
            if (winningCombo != null) {
                System.out.println("\n" + currentPlayer.getName() + " wins!");
                board.highlightWinningLine(winningCombo);  // Highlight the winning line
                gameLog.incrementWins(currentPlayer.getSymbol());
                
                // Update player-specific stats
                if (!currentPlayer.getName().equals("Computer")) {
                    gameLog.recordPlayerWin(currentPlayer.getName());
                }
                
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
    }
    
    // New method to undo the last two moves (for a full turn)
    private static boolean undoLastMoves(Stack<Move> moveHistory, Board board) {
        // Check if there are moves to undo
        if (moveHistory.isEmpty()) {
            return false;
        }
        
        // Get the last move
        Move lastMove = moveHistory.pop();
        
        // Reset the board cell
        board.undoMove(lastMove.getPosition());
        
        return true;
    }
    
    // New method to simulate computer thinking time
    private static void simulateThinking(int difficultyLevel) {
        try {
            // Thinking time increases with difficulty
            int thinkingTime = difficultyLevel * 500; // 500ms, 1000ms, or 1500ms
            TimeUnit.MILLISECONDS.sleep(thinkingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static int getValidMove(Scanner scanner) {
        int move = -1;
        while (true) {
            try {
                move = Integer.parseInt(scanner.nextLine().trim());
                if (move < 1 || move > 9) {
                    System.out.println("\nThat is not a valid move! Try again.");
                    System.out.print("Choose a cell (1-9): ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nThat is not a valid move! Try again.");
                System.out.print("Choose a cell (1-9): ");
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
    
    // Inner class to keep track of moves for the undo feature
    private static class Move {
        private int position;
        private char symbol;
        
        public Move(int position, char symbol) {
            this.position = position;
            this.symbol = symbol;
        }
        
        public int getPosition() {
            return position;
        }
        
        public char getSymbol() {
            return symbol;
        }
    }
}