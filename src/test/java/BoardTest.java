import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testBoardInitialization() {
        // Test initial board state
        char[] expectedBoard = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        assertArrayEquals(expectedBoard, board.getBoard());
    }

    @Test
    void testMakeMove() {
        // Test making a move on an empty cell
        board.makeMove(0, 'X');
        assertTrue(board.isCellOccupied(0));
        assertEquals('X', board.getBoard()[0]);
    }

    @Test
    void testCheckWinner() {
        // Test for winner on a row
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        int[] winningCombo = board.checkWinner('X');
        assertNotNull(winningCombo);  // There should be a winning combination
        assertArrayEquals(new int[]{0, 1, 2}, winningCombo);

        // Test for no winner
        Board newBoard = new Board();
        newBoard.makeMove(0, 'X');
        newBoard.makeMove(1, 'O');
        newBoard.makeMove(2, 'X');
        assertNull(newBoard.checkWinner('X'));  // No winner
    }

    @Test
    void testBoardFull() {
        // Test if the board is full
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X');
        board.makeMove(7, 'O');
        board.makeMove(8, 'X');
        assertTrue(board.isFull());
    }
}
