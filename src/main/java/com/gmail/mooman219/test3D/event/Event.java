package com.gmail.mooman219.test3D.event;


public abstract class Event {
    private String name;
    private boolean cancelled = false;

    public Event(){}

    public abstract HandlerList getHandlers();

    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    public boolean isCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }
}
