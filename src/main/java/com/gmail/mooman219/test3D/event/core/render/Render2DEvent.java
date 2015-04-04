package com.gmail.mooman219.test3D.event.core.render;

import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.HandlerList;

public class Render2DEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    public Render2DEvent(){}

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
