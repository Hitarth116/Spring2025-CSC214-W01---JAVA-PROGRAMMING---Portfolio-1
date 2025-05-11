import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TicTacToeTest {
    
    @Test
    public void testSelectFirstPlayerSymbol() {
        // Test that 'X' is accepted
        char symbol = simulateSymbolSelection("X\n");
        assertEquals('X', symbol);
        
        // Test that 'O' is accepted
        symbol = simulateSymbolSelection("O\n");
        assertEquals('O', symbol);
        
        // Test that lowercase is converted to uppercase
        symbol = simulateSymbolSelection("x\n");
        assertEquals('X', symbol);
        
        // Test invalid input followed by valid input
        symbol = simulateSymbolSelection("invalid\nX\n");
        assertEquals('X', symbol);
    }
    
    @Test
    public void testSelectDifficultyLevel() {
        // Test difficulty level 1 selection
        int level = simulateDifficultySelection("1\n");
        assertEquals(1, level);
        
        // Test difficulty level 2 selection
        level = simulateDifficultySelection("2\n");
        assertEquals(2, level);
        
        // Test difficulty level 3 selection
        level = simulateDifficultySelection("3\n");
        assertEquals(3, level);
        
        // Test invalid input followed by valid input
        level = simulateDifficultySelection("invalid\n4\n2\n");
        assertEquals(2, level);
    }
    
    // Helper method to simulate symbol selection
    private char simulateSymbolSelection(String input) {
        // Save the original System.in
        java.io.InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        
        try {
            // Redirect System.in with our simulated input
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            
            // Redirect System.out to ignore the output
            System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
            // Call the method with a new Scanner
            return TicTacToe.selectFirstPlayerSymbol(new java.util.Scanner(System.in));
        } finally {
            // Restore the original System.in and System.out
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
    
    // Helper method to simulate difficulty selection
    private int simulateDifficultySelection(String input) {
        // Save the original System.in and System.out
        java.io.InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        
        try {
            // Redirect System.in with our simulated input
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            
            // Redirect System.out to ignore the output
            System.setOut(new PrintStream(new ByteArrayOutputStream()));
            
            // Call the method with a new Scanner
            return TicTacToe.selectDifficultyLevel(new java.util.Scanner(System.in));
        } finally {
            // Restore the original System.in and System.out
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}