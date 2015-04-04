package com.gmail.mooman219.test3D.shape.polyhedron;

import org.lwjgl.util.vector.Vector3f;

public class RectangularPrism extends Hexahedron{
    public RectangularPrism(Vector3f a, Vector3f h){
        super(a, new Vector3f(h.x, a.y, a.z),
                new Vector3f(h.x, h.y, a.z), new Vector3f(a.x, h.y, a.z),
                new Vector3f(a.x, a.y, h.z), new Vector3f(h.x, a.y, h.z),
                new Vector3f(a.x, h.y, h.z), h);
    }
}
