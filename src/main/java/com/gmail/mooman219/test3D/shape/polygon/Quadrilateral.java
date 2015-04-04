package com.gmail.mooman219.test3D.shape.polygon;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import com.gmail.mooman219.test3D.shape.Shape;
import com.gmail.mooman219.test3D.util.BufferHelper;

public class Quadrilateral extends Shape{
    public Vector3f[] vertices;

    public Quadrilateral(Vector3f a, Vector3f b, Vector3f c, Vector3f d){
        vertices = new Vector3f[]{a, b, c, d};
        this.renderer.setVertexData(3, toFloatBuffer());
    }
    
    public Quadrilateral(Vector2f a, Vector2f b, Vector2f c, Vector2f d){
        this(new Vector3f(a.x, a.y, 0), new Vector3f(b.x, b.y, 0),
                new Vector3f(c.x, c.y, 0), new Vector3f(d.x, d.y, 0));
    }
    
    public Vector3f[] getVertices() {
        return vertices;
    }

    public FloatBuffer toFloatBuffer() {
        float[] tmp =  new float[]{
                vertices[0].x, vertices[0].y, vertices[0].z,// A FROUNT
                vertices[1].x, vertices[1].y, vertices[1].z,//B
                vertices[2].x, vertices[2].y, vertices[2].z,//C
                vertices[3].x, vertices[3].y, vertices[3].z,//D
        };
        return BufferHelper.toFloatBuffer(tmp);
    }
}
