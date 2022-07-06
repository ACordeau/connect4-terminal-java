package main.java.core;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.utils.Utils;

public class Gameplay {
    private int gameType;
    private Player playerX;
    private Player playerO;
    private Scanner scan;

    public Gameplay(int gameType) {
        System.out.println("Gameplay Created");
        this.gameType = gameType;
        playerX = new Player();
        playerO = new Player();
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
        Player currentPlayer;
        char[][] gameBoard = game.getGameBoard();
        scan = new Scanner(System.in);
        boolean valid = false;
        int choice = -5;

        game.createBlankBoard(gameBoard);

        while (!game.outOfPieces(playerX, playerO)) {
            do {
                currentPlayer = game.getCurrentPlayer();
//                gameBoard = game.getGameBoard();
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

        }

        scan.close();

    }

}
