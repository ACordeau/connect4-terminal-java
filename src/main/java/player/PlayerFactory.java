package main.java.player;

/**
 * Description: Player Factory used to create new player objects and decide if
 * they are Human Players or Computer Players
 * 
 * @author Aaron Cordeau
 * @version 7/3/2022
 */
public class PlayerFactory {

    /**
     * Creates a new instance of a Player object.
     * 
     * @param c Character used to decide if Player object will be Human or Computer.
     * @return new Player object instance
     */
    public Player createPlayer(char c) {
        if (c == 'p') {
            return new Player();
        } else {
            return new ComputerPlayer();
        }
    }

}
