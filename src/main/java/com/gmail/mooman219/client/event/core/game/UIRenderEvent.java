package com.gmail.mooman219.client.event.core.game;

import com.gmail.mooman219.client.event.Event;
import com.gmail.mooman219.client.event.HandlerList;

public class UIRenderEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    public UIRenderEvent(){}

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
