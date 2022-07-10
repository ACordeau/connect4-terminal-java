package main.java.player;

import java.util.Random;

import main.java.utils.Utils;

/**
 * Description: Connect4 Computer player. This class extends the Player class,
 * and creates logic for how the computer player will play the game.
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */
public class ComputerPlayer extends Player {

    /**
     * Default ComputerPlayer constructor which calls the Superclass Player
     */
    public ComputerPlayer() {
        super();
    }

    /**
     * The entry point of the computer turn, all logic is called from this hub.
     * 
     * @param gameBoard current state of the game
     * @return choice for the computer to place its piece
     */
    public int computerTurn(char[][] gameBoard) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int tryToWin = tryToWin(gameBoard);
        if (tryToWin != -2 && Utils.valid(tryToWin, gameBoard, this)) {
            return tryToWin;
        }

        int thiar = threeInARow(gameBoard);
        if (thiar != -2 && Utils.valid(thiar, gameBoard, this)) {
            return thiar;
        }

        int twiar = twoInARow(gameBoard);
        if (twiar != -2 && Utils.valid(twiar, gameBoard, this)) {
            if (Utils.randomNumber() % 2 != 0) {
                return twiar;
            }

        }

        return computerRandomChoice(gameBoard);
    }

    /**
     * Determines a piece for the AI to play randomly. Called only if the AI has no
     * better options.
     * 
     * @param gameBoard current state of the game
     * @return choice for the computer to place its piece
     */
    public int computerRandomChoice(char[][] gameBoard) {

        Random rand = new Random();
        int random;

        do {
            random = rand.nextInt(7 - 0) + 0;
        } while (Utils.columnFull(random, gameBoard));

        return random;

    }

    /**
     * Determines a piece for the AI to play randomly, avoids the given choice if
     * possible. Called if placing a piece in the 'except' slot will allow the
     * Player to win.
     * 
     * @param gameBoard current state of the game
     * @return choice for the computer to place its piece
     */
    public int computerRandomChoice(char[][] gameBoard, int except) {
        Random rand = new Random();
        int random;
        int amount = 0;

        do {
            random = rand.nextInt(7 - 0) + 0;
            amount++;
            if (amount == 10) {
                return except;
            }
        } while (Utils.columnFull(random, gameBoard));

        return random;

    }

    /**
     * The AI checks to see if it has 3 pieces in a row, and will prioritize winning
     * the game over stopping the Player from winning.
     * 
     * @param gameBoard current state of the game
     * @return choice for the computer to place its piece
     */
    public int tryToWin(char[][] gameBoard) {
        char piece = O;

        for (int[] d : DIRECTIONS) {
            int dx = d[0];
            int dy = d[1];

            for (int x = 0; x < ROWS; x++) {
                for (int y = 0; y < COLS; y++) {

                    int lastx = x + 3 * dx;
                    int lasty = y + 3 * dy;

                    if (piece == O) {

                        if (0 <= lastx && lastx < ROWS && 0 <= lasty && lasty < COLS) {
                            if (piece != ' ' && piece == gameBoard[x + dx][y + dy]
                                    && piece == gameBoard[x + 2 * dx][y + 2 * dy] && piece == gameBoard[lastx][lasty]
                                    && gameBoard[x][y] != X) {

                                if (((d[0] == 1 && d[1] == 1)
                                        || (d[0] == 1 && d[1] == -1) && gameBoard[x + 1][y] == ' ')) {
                                    return -2;
                                } else if (x < 5 && gameBoard[x + 1][y] == ' ') {
                                    return computerRandomChoice(gameBoard, y);
                                } else {
                                    return y;
                                }
                            }
                        }

                    }
                }
            }
        }
        return -2;
    }

    /**
     * The AI checks to see if the player has 2 pieces in a row. If it does, it has
     * a 50% chance to either try and block the Player's piece, or place a random
     * piece.
     * 
     * @param gameBoard current state of the game
     * @return choice for the computer to place its piece
     */
    public int twoInARow(char[][] gameBoard) {
        char piece = X;

        for (int[] d : DIRECTIONS) {
            int dx = d[0];
            int dy = d[1];

            for (int x = 0; x < ROWS; x++) {
                for (int y = 0; y < COLS; y++) {

                    int lastx = x + 2 * dx;
                    int lasty = y + 2 * dy;

                    if (piece == X) {

                        if (0 <= lastx && lastx < ROWS && 0 <= lasty && lasty < COLS) {
                            if (piece != ' ' && piece == gameBoard[x + dx][y + dy] && piece == gameBoard[lastx][lasty]
                                    && gameBoard[x][y] != O) {

                                if (((d[0] == 1 && d[1] == 1)
                                        || (d[0] == 1 && d[1] == -1) && gameBoard[x + 1][y] == ' ')) {
                                    return -2;
                                } else {
                                    return y;
                                }
                            }
                        }

                    }
                }
            }
        }
        return -2;
    }

    /**
     * The AI checks to see if the Player has 3 pieces in a row, if they do, it will
     * try to block them from winning.
     * 
     * @param gameBoard current state of the game
     * @return choice for the computer to place its piece
     */
    public int threeInARow(char[][] gameBoard) {
        char piece = X;

        for (int[] d : DIRECTIONS) {
            int dx = d[0];
            int dy = d[1];

            for (int x = 0; x < ROWS; x++) {
                for (int y = 0; y < COLS; y++) {

                    int lastx = x + 3 * dx;
                    int lasty = y + 3 * dy;

                    if (piece == X) {

                        if (0 <= lastx && lastx < ROWS && 0 <= lasty && lasty < COLS) {
                            if (piece != ' ' && piece == gameBoard[x + dx][y + dy]
                                    && piece == gameBoard[x + 2 * dx][y + 2 * dy] && piece == gameBoard[lastx][lasty]
                                    && gameBoard[x][y] != O) {

                                if (((d[0] == 1 && d[1] == 1)
                                        || (d[0] == 1 && d[1] == -1) && gameBoard[x + 1][y] == ' ')) {
                                    return -2;
                                } else if (x < 5 && gameBoard[x + 1][y] == ' ') {
                                    return computerRandomChoice(gameBoard, y);
                                } else {
                                    return y;
                                }
                            }
                        }

                    }
                }
            }
        }
        return -2;
    }

}
