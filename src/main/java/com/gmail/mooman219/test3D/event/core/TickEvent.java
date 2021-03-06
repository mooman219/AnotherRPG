package com.gmail.mooman219.test3D.event.core;

import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.HandlerList;

public class TickEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    private long currentTime;
    private long deltaTime;

    public TickEvent(long currentTime, long deltaTime){
        this.currentTime = currentTime;
        this.deltaTime = deltaTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
