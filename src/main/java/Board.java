public class Board {
    private char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    public char[] getBoard() {
        return board;
    }
    
    public void printBoard() {
        System.out.println();
        System.out.println("  " + board[0] + "  |  " + board[1] + "  |  " + board[2]);
        System.out.println(" -----+-----+-----");
        System.out.println("  " + board[3] + "  |  " + board[4] + "  |  " + board[5]);
        System.out.println(" -----+-----+-----");
        System.out.println("  " + board[6] + "  |  " + board[7] + "  |  " + board[8]);
    }
    
    public boolean isCellOccupied(int index) {
        return board[index] == 'X' || board[index] == 'O';
    }
    
    public void makeMove(int index, char player) {
        board[index] = player;
    }
    
    // New method to support the undo feature
    public void undoMove(int index) {
        // Reset the cell to its original value (1-9)
        board[index] = (char)('1' + index);
    }
    
    // Checks for a winner and returns the winning combination of indices
    public int[] checkWinner(char player) {
        int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };
        
        for (int[] combo : winningPositions) {
            if (board[combo[0]] == player && board[combo[1]] == player && board[combo[2]] == player) {
                return combo;  // Return the winning combination of indices
            }
        }
        
        return null;  // No winner
    }
    
    // Highlight the winning line by changing the winning cells to uppercase
    public void highlightWinningLine(int[] winningCombo) {
        for (int index : winningCombo) {
            // Change the board cells (e.g., make them uppercase to highlight the winning cells)
            board[index] = Character.toUpperCase(board[index]);
        }
        printBoard();  // Print the board after highlighting the winning line
    }
    
    public boolean isFull() {
        for (char cell : board) {
            if (cell != 'X' && cell != 'O') {
                return false;
            }
        }
        return true;
    }
    
    // Reset the board to its initial state
    public void reset() {
        for (int i = 0; i < board.length; i++) {
            board[i] = (char) ('1' + i);
        }
    }
}