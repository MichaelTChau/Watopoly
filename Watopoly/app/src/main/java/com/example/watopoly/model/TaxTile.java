package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.watopoly.enums.BuildingDirection;

public class TaxTile extends Tile {
    @Override
    void landOn(Canvas canvas, Player player, Paint paint) {

    }

    @Override
    public void drawOn(Canvas canvas) {

    }

    private BuildingDirection direction;

    public TaxTile(BuildingDirection d) {
        this.direction = d;
    }
}
