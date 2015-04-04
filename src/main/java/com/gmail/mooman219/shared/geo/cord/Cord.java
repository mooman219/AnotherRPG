package com.gmail.mooman219.shared.geo.cord;

import java.io.Serializable;

public abstract class Cord implements Serializable{

    private static final long serialVersionUID = 7027616161624210224L;

    public abstract Cord multiply(int amount);

    public abstract Cord divide(int amount);

    public abstract Cord add(int amount);

    public abstract Cord subtract(int amount);

    public abstract Cord mod(int amount);

    public abstract int getX();

    public abstract Cord setX(int x);

    public abstract Cord addX(int x);

    public abstract int getY();

    public abstract Cord setY(int y);

    public abstract Cord addY(int y);

    public abstract Cord clone();

    public boolean isWithin(int lowerBound, int upperBound){
        return (this.getX() > lowerBound && this.getX() < upperBound && this.getY() > lowerBound && this.getY() < upperBound);
    }

    public boolean equals(Cord pos){
        return (pos.getX() == this.getX() && pos.getY() == this.getY());
    }

    public String toString(){
        return this.getX()+", " +this.getY();
    }

    public static Cord add(Cord cord, int amount){
        return cord.clone().add(amount);
    }

    public static Cord subtract(Cord cord, int amount){
        return cord.clone().subtract(amount);
    }

    public static Cord multiply(Cord cord, int amount){
        return cord.clone().multiply(amount);
    }

    public static Cord divide(Cord cord, int amount){
        return cord.clone().divide(amount);
    }

    public static Cord mod(Cord cord, int amount){
        return cord.clone().mod(amount);
    }
}
