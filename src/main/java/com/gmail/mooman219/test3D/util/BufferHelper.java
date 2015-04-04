package com.gmail.mooman219.test3D.util;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;

public class BufferHelper {
    public static FloatBuffer stack(float[] base, int times){
        float[] tmp = new float[base.length*times];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = base[i%base.length];
        }
        return toFloatBuffer(tmp);
    }

    public static FloatBuffer toFloatBuffer(double[] base){
        float[] to = new float[base.length];
        for(int i = 0; i < to.length; i++){
            to[i] = (float)base[i];
        }
        return toFloatBuffer(to);
    }

    public static FloatBuffer toFloatBuffer(float[] base){
        FloatBuffer data = BufferUtils.createFloatBuffer(base.length);
        data.put(base);
        data.flip();
        return data;
    }

    public static FloatBuffer toRectangle(Vector2f topRight, Vector2f bottomLeft){
        return toQuadrilateral(
                new Vector2f(topRight.x, topRight.y),
                new Vector2f(bottomLeft.x, topRight.y),
                new Vector2f(bottomLeft.x, bottomLeft.y),
                new Vector2f(topRight.x, bottomLeft.y)
                );
    }

    public static FloatBuffer toQuadrilateral(Vector2f a, Vector2f b, Vector2f c, Vector2f d){
        float[] tmp =  new float[]{
                a.x, a.y,// A FROUNT
                b.x, b.y,//B
                c.x, c.y,//C
                d.x, d.y,//D
                };
        return toFloatBuffer(tmp);
    }
}
