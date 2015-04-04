package com.gmail.mooman219.test3D.render;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import com.gmail.mooman219.test3D.util.BufferHelper;

public class ShapeRenderer {
    private Runnable render;
    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    private FloatBuffer textureBuffer;
    private int type = GL_QUADS;
    private int texture = 0;
    private int vetexDimension = 3;
    private int colorDimension = 3;
    
    public ShapeRenderer(){
        render = new Runnable(){
            public void run() {}
        };
    }

    public ShapeRenderer setVertexData(int vetexDimension, FloatBuffer vertexdata){
        this.vertexBuffer = vertexdata;
        this.vetexDimension = vetexDimension;
        updateRenderer();
        return this;
    }

    public ShapeRenderer setColorData(int colorDimension, FloatBuffer colordata){
        this.colorBuffer = colordata;
        this.colorDimension = colorDimension;
        updateRenderer();
        return this;
    }
    
    public ShapeRenderer setColorData(FloatBuffer colordata){
        return setColorData(3, colordata);
    }

    public ShapeRenderer setTextureData(FloatBuffer texturedata){
        this.textureBuffer = texturedata;
        updateRenderer();
        return this;
    }
    
    public ShapeRenderer setType(int type){
        this.type = type;
        updateRenderer();
        return this;
    }

    public ShapeRenderer setTexture(int texture){
        this.texture = texture;
        updateRenderer();
        return this;
    }

    public void updateRenderer(){
        if(colorBuffer != null && textureBuffer != null){
            render = new Runnable(){
                public void run() {
                    glBindTexture(GL_TEXTURE_2D, texture);
                    glTexCoordPointer(2, 0, textureBuffer);
                    glVertexPointer(vetexDimension, 0, vertexBuffer);
                    glColorPointer(colorDimension, 0, colorBuffer);
                    glDrawArrays(type, 0, vertexBuffer.capacity()/vetexDimension);
                }
            };
        }else if(colorBuffer != null){
            render = new Runnable(){
                public void run() {
                    glBindTexture(GL_TEXTURE_2D, texture);
                    glVertexPointer(vetexDimension, 0, vertexBuffer);
                    glColorPointer(colorDimension, 0, colorBuffer);
                    glDrawArrays(type, 0, vertexBuffer.capacity()/vetexDimension);
                }
            };
        }else if(textureBuffer != null){
            render = new Runnable(){
                private FloatBuffer tColorData = BufferHelper.stack(new float[]{1, 1, 1}, vertexBuffer.capacity()/vetexDimension);
                public void run() {
                    glBindTexture(GL_TEXTURE_2D, texture);
                    glTexCoordPointer(2, 0, textureBuffer);
                    glVertexPointer(vetexDimension, 0, vertexBuffer);
                    glColorPointer(3, 0, tColorData);
                    glDrawArrays(type, 0, vertexBuffer.capacity()/vetexDimension);
                }
            };
        }else{
            render = new Runnable(){
                private FloatBuffer tColorData = BufferHelper.stack(new float[]{1, 1, 1}, vertexBuffer.capacity()/vetexDimension);
                public void run() {
                    glBindTexture(GL_TEXTURE_2D, texture);
                    glVertexPointer(vetexDimension, 0, vertexBuffer);
                    glColorPointer(3, 0, tColorData);
                    glDrawArrays(type, 0, vertexBuffer.capacity()/vetexDimension);
                }
            };
        }
    }

    public void onRender(){
        render.run();
    }
}
