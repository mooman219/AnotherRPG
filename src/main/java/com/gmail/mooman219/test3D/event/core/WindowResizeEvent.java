package com.gmail.mooman219.test3D.event.core;

import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.HandlerList;

public class WindowResizeEvent extends Event{
    private static final HandlerList handlers = new HandlerList();
    private int newHeight;
    private int newWidth;

    public WindowResizeEvent(int newHeight, int newWidth) {
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
