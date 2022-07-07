package main.java.core;

import java.util.Scanner;

import main.java.utils.Utils;

public class Gameplay {
    private int gameType;
    private Player playerX;
    private Player playerO;
    private Scanner scan;

    public Gameplay(int gameType, Scanner scan) {
        System.out.println("Gameplay Created");
        this.gameType = gameType;
        playerX = new Player();
        playerO = new Player();
        this.scan = scan;
    }

    public void start() {
        System.out.println("Entered Start");
        switch (gameType) {
        case 1:
            // pve();
            break;
        case 2:
            pvp();
            break;
        default:
            System.out.println("Something went wrong!");
            System.exit(1);
        }
    }

    public void pvp() {
        Connect4Logic game = new Connect4Logic(playerX, playerO);
        Player currentPlayer = null;
        char[][] gameBoard = game.getGameBoard();
        boolean valid = false;
        int choice = -5;
        boolean winner = false;

        game.createBlankBoard(gameBoard);

        while (!game.outOfPieces(playerX, playerO)) {
            do {
                currentPlayer = game.getCurrentPlayer();
                game.display(gameBoard);

                choice = Utils.getInput(scan);
                if (choice == -1) {
                    game.gameQuit(currentPlayer);
                    return;
                }
                valid = Utils.valid(choice, gameBoard);

            } while (!valid);

            game.placePiece(gameBoard, currentPlayer, choice);
            gameBoard = game.getGameBoard();
            winner = game.winner(gameBoard, currentPlayer);
            if (winner) {
                break;
            }
            game.setCurrentPlayer(currentPlayer);

        }
        game.display(gameBoard);
        game.announceWinner(winner, currentPlayer);

    }

}
