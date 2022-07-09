package main.java.core;

import java.util.Scanner;

import main.java.utils.Utils;
import main.java.player.Player;
import main.java.player.PlayerFactory;
import main.java.player.ComputerPlayer;

public class Gameplay {
    private int gameType;
    private PlayerFactory pf;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private Scanner scan;
    private Connect4Logic game;
    private char[][] gameBoard;
    private int choice;
    private boolean valid;
    private boolean winner;
    private boolean quit;

    public Gameplay(int gameType, Scanner scan) {
        createPlayers(gameType);
        this.gameType = gameType;
        this.scan = scan;
    }

    public void createPlayers(int type) {
        pf = new PlayerFactory();
        playerX = pf.createPlayer('p');
        if (type == 2) {
            playerO = pf.createPlayer('p');
        } else {
            playerO = pf.createPlayer('c');
        }
    }

    public void start() {
        switch (gameType) {
        case 1:
            playtime();
            break;
        case 2:
            playtime();
            break;
        default:
            System.out.println("Something went wrong!");
            System.exit(1);
        }
    }

    private void init() {
        game = new Connect4Logic(playerX, playerO);
        currentPlayer = null;
        gameBoard = game.getGameBoard();
        valid = false;
        winner = false;
        quit = false;
        choice = -5;
        game.createBlankBoard(gameBoard);
    }

    public void showBoard() {
        game.display(gameBoard);
    }

    public void startTurn() {
        currentPlayer = game.getCurrentPlayer();
        showBoard();
    }

    public void playerChoice() {
        if (currentPlayer instanceof ComputerPlayer) {
            choice = ((ComputerPlayer) currentPlayer).computerTurn(gameBoard);
        } else {
            choice = Utils.getInput(scan);
            if (choice == -1) {
                game.gameQuit(currentPlayer);
                quit = true;
                return;
            }
            valid = Utils.valid(choice, gameBoard);
        }
    }

    public void makeMove() {
        game.placePiece(gameBoard, currentPlayer, choice);
        gameBoard = game.getGameBoard();
    }

    public void checkWinner() {
        winner = game.winner(gameBoard, currentPlayer);
    }

    public void playtime() {

        init();

        while (!game.outOfPieces(playerX, playerO) || !quit) {
            do {

                startTurn();

                playerChoice();

            } while (!valid);

            if (!quit) {
                makeMove();
                checkWinner();
            } else {
                break;
            }

            if (winner) {
                break;
            }

            game.setCurrentPlayer(currentPlayer);

        }
        if (!quit) {
            showBoard();
            game.announceWinner(winner, currentPlayer);
        }
    }

//    public void pvp() {
//        game = new Connect4Logic(playerX, playerO);
//        Player currentPlayer = null;
//        char[][] gameBoard = game.getGameBoard();
//        boolean valid = false;
//        int choice = -5;
//        boolean winner = false;
//
//        game.createBlankBoard(gameBoard);
//
//        while (!game.outOfPieces(playerX, playerO)) {
//            do {
//                currentPlayer = game.getCurrentPlayer();
//                game.display(gameBoard);
//
//                choice = Utils.getInput(scan);
//                if (choice == -1) {
//                    game.gameQuit(currentPlayer);
//                    return;
//                }
//                valid = Utils.valid(choice, gameBoard);
//
//            } while (!valid);
//
//            game.placePiece(gameBoard, currentPlayer, choice);
//            gameBoard = game.getGameBoard();
//            winner = game.winner(gameBoard, currentPlayer);
//            if (winner) {
//                break;
//            }
//            game.setCurrentPlayer(currentPlayer);
//
//        }
//        game.display(gameBoard);
//        game.announceWinner(winner, currentPlayer);
//
//    }

}
