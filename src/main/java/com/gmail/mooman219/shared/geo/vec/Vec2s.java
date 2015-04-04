package com.gmail.mooman219.shared.geo.vec;

public class Vec2s extends Vec2{
    public short x;
    public short y;

    public Vec2s(short x, short y){
        this.x = x;
        this.y = y;
    }
    
    public Vec2s(double x, double y){
        this((short)x,(short)y);
    }
    
    public Vec2s(Vec2 vec){
        this(vec.getX(),vec.getY());
    }
    
    public Vec2 add(Vec2 vec){
        this.x += vec.getX();
        this.y += vec.getY();
        return this;
    }
    
    public Vec2 add(double x, double y){
        return add(new Vec2s(x,y));
    }
    
    public Vec2 subtract(Vec2 vec){
        this.x -= vec.getX();
        this.y -= vec.getY();
        return this;
    }
    
    public Vec2 subtract(double x, double y){
        return subtract(new Vec2s(x,y));
    }
    
    public Vec2 multiply(Vec2 vec){
        this.x *= vec.getX();
        this.y *= vec.getY();;
        return this;
    }
    
    public Vec2 multiply(double x, double y){
        return multiply(new Vec2s(x,y));
    }
    
    public Vec2 divide(Vec2 vec){
        this.x /= vec.getX();
        this.y /= vec.getY();
        return this;
    }
    
    public Vec2 divide(double x, double y){
        return divide(new Vec2s(x,y));
    }
    
    public Vec2 mod(Vec2 vec){
        if(vec.getX() != -1)this.x %= vec.getX();
        if(vec.getY() != -1)this.y %= vec.getY();
        return this;
    }
    
    public Vec2 mod(double x, double y){
        return mod(new Vec2s(x,y));
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public Vec2 setY(double y){
        this.y = (short) y;
        return this;
    }
    
    public Vec2 setX(double x){
        this.x = (short) x;
        return this;
    }
    
    public boolean within(Vec2s a, Vec2s b){
        return x > a.x && y > a.y && x < b.x && y < b.y;
    }
    
    public Vec2 clone(){
        return new Vec2s(this);
    }
}
