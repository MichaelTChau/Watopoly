package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.watopoly.enums.BuildingDirection;

public class Building extends Property {
    private int numberOfHouses = 0;
    private int numberOfHotels = 0;
    private String hexCode;

    @Override
    double getRentPrice() {
        return 0;
    }

    @Override
    boolean purchase(Player buyer) {
        return false;
    }

    @Override
    void landOn(Canvas canvas, Player player, Paint paint) {

    }

    @Override
    public void drawOn(Canvas canvas) {
        Paint insideFill = new Paint();
        insideFill.setColor(Color.parseColor(hexCode));
        int fillOffset = 125;

        if (direction == BuildingDirection.LEFT) {
            canvas.drawRect((float) (coordinates.left + fillOffset), coordinates.top + 2, coordinates.right - 2, coordinates.bottom, insideFill);
        } else if (direction == BuildingDirection.TOP) {
            canvas.drawRect(coordinates.left + 2, coordinates.top - 2, coordinates.right - 2, coordinates.bottom + fillOffset, insideFill);
        } else if (direction == BuildingDirection.RIGHT) {
            canvas.drawRect(coordinates.left + 2, coordinates.top + 2, coordinates.right - fillOffset, coordinates.bottom, insideFill);
        } else if (direction == BuildingDirection.BOTTOM) {
            canvas.drawRect(coordinates.left + 2, coordinates.top - fillOffset, coordinates.right - 2, coordinates.bottom + 2, insideFill);
        }
    }

    //TODO: find what this needs
    public void upgrade() {

    }

    public Building(String name, BuildingDirection direction, double baseRentPrice, double purchasePrice, String hexCode) {
        super(name, direction, baseRentPrice, purchasePrice);
        this.hexCode = hexCode;
    }
}
