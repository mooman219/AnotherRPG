package com.gmail.mooman219.client.event.core;

import com.gmail.mooman219.client.event.CancellableEvent;
import com.gmail.mooman219.client.event.HandlerList;

public class KeyPressEvent extends CancellableEvent{
    private static final HandlerList handlers = new HandlerList();

    private int keyID;

    public KeyPressEvent(int id){
        keyID = id;
    }

    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
