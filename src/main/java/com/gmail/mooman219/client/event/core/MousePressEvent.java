package com.gmail.mooman219.client.event.core;

import com.gmail.mooman219.client.event.CancellableEvent;
import com.gmail.mooman219.client.event.HandlerList;

public class MousePressEvent extends CancellableEvent{
    private static final HandlerList handlers = new HandlerList();

    private int id;
    private int x;
    private int y;

    public MousePressEvent(int id, int  x, int  y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
