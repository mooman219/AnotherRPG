package com.gmail.mooman219.shared.geo.vec;

public class Vec3s extends Vec3{
    public short x;
    public short y;
    public short z;

    public Vec3s(short x, short y, short z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vec3s(double x, double y, double z){
        this((short)x,(short)y,(short)z);
    }
    
    public Vec3s(Vec3 vec){
        this(vec.getX(),vec.getY(),vec.getZ());
    }
    
    public Vec3 add(Vec3 vec){
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }
    
    public Vec3 add(double x, double y, double z){
        return add(new Vec3s(x,y,z));
    }
    
    public Vec3 subtract(Vec3 vec){
        this.x -= vec.getX();
        this.y -= vec.getY();
        this.z -= vec.getZ();
        return this;
    }
    
    public Vec3 subtract(double x, double y, double z){
        return subtract(new Vec3s(x,y,z));
    }
    
    public Vec3 multiply(Vec3 vec){
        this.x *= vec.getX();
        this.y *= vec.getY();
        this.z *= vec.getZ();
        return this;
    }
    
    public Vec3 multiply(double x, double y, double z){
        return multiply(new Vec3s(x,y,z));
    }
    
    public Vec3 divide(Vec3 vec){
        this.x /= vec.getX();
        this.y /= vec.getY();
        this.z /= vec.getZ();
        return this;
    }
    
    public Vec3 divide(double x, double y, double z){
        return divide(new Vec3s(x,y,z));
    }
    
    public Vec3 mod(Vec3 vec){
    	if(vec.getX() != -1)this.x %= vec.getX();
        if(vec.getY() != -1)this.y %= vec.getY();
        if(vec.getZ() != -1)this.z %= vec.getZ();
        return this;
    }
    
    public Vec3 mod(double x, double y, double z){
        return mod(new Vec3s(x,y,z));
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getZ(){
        return z;
    }
    
    public Vec3 setY(double y){
        this.y = (short) y;
        return this;
    }
    
    public Vec3 setX(double x){
        this.x = (short) x;
        return this;
    }
    
    public Vec3 setZ(double z){
        this.z = (short) z;
        return this;
    }
    
    public boolean within(Vec3s a, Vec3s b){
        return x > a.x && y > a.y && z > a.z && x < b.x && y < b.y && z < b.z;
    }
    
    public Vec3 clone(){
        return new Vec3s(this);
    }
}
