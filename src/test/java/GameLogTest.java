import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameLogTest {
    private GameLog gameLog;

    @BeforeEach
    void setUp() {
        gameLog = new GameLog();
    }

    @Test
    void testIncrementWins() {
        // Test incrementing wins
        gameLog.incrementWins('X');
        gameLog.incrementWins('O');
        gameLog.incrementWins('D'); // Test a draw
        gameLog.incrementWins('X');

        // Verify the count
        assertEquals(2, gameLog.getXWins());
        assertEquals(1, gameLog.getOWins());
        assertEquals(1, gameLog.getTies());
    }

    @Test
    void testPrintLog() {
        // Test if the log prints the correct statistics
        gameLog.incrementWins('X');
        gameLog.incrementWins('O');
        gameLog.incrementWins('D');
        gameLog.printLog(); // This will print to the console
    }

    @Test
    void testSaveLog() {
        // Test the file writing process, we just check if no exceptions are thrown
        gameLog.incrementWins('X');
        gameLog.saveLog(); // This will write to "game.txt"
    }
}
