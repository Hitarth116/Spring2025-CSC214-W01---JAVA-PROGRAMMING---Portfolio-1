import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ComputerPlayerTest {
    
    private Board board;
    private ComputerPlayer computerX;
    private ComputerPlayer computerO;
    
    @BeforeEach
    public void setUp() {
        board = new Board();
        computerX = new ComputerPlayer('X', 3); // Hard difficulty
        computerO = new ComputerPlayer('O', 3); // Hard difficulty
    }
    
    @Test
    public void testComputerPlayerCreation() {
        assertEquals('X', computerX.getSymbol());
        assertEquals("Computer", computerX.getName());
        
        assertEquals('O', computerO.getSymbol());
        assertEquals("Computer", computerO.getName());
    }
    
    @Test
    public void testFirstMoveIsCorner() {
        // Empty board, first move should be a corner
        int move = computerX.makeMove(board);
        
        // Corners are at indices 0, 2, 6, 8
        Set<Integer> corners = new HashSet<>(Arrays.asList(0, 2, 6, 8));
        assertTrue(corners.contains(move), "First move should be a corner (0, 2, 6, or 8), but was: " + move);
    }
    
    @Test
    public void testSecondMoveIsCenter() {
        // Set up a board with just one move made
        board.makeMove(0, 'X');
        
        // Second move should be the center (index 4)
        int move = computerO.makeMove(board);
        assertEquals(4, move, "Second move should be the center (4)");
    }
    
    @Test
    public void testComputerTakesWinningMove() {
        // Set up a board where X can win in the next move
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        
        // Computer X should take position 2 to win
        int move = computerX.makeMove(board);
        assertEquals(2, move, "Computer should take winning move");
    }
    
    @Test
    public void testComputerBlocksOpponentWin() {
        // Set up a board where O is about to win
        board.makeMove(0, 'O');
        board.makeMove(1, 'O');
        
        // Computer X should block at position 2
        int move = computerX.makeMove(board);
        assertEquals(2, move, "Computer should block opponent's winning move");
    }
    
    @Test
    public void testComputerPrioritizesWinningOverBlocking() {
        // Set up a board where both X and O can win in different positions
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(3, 'O');
        board.makeMove(6, 'O');
        
        // Computer X should prioritize winning (position 2) over blocking (position 9)
        int move = computerX.makeMove(board);
        assertEquals(2, move, "Computer should prioritize winning over blocking");
    }
    
    @Test
    public void testRandomMoveWhenNoStrategicOption() {
        // Set up a board with no immediate winning or blocking moves
        board.makeMove(0, 'X');
        board.makeMove(8, 'O');
        
        // Computer's move should be one of the remaining positions
        int move = computerX.makeMove(board);
        
        Set<Integer> validMoves = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertTrue(validMoves.contains(move), "Move should be one of the remaining positions");
    }
    
    @Test
    public void testHardComputerCreatesFork() {
        // Set up a position where a fork can be created
        board.makeMove(0, 'X'); // X in top-left
        board.makeMove(4, 'O'); // O in center
        board.makeMove(8, 'X'); // X in bottom-right
        board.makeMove(1, 'O'); // O in top-middle
        
        // Hard computer playing as X should create a fork
        int move = computerX.makeMove(board);
        
        // Fork can be created by playing at position 6 (bottom-left)
        assertEquals(6, move, "Computer should create a fork");
    }
}