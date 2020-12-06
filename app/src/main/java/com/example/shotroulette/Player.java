package com.example.shotroulette;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")

public class Player {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String name ;
    @ColumnInfo(name="color")
    private int color ;
    @ColumnInfo(name="bet_type")
    private int bet_type;
    @ColumnInfo(name = "have_bet")
    private boolean have_bet;
    @ColumnInfo(name="bet_number")
    private int bet_number;
    @ColumnInfo(name="bet_result")
    private int bet_result;
    @ColumnInfo(name="wheel_number")
    private int wheel_number;
    @ColumnInfo(name = "have_drunk")
    private boolean have_drunk;

    public Player(int ID,String name, int color) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.have_bet = false;
        this.wheel_number = -1;
        this.bet_result = 0;
        this.have_drunk = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getBet_type() {
        return bet_type;
    }

    public void setBet_type(int bet_type) {
        this.bet_type = bet_type;
    }

    public boolean isHave_bet() {
        return have_bet;
    }

    public void setHave_bet(boolean have_bet) {
        this.have_bet = have_bet;
    }

    public int getBet_number() {
        return bet_number;
    }

    public void setBet_number(int bet_number) {
        this.bet_number = bet_number;
    }

    public int getBet_result() {
        return bet_result;
    }

    public void setBet_result(int bet_result) {
        this.bet_result = bet_result;
    }

    public int getWheel_number() {
        return wheel_number;
    }

    public void setWheel_number(int wheel_number) {
        this.wheel_number = wheel_number;
    }

    public boolean isHave_drunk() {
        return have_drunk;
    }

    public void setHave_drunk(boolean have_drunk) {
        this.have_drunk = have_drunk;
    }
}
