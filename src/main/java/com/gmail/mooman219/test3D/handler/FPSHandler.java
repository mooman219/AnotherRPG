package com.gmail.mooman219.test3D.handler;

import org.lwjgl.opengl.Display;

import com.gmail.mooman219.test3D.AnotherClient;
import com.gmail.mooman219.test3D.event.EventHandler;
import com.gmail.mooman219.test3D.event.Listener;
import com.gmail.mooman219.test3D.event.core.TickEvent;
import com.gmail.mooman219.test3D.event.core.render.RenderFont;


public class FPSHandler implements Listener{
    public long lastFrame;
    public int fps;
    public long lastFPS;

    public FPSHandler(){
        lastFPS = AnotherClient.getTime();
        lastFrame = lastFPS;
    }

    @EventHandler
    public void onTick(TickEvent e){
        if (e.getCurrentTime() - lastFPS > 1000) {
            Display.setTitle(AnotherClient.name+" FPS: "+fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }
    
    @EventHandler
    public void onRenderFont(RenderFont e){
        double memUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
        double memMax = Runtime.getRuntime().maxMemory() / 1048576L;
        double memFree = memMax - memUsed;
        double percentageFree = 100.0D / memMax * memFree;
        
        AnotherClient.font.drawString(2, 2, "X: "+-AnotherClient.position.x);
        AnotherClient.font.drawString(2, 15, "Y: "+-AnotherClient.position.y);
        AnotherClient.font.drawString(2, 28, "Z: "+-AnotherClient.position.z);
        
        AnotherClient.font.drawString(2, 54, "RX: "+AnotherClient.rotation.x);
        AnotherClient.font.drawString(2, 67, "RY: "+AnotherClient.rotation.y);
        AnotherClient.font.drawString(2, 80, "USED: "+memUsed);
        AnotherClient.font.drawString(2, 93, "MAX: "+memMax);
        AnotherClient.font.drawString(2, 106, "FREE: "+memFree);
        AnotherClient.font.drawString(2, 119, "Percent: "+percentageFree);
    }
}
