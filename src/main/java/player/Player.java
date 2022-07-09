package main.java.player;

import main.java.utils.Connect4Constants;

/**
 * Description: Player class of a game of Connect 4
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */
public class Player implements Connect4Constants {
    int pieces;

    /**
     * Default constructor to create a new Player object and initalize their pieces
     */
    public Player() {
        setPieces(PIECES);
    }

    /**
     * Sets the initial amount of pieces. Only used to initilize new Player obejcts.
     * 
     * @param pieces Initial number of pieces
     */
    private void setPieces(int pieces) {
        this.pieces = pieces;
    }

    /**
     * Gives the amount of remaining pieces.
     * 
     * @return
     */
    public int getPieces() {
        return this.pieces;
    }

    /**
     * Decrements the current pieces by one.
     */
    public void decrementPiece() {
        this.pieces--;
    }
}
