import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private Player playerX;
    private Player playerO;

    @BeforeEach
    void setUp() {
        // Provide names for players when creating Player objects
        playerX = new Player('X', "Alice");  // 'X' and the player's name
        playerO = new Player('O', "Bob");    // 'O' and the player's name
    }

    @Test
    void testGetSymbol() {
        // Test that each player has the correct symbol
        assertEquals('X', playerX.getSymbol());
        assertEquals('O', playerO.getSymbol());
    }

    @Test
    void testGetName() {
        // Test that each player has the correct name
        assertEquals("Alice", playerX.getName());
        assertEquals("Bob", playerO.getName());
    }
}
