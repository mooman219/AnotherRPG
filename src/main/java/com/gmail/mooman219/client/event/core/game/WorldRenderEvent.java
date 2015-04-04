package com.gmail.mooman219.client.event.core.game;

import com.gmail.mooman219.client.event.Event;
import com.gmail.mooman219.client.event.HandlerList;

public class WorldRenderEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    public WorldRenderEvent(){}

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
