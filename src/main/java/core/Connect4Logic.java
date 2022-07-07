package main.java.core;

import main.java.utils.Connect4Constants;

public class Connect4Logic implements Connect4Constants {

    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private char[][] gameBoard;

    public Connect4Logic(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        currentPlayerStart(playerX);
        this.gameBoard = new char[ROWS][COLS];
    }

    private void currentPlayerStart(Player currPlayer) {
        this.currentPlayer = currPlayer;
    }

    public void createBlankBoard(char[][] gameBoard) {

        for (int row = 0; row < gameBoard.length; row++) {

            for (int col = 0; col < gameBoard[0].length; col++) {

                gameBoard[row][col] = ' ';

            }

        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public char determinePlayerPiece(Player currentPlayer) {

        if (currentPlayer.equals(playerX)) {
            return 'X';
        } else {
            return 'O';
        }

    }

    public void setCurrentPlayer(Player p) {
        if (currentPlayer.equals(playerX)) {
            currentPlayer = playerO;
        } else {
            currentPlayer = playerX;
        }
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void display(char[][] gameBoard) {
        for (int row = 0; row < gameBoard.length; row++) {

            System.out.print("|");

            for (int col = 0; col < gameBoard[0].length; col++) {

                System.out.print(gameBoard[row][col]);

                System.out.print("|");

            }

            System.out.println();

        }

        System.out.print(" 1 2 3 4 5 6 7");

        System.out.println();
    }

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

    public boolean outOfPieces(Player px, Player po) {

        if (px.getPieces() == 0 && po.getPieces() == 0) {
            return true;
        }

        return false;
    }

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

    public boolean winner(char[][] gameBoard, Player currentPlayer) {

        char piece = determinePlayerPiece(currentPlayer);

        int[][] directions = { { 1, 0 }, { 1, -1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 } };
        for (int[] d : directions) {
            int dx = d[0];
            int dy = d[1];
            for (int x = 0; x < COLS; x++) {
                for (int y = 0; y <= ROWS; y++) {
                    int lastx = x + 4 * dx;
                    int lasty = y + 4 * dy;
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
        return false;
    }

}
