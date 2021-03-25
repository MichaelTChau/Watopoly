package com.example.watopoly.model;

import java.util.ArrayList;

public class Game {
    private static Game gameState = new Game();

    private ArrayList<Player> players = new ArrayList<>();
    private Board board = new Board();
    private int turnNumber = -1;

    //Singleton
    private Game(){}

    public static Game getInstance() {
        return gameState;
    }

    //public methods
    public void setTiles(ArrayList<Tile> tiles) {
        board.setTiles(tiles);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player nextTurn() {
        turnNumber ++;
        return players.get(turnNumber % players.size());
    }

    public Player getCurrentPlayer() {
        return players.get(turnNumber % players.size());
    }
}
