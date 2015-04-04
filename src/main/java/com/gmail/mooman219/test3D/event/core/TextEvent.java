package com.gmail.mooman219.test3D.event.core;

import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.HandlerList;

public class TextEvent extends Event{
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
