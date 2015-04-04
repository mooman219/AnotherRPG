package com.gmail.mooman219.test3D.shape.polygon;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Rectangle extends Quadrilateral{
    public Rectangle(Vector3f a, Vector3f c){
        super(a, new Vector3f(c.x, a.y, a.z),
                c, new Vector3f(a.x, c.y, c.z));
    }
    
    public Rectangle(Vector2f a, Vector2f c){
        super(a, new Vector2f(c.x, a.y),
                c, new Vector2f(a.x, c.y));
    }
}
