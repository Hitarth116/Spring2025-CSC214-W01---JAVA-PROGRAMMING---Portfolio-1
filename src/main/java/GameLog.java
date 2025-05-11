import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameLog {
    private int xWins;
    private int oWins;
    private int ties;
    
    // Map to store player-specific statistics: player name -> [wins, losses, ties]
    private Map<String, int[]> playerStats = new HashMap<>();
    
    public void incrementWins(char winner) {
        if (winner == 'X') {
            xWins++;
        } else if (winner == 'O') {
            oWins++;
        } else {
            ties++;
        }
    }
    
    // Record a win for a specific player
    public void recordPlayerWin(String playerName) {
        if (playerName == null || playerName.equals("Computer")) {
            return; // Don't track computer stats
        }
        
        updatePlayerStats(playerName);
        
        // Increment wins for this player
        playerStats.get(playerName)[0]++;
    }
    
    // Update player stats when they play a game
    public void updatePlayerStats(String playerName) {
        if (playerName == null || playerName.equals("Computer")) {
            return; // Don't track computer stats
        }
        
        // Create entry for new player if they don't exist
        if (!playerStats.containsKey(playerName)) {
            playerStats.put(playerName, new int[]{0, 0, 0});
        }
    }
    
    // Called after a game to update loss/tie stats for players
    public void updateGameResult(String winnerName, String player1, String player2) {
        if (winnerName == null) {
            // It's a tie, update both players' tie stats
            if (playerStats.containsKey(player1)) {
                playerStats.get(player1)[2]++;
            }
            if (playerStats.containsKey(player2)) {
                playerStats.get(player2)[2]++;
            }
        } else {
            // Update loss stat for the losing player
            String losingPlayer;
            if (player1.equals(winnerName)) {
                losingPlayer = player2;
            } else {
                losingPlayer = player1;
            }
            
            if (playerStats.containsKey(losingPlayer)) {
                playerStats.get(losingPlayer)[1]++;
            }
        }
    }
    
    public void printLog() {
        System.out.println("\nThe current session log is:");
        System.out.println("Player X Wins   " + xWins);
        System.out.println("Player O Wins   " + oWins);
        System.out.println("Ties            " + ties);
    }
    
    public void printPlayerStats() {
        if (playerStats.isEmpty()) {
            return;
        }
        
        System.out.println("\nPlayer Statistics:");
        System.out.println("---------------------------------");
        System.out.printf("%-15s %-7s %-7s %-7s%n", "Player", "Wins", "Losses", "Ties");
        System.out.println("---------------------------------");
        
        for (Map.Entry<String, int[]> entry : playerStats.entrySet()) {
            String playerName = entry.getKey();
            int[] stats = entry.getValue();
            System.out.printf("%-15s %-7d %-7d %-7d%n", playerName, stats[0], stats[1], stats[2]);
        }
        System.out.println("---------------------------------");
    }
    
    public void saveLog() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Player X Wins   " + xWins + "\n");
            writer.write("Player O Wins   " + oWins + "\n");
            writer.write("Ties            " + ties + "\n");
            
            if (!playerStats.isEmpty()) {
                writer.write("\nPlayer Statistics:\n");
                writer.write("---------------------------------\n");
                writer.write(String.format("%-15s %-7s %-7s %-7s%n", "Player", "Wins", "Losses", "Ties"));
                writer.write("---------------------------------\n");
                
                for (Map.Entry<String, int[]> entry : playerStats.entrySet()) {
                    String playerName = entry.getKey();
                    int[] stats = entry.getValue();
                    writer.write(String.format("%-15s %-7d %-7d %-7d%n", playerName, stats[0], stats[1], stats[2]));
                }
                writer.write("---------------------------------\n");
            }
            
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
    
    // Get a specific player's win count
    public int getPlayerWins(String playerName) {
        if (playerStats.containsKey(playerName)) {
            return playerStats.get(playerName)[0];
        }
        return 0;
    }
    
    // Get a specific player's loss count
    public int getPlayerLosses(String playerName) {
        if (playerStats.containsKey(playerName)) {
            return playerStats.get(playerName)[1];
        }
        return 0;
    }
    
    // Get a specific player's tie count
    public int getPlayerTies(String playerName) {
        if (playerStats.containsKey(playerName)) {
            return playerStats.get(playerName)[2];
        }
        return 0;
    }
}