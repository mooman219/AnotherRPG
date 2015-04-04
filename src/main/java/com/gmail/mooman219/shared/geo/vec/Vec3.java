package com.gmail.mooman219.shared.geo.vec;

import java.io.Serializable;

public abstract class Vec3 implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public abstract Vec3 add(Vec3 vec);
    
    public abstract Vec3 add(double x, double y, double z);
    
    public abstract Vec3 subtract(Vec3 vec);
    
    public abstract Vec3 subtract(double x, double y, double z);
    
    public abstract Vec3 multiply(Vec3 vec);
    
    public abstract Vec3 multiply(double x, double y, double z);
    
    public abstract Vec3 divide(Vec3 vec);
    
    public abstract Vec3 divide(double x, double y, double z);
    
    public abstract Vec3 mod(Vec3 vec);
    
    public abstract Vec3 mod(double x, double y, double z);
    
    public abstract Vec3 setY(double y);
    
    public abstract Vec3 setX(double x);
    
    public abstract Vec3 setZ(double z);
    
    public abstract double getY();
    
    public abstract double getX();
    
    public abstract double getZ();
    
    public abstract Vec3 clone();
    
    public boolean isWithin(double start, double end){
        return getX() > start && getY() > start && getZ() > start && getX() < end && getY() < end && getZ() < end;
    }
    
    public static Vec3 add(Vec3 vec, Vec3 modifier){
        return vec.clone().add(modifier);
    }
    
    public static Vec3 add(Vec3 vec, double x, double y, double z){
        return vec.clone().add(x,y,z);
    }
    
    public static Vec3 subtract(Vec3 vec, Vec3 modifier){
        return vec.clone().subtract(modifier);
    }
    
    public static Vec3 subtract(Vec3 vec, double x, double y, double z){
        return vec.clone().subtract(x,y,z);
    }
    
    public static Vec3 multiply(Vec3 vec, Vec3 modifier){
        return vec.clone().multiply(modifier);
    }
    
    public static Vec3 multiply(Vec3 vec, double x, double y, double z){
        return vec.clone().multiply(x,y,z);
    }
    
    public static Vec3 divide(Vec3 vec, Vec3 modifier){
        return vec.clone().divide(modifier);
    }
    
    public static Vec3 divide(Vec3 vec, double x, double y, double z){
        return vec.clone().divide(x,y,z);
    }
    
    public static Vec3 mod(Vec3 vec, Vec3 modifier){
        return vec.clone().mod(modifier);
    }
    
    public static Vec3 mod(Vec3 vec, double x, double y, double z){
        return vec.clone().mod(x,y,z);
    }
}
