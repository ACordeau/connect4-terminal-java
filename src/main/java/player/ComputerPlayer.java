package main.java.player;

import java.util.Random;

import main.java.utils.Utils;

public class ComputerPlayer extends Player {
    private int pieces;

    public ComputerPlayer() {
        super();
    }

    public int computerTurn(char[][] gameBoard) {
        int thiar = threeInARow(gameBoard);
        int twiar = twoInARow(gameBoard);
        int tryToWin = tryToWin(gameBoard);

        if (tryToWin != -2 || Utils.valid(tryToWin, gameBoard)) {
            return tryToWin;
        }

        if (thiar != -2 || Utils.valid(thiar, gameBoard)) {
            return thiar;
        }

        if (twiar != -2 || Utils.valid(twiar, gameBoard)) {
            if (computerRandomChoice(gameBoard) % 2 != 0) {
                return twiar;
            }

        }

        return computerRandomChoice(gameBoard);
    }

    public int computerRandomChoice(char[][] gameBoard) {

        Random rand = new Random();
        int random;

        do {
            random = rand.nextInt(7 - 0) + 0;
        } while (Utils.columnFull(random, gameBoard));

        return random;

    }

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

    public int tryToWin(char[][] gameBoard) {
        char piece = O;

        int[][] directions = { { 1, 0 }, { 1, -1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 } };
        for (int[] d : directions) {
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

                                System.out.println("X: " + x + "\tY: " + y);
                                System.out.println("X+1: " + (x + 1) + "\tY+1: " + (y + 1));

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

    public int twoInARow(char[][] gameBoard) {

        char piece = X;

        int[][] directions = { { 1, 0 }, { 1, -1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 } };
        for (int[] d : directions) {
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

    public int threeInARow(char[][] gameBoard) {

        char piece = X;

        int[][] directions = { { 1, 0 }, { 1, -1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 } };
        for (int[] d : directions) {
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

                                System.out.println("X: " + x + "\tY: " + y);
                                System.out.println("X+1: " + (x + 1) + "\tY+1: " + (y + 1));

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
