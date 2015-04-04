package com.gmail.mooman219.test3D.unused.render;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

public class DisplayList {
    private static ArrayList<DisplayList> displayLists = new ArrayList<DisplayList>();

    private int dlId = -1;
    private int mode = GL11.GL_COMPILE;
    private boolean isStarted = false;

    public DisplayList(){}

    public DisplayList(int mode){
        this.mode = mode;
    }

    public void start(){
        if(dlId == -1){
            dlId = glGenLists(1);
        }
        if(!displayLists.contains(this)){
            displayLists.add(this);
        }
        isStarted = true;
        glNewList(dlId, mode);
    }

    public void end(){
        if(isStarted){
            isStarted = false;
            glEndList();
        }
    }

    public void call(){
        if(dlId != -1 && !isStarted){
            glCallList(dlId);
        }
    }

    public void dispose() {
        if(dlId != -1){
            glDeleteLists(dlId, 1);
            displayLists.remove((Integer)dlId);
        }
    }

    public static void disposeAll(){
        for(DisplayList i:displayLists){
            i.dispose();
        }
    }
}
