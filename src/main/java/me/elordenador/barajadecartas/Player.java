package me.elordenador.barajadecartas;


/**
 * Represents a player in the game.
 * This class can set and get the player's points and name.
 * @version 1.0
 * @since 1.0
 * @author Daniel Sánchez Úbeda
 */
public class Player {
    /**
     * The number of points the player has.
     */
    private double points;

    /**
     * The name of the player.
     */
    private String playername;

    /**
     * Constructor for the Player class.
     */
    public Player() {
        this.points = 0;
        this.playername = "";
    }

    /**
     * Constructor for the Player class.
     * @param playername The name of the player.
     */
    public Player(String playername) {
        this.points = 0;
        this.playername = playername;
    }

    /**
     * Constructor for the Player class.
     * @param playername The name of the player.
     * @param points The number of points the player has.
     */
    public Player(String playername, double points) {
        this.points = points;
        this.playername = playername;
    }

    /**
     * Sets the number of points the player has.
     * @param points The number of points to set.
     */
    public void setPoints(double points) {
        this.points = points;
    }

    /**
     * Gets the number of points the player has.
     * @return The number of points the player has.
     */
    public double getPoints() {
        return points;
    }

    /**
     * Sets the name of the player.
     * @param playername The name of the player.
     */
    public void setPlayername(String playername) {
        this.playername = playername;
    }

    /**
     * Gets the name of the player.
     * @return The name of the player.
     */
    public String getPlayername() {
        return playername;
    }
}
