package com.gmail.mooman219.test3D.shape.polyhedron;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Vector3f;

import com.gmail.mooman219.test3D.shape.Shape;
import com.gmail.mooman219.test3D.util.BufferHelper;

public class Hexahedron extends Shape{

    public Vector3f[] vertices;

    public Hexahedron(Vector3f a, Vector3f b, Vector3f c, Vector3f d, Vector3f e, Vector3f f, Vector3f g, Vector3f h){
        vertices = new Vector3f[]{a, b, c, d, e, f, g, h};
        this.renderer.setVertexData(3, toFloatBuffer());
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
                vertices[4].x, vertices[4].y, vertices[4].z,// E BACK
                vertices[6].x, vertices[6].y, vertices[6].z,//G
                vertices[7].x, vertices[7].y, vertices[7].z,//H
                vertices[5].x, vertices[5].y, vertices[5].z,//F
                vertices[5].x, vertices[5].y, vertices[5].z,// F LEFT
                vertices[7].x, vertices[7].y, vertices[7].z,//H
                vertices[2].x, vertices[2].y, vertices[2].z,//C
                vertices[1].x, vertices[1].y, vertices[1].z,//B
                vertices[4].x, vertices[4].y, vertices[4].z,// E RIGHT
                vertices[0].x, vertices[0].y, vertices[0].z,//A
                vertices[3].x, vertices[3].y, vertices[3].z,//D
                vertices[6].x, vertices[6].y, vertices[6].z,//G
                vertices[4].x, vertices[4].y, vertices[4].z,// E TOP
                vertices[5].x, vertices[5].y, vertices[5].z,//F
                vertices[1].x, vertices[1].y, vertices[1].z,//B
                vertices[0].x, vertices[0].y, vertices[0].z,//A
                vertices[6].x, vertices[6].y, vertices[6].z,// G BOTTOM
                vertices[3].x, vertices[3].y, vertices[3].z,//D
                vertices[2].x, vertices[2].y, vertices[2].z,//C
                vertices[7].x, vertices[7].y, vertices[7].z,//H
        };
        return BufferHelper.toFloatBuffer(tmp);
    }
}
