package main.java.utils;

public interface Connect4Constants {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static final int PIECES = 21;
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char WALL = '|';
    public static final int[][] DIRECTIONS = { { 1, 0 }, { 1, -1 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, -1 }, { -1, -1 } };
}
