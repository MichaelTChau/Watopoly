package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.watopoly.enums.Direction;

public class CardTile extends Tile{

    private Direction direction;

    //TODO: draw and apply cards
    @Override
    void landOn(Canvas canvas, Player player, Paint paint) {

    }

    @Override
    public void drawOn(Canvas canvas) {

    }

    public CardTile (String n, Direction d) {
        this.name = n;
        this.direction = d;
        maxNumberOfPlayers = 2;
    }
}
