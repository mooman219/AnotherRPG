package com.gmail.mooman219.client.event;


public abstract class CancellableEvent extends Event{
    private boolean cancelled = false;

    public CancellableEvent(){}

    public boolean isCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }
}
