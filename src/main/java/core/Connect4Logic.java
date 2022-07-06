package main.java.core;

public class Connect4Logic {

    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private char[][] gameBoard;

    public Connect4Logic(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        currentPlayerStart(playerX);
        this.gameBoard = new char[6][7];
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

        setCurrentPlayer(currentPlayer);
    }

    public boolean outOfPieces(Player px, Player po) {

        if (px.getPieces() == 0 && po.getPieces() == 0) {
            return true;
        }

        return false;
    }

}
