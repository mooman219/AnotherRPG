package com.gmail.mooman219.client.event.core.game;

import com.gmail.mooman219.client.event.Event;
import com.gmail.mooman219.client.event.HandlerList;

public class ConnectionInfoEvent extends Event{
    private static final HandlerList handlers = new HandlerList();

    private String infoMessage;

    public ConnectionInfoEvent(String message){
        this.infoMessage = message;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
