import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {
    
    private GameLog gameLog;
    
    @BeforeEach
    public void setUp() {
        gameLog = new GameLog();
    }
    
    @Test
    public void testPlayerStatTracking() {
        // Add a player and record a win
        gameLog.updatePlayerStats("Alice");
        gameLog.recordPlayerWin("Alice");
        
        assertEquals(1, gameLog.getPlayerWins("Alice"));
        assertEquals(0, gameLog.getPlayerLosses("Alice"));
        assertEquals(0, gameLog.getPlayerTies("Alice"));
    }
    
    @Test
    public void testMultiplePlayerStats() {
        // Track multiple players
        gameLog.updatePlayerStats("Alice");
        gameLog.updatePlayerStats("Bob");
        
        // Record some game results
        gameLog.recordPlayerWin("Alice");
        gameLog.updateGameResult("Alice", "Alice", "Bob");
        
        // Alice should have 1 win, 0 losses
        // Bob should have 0 wins, 1 loss
        assertEquals(1, gameLog.getPlayerWins("Alice"));
        assertEquals(0, gameLog.getPlayerLosses("Alice"));
        assertEquals(0, gameLog.getPlayerTies("Alice"));
        
        assertEquals(0, gameLog.getPlayerWins("Bob"));
        assertEquals(1, gameLog.getPlayerLosses("Bob"));
        assertEquals(0, gameLog.getPlayerTies("Bob"));
    }
    
    @Test
    public void testTieGameStats() {
        // Add players
        gameLog.updatePlayerStats("Alice");
        gameLog.updatePlayerStats("Bob");
        
        // Record a tie
        gameLog.updateGameResult(null, "Alice", "Bob");
        
        // Both should have 0 wins, 0 losses, 1 tie
        assertEquals(0, gameLog.getPlayerWins("Alice"));
        assertEquals(0, gameLog.getPlayerLosses("Alice"));
        assertEquals(1, gameLog.getPlayerTies("Alice"));
        
        assertEquals(0, gameLog.getPlayerWins("Bob"));
        assertEquals(0, gameLog.getPlayerLosses("Bob"));
        assertEquals(1, gameLog.getPlayerTies("Bob"));
    }
    
    @Test
    public void testComputerStatsNotTracked() {
        // Computer stats should not be tracked
        gameLog.updatePlayerStats("Computer");
        gameLog.recordPlayerWin("Computer");
        
        assertEquals(0, gameLog.getPlayerWins("Computer"));
    }
    
    @Test
    public void testOverallGameTotals() {
        // Test the overall game totals
        gameLog.incrementWins('X');
        gameLog.incrementWins('X');
        gameLog.incrementWins('O');
        gameLog.incrementWins('D');
        
        assertEquals(2, gameLog.getXWins());
        assertEquals(1, gameLog.getOWins());
        assertEquals(1, gameLog.getTies());
    }
}