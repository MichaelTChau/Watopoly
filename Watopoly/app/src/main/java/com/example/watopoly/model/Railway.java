package com.example.watopoly.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.watopoly.enums.Direction;

public class Railway extends Property {

    @Override
    double getRentPrice() {
        return 0;
    }

    //TODO:
    @Override
    boolean purchase(Player buyer) {
        return false;
    }

    @Override
    void landOn(Canvas canvas, Player player, Paint paint) {

    }

    @Override
    public void drawOn(Canvas canvas) {

    }

    public Railway(String name, Direction direction, double baseRentPrice, double purchasePrice) {
        super(name, direction, baseRentPrice, purchasePrice);
    }

}
