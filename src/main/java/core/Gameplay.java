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
        System.out.println("Entered PVP");
        Connect4Logic game = new Connect4Logic(playerX, playerO);
        scan = new Scanner(System.in);
        boolean valid = false;
        int choice = -1;

        game.createBlankBoard(game.getGameBoard());

        while (!game.outOfPieces(playerX, playerO)) {
            do {
                game.display(game.getGameBoard());

                choice = Utils.getInput(scan);
                valid = Utils.valid(choice, game.getGameBoard());

            } while (!valid);

            game.placePiece(game.getGameBoard(), game.getCurrentPlayer(), choice);

        }

        scan.close();

    }

}
