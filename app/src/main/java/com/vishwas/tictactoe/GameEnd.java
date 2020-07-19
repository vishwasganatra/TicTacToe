package com.vishwas.tictactoe;

import android.graphics.Point;

public class GameEnd {
    private Poi arr[];
    private int player, key;
    private boolean isDraw, isResult;

    public GameEnd() {
        arr = new Poi[3];
    }

    public GameEnd(Poi a, Poi b, Poi c, int player) {
        arr = new Poi[3];
        arr[0] = a;
        arr[1] = b;
        arr[2] = c;
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public Poi[] getArr() {
        return arr;
    }

    public boolean getIsDraw() {
        return isDraw;
    }

    public boolean getIsResult() {
        return isResult;
    }

    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    public void setIsResult(boolean isResult) {
        this.isResult = isResult;
    }

    public void Push(Poi p, int player) {
        arr[key++] = p;
        this.player = player;
    }

    public void Reset() {
        key = 0;
        this.player = 0;
    }

    public void print() {
        System.out.println("isDraw = " + isDraw);
        System.out.println("isResult = " + isResult);
        System.out.println("arr[0] = " + arr[0].getX() + " " + arr[0].getY());
        System.out.println("arr[1] = " + arr[1].getX() + " " + arr[1].getY());
        System.out.println("arr[2] = " + arr[2].getX() + " " + arr[2].getY());
        System.out.println("player " + player);
    }
}