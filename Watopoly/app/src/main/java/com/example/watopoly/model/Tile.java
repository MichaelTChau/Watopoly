package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Tile {
    protected String name;
    protected Coordinates coordinates = new Coordinates(0,0,0,0);
    protected int maxNumberOfPlayers;
    protected int currNumberOfPlayers = 0;

    abstract void landOn(Canvas canvas, Player player, Paint paint);

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates c) {
        coordinates = c;
    }

    abstract public void drawOn(Canvas canvas);

}
