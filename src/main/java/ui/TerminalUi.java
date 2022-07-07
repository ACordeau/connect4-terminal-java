package main.java.ui;

import java.util.Scanner;

import main.java.core.Gameplay;

/**
 * Description: Create a simple console UI for a game of Connect 4. Determines
 * the structure of a Connect 4 game.
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */

public class TerminalUi {
    
    private static Scanner scan;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        scan = new Scanner(System.in);
        int playerMenuChoice = -1;

        while (playerMenuChoice < 0 || playerMenuChoice > 2) {

            System.out.println("Welcome to Aaron's Connect 4 experience. \n******************************");
            System.out.println("1. Start game against AI");
            System.out.println("2. Start game against Player");
            System.out.println("0. Quit");

            System.out.print("\nPlease select a menu option: ");

            playerMenuChoice = scan.nextInt();

            switch (playerMenuChoice) {
            case 1:
                // pve();
                System.out.println("pve not yet implemented");
                break;
            case 2:
                pvp();
                break;
            case 0:
                System.out.println("Thank you for joining, goodbye!");
                scan.close();
                System.exit(0);
                break;
            default:
                System.out.println("Please select an available option");
            }
        }
        
    }
    
    public static void pvp() {
        Gameplay game = new Gameplay(2, scan);
        game.start();
        menu();
    }

}
