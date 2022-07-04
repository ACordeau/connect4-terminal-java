package main.java.core;

public class Gameplay {
    private int gameType;
    private Player playerX;
    private Player playerO;

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

    }

}
