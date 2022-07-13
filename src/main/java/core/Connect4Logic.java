package main.java.core;

import main.java.player.Player;
import main.java.utils.Connect4Constants;
import main.java.utils.Utils;

/**
 * Description: Connect4 game logic. Determines the rules of a game of Connect
 * 4.
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */
public class Connect4Logic implements Connect4Constants {
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private char[][] gameBoard;

    /**
     * Creates an instance of the Connect4Logic object
     */
    public Connect4Logic(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        currentPlayerStart(playerX);
        this.gameBoard = new char[ROWS][COLS];
    }

    /**
     * Initializes current player
     * 
     * @param currPlayer The current turn player
     */
    private void currentPlayerStart(Player currPlayer) {
        this.currentPlayer = currPlayer;
    }

    /**
     * Creates a fresh game board
     * 
     * @param gameBoard At the start of a new game, gives us a blank game board
     */
    public void createBlankBoard(char[][] gameBoard) {

        for (int row = 0; row < gameBoard.length; row++) {

            for (int col = 0; col < gameBoard[0].length; col++) {

                gameBoard[row][col] = ' ';

            }

        }
    }

    /**
     * Returns the current player
     * 
     * @return currentPlayer The player whos turn it currently is
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the token for the current player
     * 
     * @param currentPlayer The current turn player
     * @return Character token.
     */
    public char determinePlayerPiece(Player currentPlayer) {

        if (currentPlayer.equals(playerX)) {
            return X;
        } else {
            return O;
        }

    }

    /**
     * Sets the new current player based on the current turn player.
     * 
     * @param p Current turn player
     */
    public void setCurrentPlayer(Player p) {
        if (currentPlayer.equals(playerX)) {
            currentPlayer = playerO;
        } else {
            currentPlayer = playerX;
        }
    }

    /**
     * Returns the state of the game board
     * 
     * @return gameBoard char array
     */
    public char[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * Draws the gameBoard and populates the squares with the appropriate tokens
     * 
     * @param gameBoard char array
     */
    public void display(char[][] gameBoard) {
        for (int row = 0; row < gameBoard.length; row++) {

            System.out.print(WALL);

            for (int col = 0; col < gameBoard[0].length; col++) {

                System.out.print(gameBoard[row][col]);

                System.out.print(WALL);

            }

            System.out.println();

        }

        System.out.print(" 1 2 3 4 5 6 7");

        System.out.println();
    }

    /**
     * Places the piece of the current player where they have chosen on the game
     * board
     * 
     * @param gameBoard     The current state of the game board
     * @param currentPlayer The current turn player
     * @param choice        The choice the current turn player made
     */
    public void placePiece(char[][] gameBoard, Player currentPlayer, int choice) {

        char playerPiece = determinePlayerPiece(currentPlayer);

        for (int row = gameBoard.length - 1; row >= 0; row--) {
            if (gameBoard[row][choice] == ' ') {
                gameBoard[row][choice] = playerPiece;
                break;
            }
        }

        currentPlayer.decrementPiece();
    }

    /**
     * Determines if both players are out of pieces, resulting in a tie.
     * 
     * @param px PlayerX
     * @param po PlayerO
     * @return boolean to determine if players are out of pieces
     */
    public boolean outOfPieces(Player px, Player po) {

        if (px.getPieces() == 0 && po.getPieces() == 0) {
            return true;
        }

        return false;
    }

    /**
     * Determines who wins if a player quits the game.
     * 
     * @param currentPlayer The current turn player
     */
    public void gameQuit(Player currentPlayer) {
        String winner;
        String current;

        if (currentPlayer.equals(playerX)) {
            current = "X";
            winner = "O";
        } else {
            current = "O";
            winner = "X";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Player ");
        builder.append(current);
        builder.append(" has quit the game. Player ");
        builder.append(winner);
        builder.append(" wins!");

        System.out.println(builder);
    }

    /**
     * Sends a message to the console about the winner of the game. If there is not
     * one, the game results in a tie.
     * 
     * @param winner        A boolean to decide if there was a winner
     * @param currentPlayer The current turn player
     */
    public void announceWinner(boolean winner, Player currentPlayer) {
        if (winner) {
            if (currentPlayer.equals(playerX)) {
                System.out.println("Player X wins!");
                return;
            }

            if (currentPlayer.equals(playerO)) {
                System.out.println("Player O wins!");
                return;
            }
        }

        System.out.println("The game is a tie!");
        return;

    }

    /**
     * The full logic to determine if a player has one. Checks to see if there are 4
     * in a row in any direction. This method only checks the turn players pieces,
     * and ignores empty spaces and opponent pieces. Benchmarked at an average time
     * of under a millisecond.
     * 
     * @param gameBoard     The current state of the game board
     * @param currentPlayer The current turn player
     * @return boolean to decide if there is a winner
     */
    public boolean winner(char[][] gameBoard, Player currentPlayer) {

        char piece = determinePlayerPiece(currentPlayer);

        for (int[] d : DIRECTIONS) {
            int dx = d[0];
            int dy = d[1];

            for (int x = 0; x < ROWS; x++) {
                for (int y = 0; y < COLS; y++) {

                    int lastx = x + 3 * dx;
                    int lasty = y + 3 * dy;

                    if (Utils.getPiece(gameBoard, x, y) == piece) {

                        if (0 <= lastx && lastx < ROWS && 0 <= lasty && lasty < COLS) {
                            if (piece != ' ' && piece == gameBoard[x + dx][y + dy]
                                    && piece == gameBoard[x + 2 * dx][y + 2 * dy]
                                    && piece == gameBoard[x + 3 * dx][y + 3 * dy] && piece == gameBoard[lastx][lasty]) {
                                return true;
                            }
                        }

                    }
                }
            }
        }
        return false;
    }

}
