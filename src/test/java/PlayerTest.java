import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    @Test
    public void testPlayerCreation() {
        Player player = new Player('X', "John");
        
        assertEquals('X', player.getSymbol());
        assertEquals("John", player.getName());
    }
    
    @Test
    public void testMultiplePlayers() {
        Player playerX = new Player('X', "Alice");
        Player playerO = new Player('O', "Bob");
        
        assertEquals('X', playerX.getSymbol());
        assertEquals("Alice", playerX.getName());
        
        assertEquals('O', playerO.getSymbol());
        assertEquals("Bob", playerO.getName());
    }
}