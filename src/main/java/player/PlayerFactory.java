package main.java.player;

public class PlayerFactory {
    
    public Player createPlayer(char c) {
        if (c == 'p') {
            return new Player();
        } else {
            return new ComputerPlayer();
        }
    }

}
