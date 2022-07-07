package main.java.core;

import main.java.utils.Connect4Constants;

public class Player implements Connect4Constants{
    int pieces;
    
    public Player() {
        setPieces(PIECES);
    }
    
    private void setPieces(int pieces) {
        this.pieces = pieces;
    }
    
    public int getPieces() {
        return this.pieces;
    }
    
    public void decrementPiece() {
        this.pieces--;
    }
}
