package com.gmail.mooman219.test3D.event.core;

import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.HandlerList;

public class MouseMoveEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    private int realX;
    private int realY;
    private int realDX;
    private int realDY;

    public MouseMoveEvent(int realX, int realY, int realDX, int realDY){
        this.realX = realX;
        this.realY = realY;
        this.realDX = realDX;
        this.realDY = realDY;
    }

    public int getRealX() {
        return realX;
    }

    public int getRealY() {
        return realY;
    }

    public int getRealDX() {
        return realDX;
    }

    public int getRealDY() {
        return realDY;
    }

    public void setRealX(int realX) {
        this.realX = realX;
    }

    public void setRealY(int realY) {
        this.realY = realY;
    }

    public void setRealDX(int realDX) {
        this.realDX = realDX;
    }

    public void setRealDY(int realDY) {
        this.realDY = realDY;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
