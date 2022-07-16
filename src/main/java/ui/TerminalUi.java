package main.java.ui;

import java.util.Scanner;

import main.java.core.Gameplay;
import main.java.utils.Utils;

/**
 * Description: Create a simple console UI for a game of Connect 4. Determines
 * the structure of a Connect 4 game.
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */

public class TerminalUi {

    // TODO DONE | ADD GRADLE
    // TODO DONE | FIX QUIT GAME BUG
    // TODO DONE | FIND A WAY TO ABSTRACT THE COMPUTER PLAYER METHODS
    // TODO DONE | ADD MORE MEANINGFUL MESSAGES FOR THE USER
    // TODO ADD DIFFICULTY
    // TODO DONE | FIX INPUTMISMATCH

    private static Scanner scan;

    /**
     * Entry point to the program.
     * 
     * @param args
     */
    public static void main(String[] args) {
        menu();
    }

    /**
     * Creates the UI. User chooses to play against a computer, another player, or
     * exit.
     */
    public static void menu() {
        scan = new Scanner(System.in);
        int playerMenuChoice = -1;

        while (playerMenuChoice < 0 || playerMenuChoice > 2) {

            Utils.createMessage("Welcome to Aaron's Connect 4 experience. \n******************************",
                    "\n1. Start game against AI", "\n2. Start game against Player", "\n0. Quit");

            System.out.print("\nPlease select a menu option: ");

            playerMenuChoice = Utils.getMenuInput(scan);

            switch (playerMenuChoice) {
            case 1:
                start(playerMenuChoice);
                break;
            case 2:
                start(playerMenuChoice);
                break;
            case 0:
                Utils.createMessage("Thank you for joining, goodbye!");
                scan.close();
                System.exit(0);
                break;
            default:
                Utils.createMessage("Please select an available option");
            }
        }

    }

    /**
     * Creates a new Gameplay object and starts the game.
     * 
     * @param type
     */
    private static void start(int type) {
        Gameplay game = new Gameplay(type, scan);
        game.start();
        menu();
    }

}
