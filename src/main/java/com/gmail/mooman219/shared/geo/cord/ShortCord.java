package com.gmail.mooman219.shared.geo.cord;

public class ShortCord extends Cord{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public short x;
    public short y;

    public ShortCord(short x,short y){
        this.x = x;
        this.y = y;
    }

    public ShortCord(int x, int y){
        this((short)x, (short)y);
    }

    public ShortCord(ShortCord pos){
        this.x = pos.x;
        this.y = pos.y;
    }

    public ShortCord multiply(int amount){
        this.x *= amount;
        this.y *= amount;
        return this;
    }

    public ShortCord divide(int amount){
        this.x /= amount;
        this.y /= amount;
        return this;
    }

    public ShortCord add(int amount){
        this.x += amount;
        this.y += amount;
        return this;
    }

    public ShortCord subtract(int amount){
        this.x -= amount;
        this.y -= amount;
        return this;
    }

    public ShortCord mod(int amount){
        this.x %= amount;
        this.y %= amount;
        return this;
    }

    public int getX() {
        return x;
    }

    public ShortCord setX(int x) {
        this.x = (short)x;
        return this;
    }

    public ShortCord addX(int x) {
        this.x += x;
        return this;
    }

    public int getY() {
        return y;
    }

    public ShortCord setY(int y) {
        this.y = (short)y;
        return this;
    }

    public ShortCord addY(int y) {
        this.y += y;
        return this;
    }

    public ShortCord clone(){
        return new ShortCord(this);
    }
}
