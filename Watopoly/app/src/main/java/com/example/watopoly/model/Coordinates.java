package com.example.watopoly.model;

public class Coordinates {
    float left; float top; float right; float bottom;

    public Coordinates(float l, float t, float r, float b) {
        left = l; top = t; right = r; bottom = b;
    }

    public float getBottom() {
        return bottom;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }
}
