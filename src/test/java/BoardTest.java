import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    
    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    
    @Test
    public void testUndoMove() {
        // Make a move
        board.makeMove(4, 'X');
        assertTrue(board.isCellOccupied(4));
        
        // Undo the move
        board.undoMove(4);
        assertFalse(board.isCellOccupied(4));
        
        // Check that the cell was reset to its original value
        assertEquals('5', board.getBoard()[4]);
    }
    
    @Test
    public void testMultipleMovesAndUndo() {
        // Make several moves
        board.makeMove(0, 'X');
        board.makeMove(4, 'O');
        board.makeMove(8, 'X');
        
        // Undo last move
        board.undoMove(8);
        
        // Check board state
        char[] boardState = board.getBoard();
        assertEquals('X', boardState[0]);
        assertEquals('O', boardState[4]);
        assertEquals('9', boardState[8]);
    }
}