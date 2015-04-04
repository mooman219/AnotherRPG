package com.gmail.mooman219.test3D.event.core;

import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.HandlerList;

public class StartupEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public StartupEvent(){}

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
