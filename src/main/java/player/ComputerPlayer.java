package main.java.player;

import java.util.Random;

import main.java.core.Connect4Logic;
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
        Utils.createMessage("Computer Turn...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int tryToWin = checkBoard(gameBoard, O, X, 3);
        if (tryToWin != -2 && Utils.valid(tryToWin, gameBoard, this)) {
            return tryToWin;
        }

        int thiar = checkBoard(gameBoard, X, O, 3);
        if (thiar != -2 && Utils.valid(thiar, gameBoard, this)) {
            return thiar;
        }

        int twiar = checkBoard(gameBoard, X, O, 2);
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
    private int computerRandomChoice(char[][] gameBoard) {

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
    private int computerRandomChoice(char[][] gameBoard, int except) {
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
     * Helper method to check to see if there are 3 pieces in a row in any
     * direction.
     * 
     * @param gameBoard Current state of the game
     * @param x         Integer for the X coordinate on the gameBoard
     * @param y         Integer for the Y coordinate on the gameBoard
     * @param d         Integer array that holds the directional coordinates
     *                  (specifically used for diagonals)
     * @return Integer to tell the AI where their piece should be placed
     */
    private int threeInARow(char[][] gameBoard, int x, int y, int[] d) {
        if (((d[0] == 1 && d[1] == 1) || (d[0] == 1 && d[1] == -1) && gameBoard[x + 1][y] == ' ')) {
            return -2;
        } else if (x < 5 && gameBoard[x + 1][y] == ' ') {
            return computerRandomChoice(gameBoard, y);
        } else {
            return y;
        }
    }

    /**
     * Helper method to check to see if the opponent has two pieces in a row in any
     * direction.
     * 
     * @param gameBoard Current state of the game
     * @param x         Integer for the X coordinate on the gameBoard
     * @param y         Integer for the Y coordinate on the gameBoard
     * @param d         Integer array that holds the directional coordinates
     *                  (specifically used for diagonals)
     * @return Integer to tell the AI where their piece should be placed
     */
    private int twoInARow(char[][] gameBoard, int x, int y, int[] d) {
        if (((d[0] == 1 && d[1] == 1) || (d[0] == 1 && d[1] == -1) && gameBoard[x + 1][y] == ' ')) {
            return -2;
        } else {
            return y;
        }
    }

    /**
     * The AI checks the board to see what the best play is based on the given
     * paramenters.
     * 
     * @param gameBoard     Current state of the game
     * @param playerPiece   The piece the AI is checking for
     * @param opposingPiece The piece the AI is checking against
     * @param inARow        Integer to tell the AI how many in a row it needs to
     *                      check for
     * @return Integer to tell the AI where their piece should be placed
     */
    private int checkBoard(char[][] gameBoard, char playerPiece, char opposingPiece, int inARow) {
        for (int[] d : DIRECTIONS) {
            int dx = d[0];
            int dy = d[1];

            for (int x = 0; x < ROWS; x++) {
                for (int y = 0; y < COLS; y++) {

                    int lastx = x + inARow * dx;
                    int lasty = y + inARow * dy;

                    if (0 <= lastx && lastx < ROWS && 0 <= lasty && lasty < COLS) {
                        if (playerPiece == Utils.getPiece(gameBoard, lastx, lasty)) {
                            if (inARow == 3 && playerPiece == gameBoard[x + dx][y + dy]
                                    && playerPiece == gameBoard[x + 2 * dx][y + 2 * dy]
                                    && playerPiece == gameBoard[lastx][lasty] && gameBoard[x][y] != opposingPiece) {

                                return threeInARow(gameBoard, x, y, d);

                            }
                            if (inARow == 2 && playerPiece == gameBoard[x + dx][y + dy]
                                    && playerPiece == gameBoard[lastx][lasty] && gameBoard[x][y] != opposingPiece) {

                                return twoInARow(gameBoard, x, y, d);

                            }
                        }

                    }
                }
            }
        }
        return -2;
    }

}
