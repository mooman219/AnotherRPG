package com.gmail.mooman219.client.handler;

import org.lwjgl.opengl.Display;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.TickEvent;


public class FPSHandler implements Listener{
    public long lastFrame;
    public int fps;
    public long lastFPS;

    public FPSHandler(){
        lastFPS = AnotherRPG.getTime();
        lastFrame = lastFPS;
    }

    @EventHandler
    public void onTick(TickEvent e){
        if (e.getCurrentTime() - lastFPS > 1000) {
            Display.setTitle(AnotherRPG.name + " FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }
}
