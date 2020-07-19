package com.vishwas.tictactoe;

public class Poi {
    private int x, y, h, d;

    public Poi() {

    }

    public Poi(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public Poi(int x, int y, int h, int d) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.d = d;
    }

    public Poi(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Poi(int h) {
        this.h = h;
    }

    public Poi(int d, boolean f) {
        this.d = d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getD() {
        return d;
    }
}
