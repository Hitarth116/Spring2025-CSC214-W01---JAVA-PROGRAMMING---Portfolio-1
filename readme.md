# Tic-Tac-Toe Game

A Java implementation of the classic Tic-Tac-Toe game with enhanced features and customizability.

## Features

### Core Gameplay
- Play the traditional 3x3 Tic-Tac-Toe game
- Multiple game modes:
  - Human vs Human
  - Human vs Computer
  - Computer vs Human
- Highlighted winning combinations
- Game statistics tracking
- Save game logs to a file

### New Enhancements
- **Difficulty Levels**: Choose between Easy, Medium, and Hard computer AI
  - **Easy**: Makes occasional random moves and misses some blocking opportunities
  - **Medium**: Makes strategic moves with a focus on center and corner positions
  - **Hard**: Implements advanced strategies including fork detection and tactical play
- **Symbol Selection**: Players can now choose to play as X or O
- **Undo Move Feature**: Human players can undo their last move by typing 'u'
- **Computer "Thinking"**: Added delay simulation that scales with difficulty level
- **Enhanced Game Statistics**: Tracks per-player statistics (wins, losses, ties)
- **Improved AI Logic**: Advanced computer player with strategic decision making

## Technical Improvements
- **Modular Code Structure**: Better organized codebase with clear separation of concerns
- **Comprehensive Test Coverage**: Unit tests for all key functionality
- **Gradle Build System**: Modern build configuration with dependency management
- **Code Quality Tools**: Integration with Checkstyle and JaCoCo for quality assurance
- **Enhanced Documentation**: Clear code comments and improved documentation

## Getting Started

### Prerequisites
- Java 11 or higher
- Gradle 7.0 or higher

### Building the Application
```bash
gradle clean build
```

### Running the Application
```bash
gradle run
```

## Game Instructions

1. Launch the game using `gradle run`
2. Select a game mode (Human vs Human, Human vs Computer, Computer vs Human)
3. If playing against a computer, select the difficulty level
4. Choose whether the first player will be X or O
5. Enter player names when prompted
6. Make moves by entering a number 1-9 corresponding to the board position
7. To undo a move, type 'u' when prompted for your move
8. After the game ends, you can choose to play again or exit
9. Game statistics will be saved to "game.txt" when you exit

## Board Layout
The board positions are numbered 1-9 as follows:
```
  1  |  2  |  3
-----+-----+-----
  4  |  5  |  6
-----+-----+-----
  7  |  8  |  9
```

## Project Structure
- `TicTacToe.java`: Main game controller with game loop logic
- `Board.java`: Represents the game board and its state
- `Player.java`: Base class for all players
- `ComputerPlayer.java`: AI player with difficulty-based strategies
- `GameLog.java`: Handles statistics tracking and persistence
- Test classes for unit testing each component

## Testing
Run the tests using:
```bash
gradle test
```

A test report will be generated at `build/reports/tests/test/index.html`

## Future Enhancements
- Graphical user interface
- Network play support
- Custom board sizes
- Saving and loading games
- More sophisticated AI algorithms
- Player profiles and persistent statistics

