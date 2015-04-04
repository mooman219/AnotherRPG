package com.gmail.mooman219.client.event.core;

import com.gmail.mooman219.client.event.CancellableEvent;
import com.gmail.mooman219.client.event.HandlerList;

public class TextEvent extends CancellableEvent{
    private static final HandlerList handlers = new HandlerList();

    private String input;

    public TextEvent(String input){
        this.input = input;
    }

    public TextEvent(char input){
        this.input = input+"";
    }

    public String getInput() {
        return input;
    }

    public void setInput(String newInput){
        input = newInput;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
