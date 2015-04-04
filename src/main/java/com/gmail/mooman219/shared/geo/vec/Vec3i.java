package com.gmail.mooman219.shared.geo.vec;

public class Vec3i extends Vec3{
    public int x;
    public int y;
    public int z;

    public Vec3i(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vec3i(double x, double y, double z){
        this((int)x,(int)y,(int)z);
    }
    
    public Vec3i(Vec3 vec){
        this(vec.getX(),vec.getY(),vec.getZ());
    }
    
    public Vec3 add(Vec3 vec){
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }
    
    public Vec3 add(double x, double y, double z){
        return add(new Vec3i(x,y,z));
    }
    
    public Vec3 subtract(Vec3 vec){
        this.x -= vec.getX();
        this.y -= vec.getY();
        this.z -= vec.getZ();
        return this;
    }
    
    public Vec3 subtract(double x, double y, double z){
        return subtract(new Vec3i(x,y,z));
    }
    
    public Vec3 multiply(Vec3 vec){
        this.x *= vec.getX();
        this.y *= vec.getY();
        this.z *= vec.getZ();
        return this;
    }
    
    public Vec3 multiply(double x, double y, double z){
        return multiply(new Vec3i(x,y,z));
    }
    
    public Vec3 divide(Vec3 vec){
        this.x /= vec.getX();
        this.y /= vec.getY();
        this.z /= vec.getZ();
        return this;
    }
    
    public Vec3 divide(double x, double y, double z){
        return divide(new Vec3i(x,y,z));
    }
    
    public Vec3 mod(Vec3 vec){
    	if(vec.getX() != -1)this.x %= vec.getX();
        if(vec.getY() != -1)this.y %= vec.getY();
        if(vec.getZ() != -1)this.z %= vec.getZ();
        return this;
    }
    
    public Vec3 mod(double x, double y, double z){
        return mod(new Vec3i(x,y,z));
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
        this.y = (int) y;
        return this;
    }
    
    public Vec3 setX(double x){
        this.x = (int) x;
        return this;
    }
    
    public Vec3 setZ(double z){
        this.z = (int) z;
        return this;
    }
    
    public boolean within(Vec3i a, Vec3i b){
        return x > a.x && y > a.y && z > a.z && x < b.x && y < b.y && z < b.z;
    }
    
    public Vec3 clone(){
        return new Vec3i(this);
    }
}
