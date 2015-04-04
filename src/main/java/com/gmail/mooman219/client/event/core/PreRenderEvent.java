package com.gmail.mooman219.client.event.core;

import com.gmail.mooman219.client.event.Event;
import com.gmail.mooman219.client.event.HandlerList;

public class PreRenderEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    public PreRenderEvent(){}

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
