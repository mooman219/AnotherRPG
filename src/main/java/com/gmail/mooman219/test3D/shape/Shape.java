package com.gmail.mooman219.test3D.shape;

import java.nio.FloatBuffer;

import org.lwjgl.util.vector.Vector3f;

import com.gmail.mooman219.test3D.render.ShapeRenderer;

public abstract class Shape {
    public ShapeRenderer renderer;
    
    public Shape(){
        this.renderer = new ShapeRenderer();
    }
    
    public final void onRender(){
        renderer.onRender();
    }
    
    public abstract Vector3f[] getVertices();
	
    public abstract FloatBuffer toFloatBuffer();
}
