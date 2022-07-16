package main.java.utils;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import main.java.player.ComputerPlayer;
import main.java.player.Player;

/**
 * Description: Utility methods.
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */
public class Utils {

    /**
     * Gets menu input from the Player. Will return a non valid number if the Player
     * does not input an integer.
     * 
     * @param scan Scanner to get input
     * @return choice of menu option
     */
    public static int getMenuInput(Scanner scan) {
        try {
            int choice = scan.nextInt();
            return choice;
        } catch (InputMismatchException e) {
            scan.nextLine();
            return -1;
        }
    }

    /**
     * Gets column input from the Player. Will return a non valid number if the
     * Player does not input an integer.
     * 
     * @param scan Scanner to get input
     * @return choice of column to place piece
     */
    public static int getColumnInput(Scanner scan) {
        try {
            int choice = scan.nextInt();
            choice = choice - 1;
            return choice;
        } catch (InputMismatchException e) {
            scan.nextLine();
            return -5;
        }
    }

    /**
     * Gives a random number between 0 and 100
     * 
     * @return Random number
     */
    public static int randomNumber() {
        Random rand = new Random();
        int random = rand.nextInt(100 - 0) + 0;
        return random;
    }

    /**
     * Checks to see if the choice given is a valid input.
     * 
     * @param choice The given input choice
     * @param board  The state of the game
     * @param p      The current turn player
     * @return Boolean to decide if the move is valid
     */
    public static boolean valid(int choice, char[][] board, Player p) {
        if (choice < 0 || choice > board.length) {
            if (!(p instanceof ComputerPlayer)) {
                createMessage("Input must be either 1 through 7, or 0 to quit.");
            }
            return false;
        }

        if (columnFull(choice, board)) {
            if (!(p instanceof ComputerPlayer)) {
                createMessage("The column is full.");
            }
            return false;
        }

        return true;
    }

    /**
     * Checks to see if the column choice is full.
     * 
     * @param choice The given input choice
     * @param board  The state of the game
     * @return Boolean to decide if the move is valid
     */
    public static boolean columnFull(int choice, char[][] board) {
        if (board[0][choice] != ' ') {
            return true;
        }

        return false;
    }

    /**
     * A helper method that returns the game token at a given space on the board.
     * 
     * @param row integer for row space
     * @param col integer for column space
     * @return character token
     */
    public static char getPiece(char[][] gameBoard, int row, int col) {
        char c = gameBoard[row][col];
        return c;
    }

    public static void createMessage(String... s) {
        StringBuilder sb = new StringBuilder();
        for (String val : s) {
            sb.append(val);
        }
        System.out.println(sb);
    }

}
