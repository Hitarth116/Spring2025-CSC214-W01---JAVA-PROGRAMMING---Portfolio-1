import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    private int xWins;
    private int oWins;
    private int ties;

    public void incrementWins(char winner) {
        if (winner == 'X') {
            xWins++;
        } else if (winner == 'O') {
            oWins++;
        } else {
            ties++;
        }
    }


    
    public void printLog() {
        System.out.println("\nThe current log is:");
        System.out.println("Player X Wins   " + xWins);
        System.out.println("Player O Wins   " + oWins);
        System.out.println("Ties            " + ties);
    }

    public void saveLog() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Player X Wins   " + xWins + "\n");
            writer.write("Player O Wins   " + oWins + "\n");
            writer.write("Ties            " + ties + "\n");
            System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public int getXWins() {
        return xWins;
    }
    
    public int getOWins() {
        return oWins;
    }
    
    public int getTies() {
        return ties;
    }
}
