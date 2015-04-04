package com.gmail.mooman219.test3D.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import com.gmail.mooman219.test3D.enums.BufferType;

public class VertexBuffer {
    private static ArrayList<VertexBuffer> activeVertexBuffers = new ArrayList<VertexBuffer>();
    
    public int usage = GL_STATIC_DRAW;
    private int size = 0;
    private int vboId = -1;
    private BufferType type;
    
    public VertexBuffer(){}
    
    public VertexBuffer(int usage){
        this.usage = usage;
    }
    
    public void flush(BufferType type, FloatBuffer buffer){
        if(vboId == -1){
            vboId = glGenBuffers();
            activeVertexBuffers.add(this);
        }
        this.size = buffer.capacity();
        this.type = type;
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, buffer, usage);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    public void bind(){
        if(vboId == -1){
            return;
        }
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
    }
    
    public void call(){
    	if(vboId == -1){
            return;
        }
    	glBindBuffer(GL_ARRAY_BUFFER, vboId);
    	switch(type){
    	case COLOR:
    	    glColorPointer(3, GL_FLOAT, 0, 0L);
    		break;
    	case VERTEX:
    	    glVertexPointer(3, GL_FLOAT, 0, 0L);
    		break;
    	case TEXCORD:
    	    glTexCoordPointer(2, GL_FLOAT, 0, 0L);
    		break;
    	}
    }
    
    public int getId(){
        return vboId;
    }
    
    public int getSize(){
        return size;
    }
    
    public void dispose() {
        if(vboId != -1){
            glDeleteBuffers(vboId);
            activeVertexBuffers.remove((Integer)vboId);
            vboId = -1;
            usage = GL_STATIC_DRAW;
        }
    }
    
    public static void disposeAll(){
        for(VertexBuffer i:activeVertexBuffers){
            i.dispose();
        }
    }
}
