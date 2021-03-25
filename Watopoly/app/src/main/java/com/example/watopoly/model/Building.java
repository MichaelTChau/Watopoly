package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.watopoly.enums.Direction;

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

        // offset for thickness of gameboard lines, which is currently 4
        int thicknessOffset = 2;

        if (direction == Direction.LEFT) {
            canvas.drawRect(coordinates.left + fillOffset,
                    coordinates.top,
                    coordinates.right - thicknessOffset,
                    coordinates.bottom - thicknessOffset,
                    insideFill);
        } else if (direction == Direction.TOP) {
            canvas.drawRect(coordinates.left + thicknessOffset,
                    coordinates.top + fillOffset,
                    coordinates.right - thicknessOffset,
                    coordinates.bottom - thicknessOffset,
                    insideFill);
        } else if (direction == Direction.RIGHT) {
            canvas.drawRect(coordinates.left + thicknessOffset,
                    coordinates.top - thicknessOffset,
                    coordinates.right - fillOffset,
                    coordinates.bottom - thicknessOffset,
                    insideFill);
        } else if (direction == Direction.BOTTOM) {
            canvas.drawRect(coordinates.left + thicknessOffset,
                    coordinates.top - fillOffset,
                    coordinates.right - thicknessOffset,
                    coordinates.bottom + thicknessOffset,
                    insideFill);
        }
    }

    //TODO: find what this needs
    public void upgrade() {

    }

    public Building(String name, Direction direction, double baseRentPrice, double purchasePrice, String hexCode) {
        super(name, direction, baseRentPrice, purchasePrice);
        this.hexCode = hexCode;
    }
}
