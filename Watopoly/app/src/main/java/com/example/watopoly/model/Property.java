package com.example.watopoly.model;

import com.example.watopoly.enums.Direction;

public abstract class Property extends Tile {
    protected Player owner = null;
    protected double baseRentPrice;
    protected double purchasePrice;
    protected boolean isMortgaged = false;
    protected Direction direction;


    abstract double getRentPrice();
    public double getPurchasePrice(){ return purchasePrice; }

    abstract boolean purchase(Player buyer);

    protected Property(String name, Direction direction, double baseRentPrice, double purchasePrice) {
        this.direction = direction;
        this.baseRentPrice = baseRentPrice;
        this.name = name;
        this.purchasePrice = purchasePrice;
        maxNumberOfPlayers = 2;
    }
}

