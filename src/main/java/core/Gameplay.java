package main.java.core;

import java.util.Scanner;

import main.java.utils.Utils;
import main.java.player.Player;
import main.java.player.PlayerFactory;
import main.java.player.ComputerPlayer;

/**
 * Description: Create an object which holds the structure of the Connect 4
 * game.
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */
public class Gameplay {

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

    /**
     * Constructor for the gameplay flow.
     * 
     * @param gameType Determines if the game is vs AI or Player
     * @param scan     Scanner for input
     */
    public Gameplay(int gameType, Scanner scan) {
        createPlayers(gameType);
        this.scan = scan;
    }

    /**
     * Creates the player objects by calling the player factory and passing the game
     * type.
     * 
     * @param type The type of game, vs AI or Player
     */
    public void createPlayers(int type) {
        pf = new PlayerFactory();
        playerX = pf.createPlayer('p');
        if (type == 2) {
            playerO = pf.createPlayer('p');
        } else {
            playerO = pf.createPlayer('c');
        }
    }

    /**
     * Entry point to start up the game flow
     */
    public void start() {

        playtime();

    }

    /**
     * Initializes the game and creates a blank board
     */
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

    /**
     * Draws the board to the console
     */
    public void showBoard() {
        game.display(gameBoard);
    }

    /**
     * Sets up the start of the turn
     */
    public void startTurn() {
        currentPlayer = game.getCurrentPlayer();
        showBoard();
        if (!(currentPlayer instanceof ComputerPlayer)) {
            System.out.println("It is Player" + game.determinePlayerPiece(currentPlayer) + "'s turn.");
            System.out.println("Choose a column number 1-7 to place your piece or enter 0 to quit.");
        }
    }

    /**
     * Gets input from the AI or Player
     */
    public void playerChoice() {

        if (currentPlayer instanceof ComputerPlayer) {
            choice = ((ComputerPlayer) currentPlayer).computerTurn(gameBoard);
        } else {
            choice = Utils.getColumnInput(scan);
            if (choice == -1) {
                game.gameQuit(currentPlayer);
                quit = true;
                return;
            }
            valid = Utils.valid(choice, gameBoard, currentPlayer);
        }
    }

    /**
     * Places a piece onto the game board
     */
    public void makeMove() {
        game.placePiece(gameBoard, currentPlayer, choice);
        gameBoard = game.getGameBoard();
    }

    /**
     * Checks if there is a winner
     */
    public void checkWinner() {
        winner = game.winner(gameBoard, currentPlayer);
    }

    /**
     * The Connect 4 gameplay loop
     */
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

}
