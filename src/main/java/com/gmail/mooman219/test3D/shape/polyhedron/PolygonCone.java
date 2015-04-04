package com.gmail.mooman219.test3D.shape.polyhedron;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.gmail.mooman219.test3D.shape.Shape;
import com.gmail.mooman219.test3D.util.BufferHelper;

public class PolygonCone extends Shape{
    public Vector3f[] vertices;

    public PolygonCone(Vector3f top, Vector3f... base){
        vertices = new Vector3f[base.length+2];
        vertices[0] = top;
        vertices[vertices.length-1] = base[0];
        for(int i = 0; i < base.length; i++){
            vertices[i+1] = base[i];
        }
        this.renderer.setVertexData(3, toFloatBuffer())
        .setType(GL11.GL_TRIANGLE_FAN);
    }
    
    public Vector3f[] getVertices() {
        return vertices;
    }

    public FloatBuffer toFloatBuffer() {
        float[] tmp =  new float[vertices.length*3];
        for(int i = 0; i < vertices.length; i++){
            tmp[i*3] = vertices[i].x;
            tmp[i*3+1] = vertices[i].y;
            tmp[i*3+2] = vertices[i].z;
        }
        return BufferHelper.toFloatBuffer(tmp);
    }
}
