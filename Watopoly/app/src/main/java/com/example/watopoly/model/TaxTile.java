package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.watopoly.enums.Direction;

public class TaxTile extends Tile {
    @Override
    void landOn(Canvas canvas, Player player, Paint paint) {

    }

    @Override
    public void drawOn(Canvas canvas) {

    }

    private Direction direction;

    public TaxTile(Direction d) {
        this.direction = d;
    }
}
