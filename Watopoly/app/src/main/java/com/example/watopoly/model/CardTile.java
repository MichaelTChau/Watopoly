package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.watopoly.enums.BuildingDirection;

public class CardTile extends Tile{

    private BuildingDirection direction;

    //TODO: draw and apply cards
    @Override
    void landOn(Canvas canvas, Player player, Paint paint) {

    }

    @Override
    public void drawOn(Canvas canvas) {

    }

    public CardTile (String n, BuildingDirection d) {
        this.name = n;
        this.direction = d;
        maxNumberOfPlayers = 2;
    }
}
