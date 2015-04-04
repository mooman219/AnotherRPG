package com.gmail.mooman219.test3D.shape.polygon;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import com.gmail.mooman219.test3D.shape.Shape;
import com.gmail.mooman219.test3D.util.BufferHelper;

public class Triangle extends Shape{
    public Vector3f[] vertices;

    public Triangle(Vector3f a, Vector3f b, Vector3f c){
        vertices = new Vector3f[]{a, b, c};
        this.renderer.setVertexData(3, toFloatBuffer())
        .setType(GL11.GL_TRIANGLES);
    }
    
    public Vector3f[] getVertices() {
        return vertices;
    }

    public FloatBuffer toFloatBuffer() {
        float[] tmp =  new float[]{
                vertices[0].x, vertices[0].y, vertices[0].z,
                vertices[1].x, vertices[1].y, vertices[1].z,
                vertices[2].x, vertices[2].y, vertices[2].z
        };
        return BufferHelper.toFloatBuffer(tmp);
    }
}
