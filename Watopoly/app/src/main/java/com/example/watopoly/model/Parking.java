package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Parking extends Tile {
    @Override
    void landOn(Canvas canvas, Player player, Paint paint) { }

    @Override
    public void drawOn(Canvas canvas) {

    }

    public Parking() {
        maxNumberOfPlayers = 4;
    }

}
