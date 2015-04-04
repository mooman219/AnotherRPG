package com.gmail.mooman219.test3D.physics.math;

import com.gmail.mooman219.test3D.physics.math.geometry.Vector;

public class CollisionResult {
    public boolean willIntersect;
    public Vector entityOneTranslation;
    public Vector entityTwoTranslation;
    public Vector entityOneVelocity;
    public Vector entityTwoVelocity;

    public CollisionResult(){
        willIntersect = false;
        entityOneTranslation = new Vector(0,0,0);
        entityTwoTranslation = new Vector(0,0,0);
        entityOneVelocity = new Vector(0,0,0);
        entityTwoVelocity = new Vector(0,0,0);
    }
}
