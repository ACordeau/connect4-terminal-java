package main.java.core;

public class Connect4Logic {

    private Player playerX;
    private Player playerO;
    private char currentPlayer;
    private char[][] gameBoard;

    public Connect4Logic(Player playerX, Player playerO) {
        setCurrentPlayer('X');
        this.playerX = playerX;
        this.playerO = playerO;
        createBlankBoard(new char[6][7]);
    }

    private void setCurrentPlayer(char currPlayer) {
        this.currentPlayer = currPlayer;
    }

    public void createBlankBoard(char[][] gameBoard) {

        for (int row = 0; row < gameBoard.length; row++) {

            for (int col = 0; col < gameBoard[0].length; col++) {

                gameBoard[row][col] = ' ';

            }

        }
    }

}
