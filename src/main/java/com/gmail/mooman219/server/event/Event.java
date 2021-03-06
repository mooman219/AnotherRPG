package com.gmail.mooman219.server.event;


public abstract class Event {
    private String name;

    public Event(){}

    public abstract HandlerList getHandlers();

    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }
}
