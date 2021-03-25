package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class GoTile extends Tile {
    @Override
    void landOn(Canvas canvas, Player player, Paint paint) { }

    @Override
    public void drawOn(Canvas canvas) {

    }

    public GoTile() {
        maxNumberOfPlayers = 4;
    }

}
