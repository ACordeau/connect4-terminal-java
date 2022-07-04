package main.java.core;

public class Player {
    int pieces;
    
    public Player() {
        setPieces(21);
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
