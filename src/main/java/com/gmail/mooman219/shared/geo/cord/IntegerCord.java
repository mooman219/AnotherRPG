package com.gmail.mooman219.shared.geo.cord;

public class IntegerCord extends Cord{
    private static final long serialVersionUID = 1L;
    public int x;
    public int y;

    public IntegerCord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public IntegerCord(IntegerCord pos){
        this.x = pos.x;
        this.y = pos.y;
    }

    public IntegerCord multiply(int amount){
        this.x *= amount;
        this.y *= amount;
        return this;
    }

    public IntegerCord divide(int amount){
        this.x /= amount;
        this.y /= amount;
        return this;
    }

    public IntegerCord add(int amount){
        this.x += amount;
        this.y += amount;
        return this;
    }

    public IntegerCord subtract(int amount){
        this.x -= amount;
        this.y -= amount;
        return this;
    }

    public IntegerCord mod(int amount){
        this.x %= amount;
        this.y %= amount;
        return this;
    }

    public int getX() {
        return x;
    }

    public IntegerCord setX(int x) {
        this.x = x;
        return this;
    }

    public IntegerCord addX(int x) {
        this.x += x;
        return this;
    }

    public int getY() {
        return y;
    }

    public IntegerCord setY(int y) {
        this.y = y;
        return this;
    }

    public IntegerCord addY(int y) {
        this.y += y;
        return this;
    }

    public IntegerCord clone(){
        return new IntegerCord(this);
    }
}
