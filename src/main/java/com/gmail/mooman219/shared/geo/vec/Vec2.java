package com.gmail.mooman219.shared.geo.vec;

import java.io.Serializable;

public abstract class Vec2 implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public abstract Vec2 add(Vec2 vec);
    
    public abstract Vec2 add(double x, double y);
    
    public abstract Vec2 subtract(Vec2 vec);
    
    public abstract Vec2 subtract(double x, double y);
    
    public abstract Vec2 multiply(Vec2 vec);
    
    public abstract Vec2 multiply(double x, double y);
    
    public abstract Vec2 divide(Vec2 vec);
    
    public abstract Vec2 divide(double x, double y);
    
    public abstract Vec2 mod(Vec2 vec);
    
    public abstract Vec2 mod(double x, double y);
    
    public abstract Vec2 setY(double y);
    
    public abstract Vec2 setX(double x);
    
    public abstract double getY();
    
    public abstract double getX();
    
    public abstract Vec2 clone();
    
    public boolean isWithin(double start, double end){
        return getX() > start && getY() > start && getX() < end && getY() < end;
    }
    
    public static Vec2 add(Vec2 vec, Vec2 modifier){
        return vec.clone().add(modifier);
    }
    
    public static Vec2 add(Vec2 vec, double x, double y){
        return vec.clone().add(x,y);
    }
    
    public static Vec2 subtract(Vec2 vec, Vec2 modifier){
        return vec.clone().subtract(modifier);
    }
    
    public static Vec2 subtract(Vec2 vec, double x, double y){
        return vec.clone().subtract(x,y);
    }
    
    public static Vec2 multiply(Vec2 vec, Vec2 modifier){
        return vec.clone().multiply(modifier);
    }
    
    public static Vec2 multiply(Vec2 vec, double x, double y){
        return vec.clone().multiply(x,y);
    }
    
    public static Vec2 divide(Vec2 vec, Vec2 modifier){
        return vec.clone().divide(modifier);
    }
    
    public static Vec2 divide(Vec2 vec, double x, double y){
        return vec.clone().divide(x,y);
    }
    
    public static Vec2 mod(Vec2 vec, Vec2 modifier){
        return vec.clone().mod(modifier);
    }
    
    public static Vec2 mod(Vec2 vec, double x, double y){
        return vec.clone().mod(x,y);
    }
}
