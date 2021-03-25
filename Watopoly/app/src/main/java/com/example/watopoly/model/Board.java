package com.example.watopoly.model;

import com.example.watopoly.view.BoardView;

import java.util.ArrayList;

public class Board {
    private ArrayList<Tile> boardTiles = new ArrayList<>();

    public void setTiles(ArrayList<Tile> tiles) {
        boardTiles = tiles;
    }
    //TODO:
    public void move(Player player, int steps) {
    }
}
