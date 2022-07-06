package main.java.utils;

import java.util.Scanner;

public class Utils {

    public static int getInput(Scanner scan) {
        int choice = scan.nextInt();
        choice = choice - 1;
        return choice;
    }

    public static boolean valid(int choice, char[][] board) {
        if (choice < 0 || choice > board.length) {
            System.out.println("Input must be either 1 through 7, or 0 to quit.");
            return false;
        }

        if (columnFull(choice, board)) {
            System.out.println("The column is full.");
            return false;
        }

        return true;
    }

    public static boolean columnFull(int choice, char[][] board) {
        if (board[0][choice] != ' ') {
            return true;
        }

        return false;
    }

}
