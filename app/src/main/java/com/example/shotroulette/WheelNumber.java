package com.example.shotroulette;

public class WheelNumber {

    private int number;

    //color=0 means red ,color=1 means black and color=2 means green
    private int color;

    public WheelNumber(int number, int color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
