package com.gmail.mooman219.server.event.core;

import com.gmail.mooman219.server.event.CancellableEvent;
import com.gmail.mooman219.server.event.HandlerList;
import com.gmail.mooman219.shared.geo.GenericBlock;

public class BlockChangeEvent extends CancellableEvent{
    private static final HandlerList handlers = new HandlerList();

    private GenericBlock newBlock;

    public BlockChangeEvent(GenericBlock newBlock){
        this.newBlock = newBlock;
    }

    public GenericBlock getNewBlock() {
        return newBlock;
    }

    public void setNewBlock(GenericBlock newBlock) {
        this.newBlock = newBlock;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
