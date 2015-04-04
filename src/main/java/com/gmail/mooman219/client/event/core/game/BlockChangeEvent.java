package com.gmail.mooman219.client.event.core.game;

import com.gmail.mooman219.client.event.CancellableEvent;
import com.gmail.mooman219.client.event.HandlerList;
import com.gmail.mooman219.client.geo.Block;

public class BlockChangeEvent extends CancellableEvent{
    private static final HandlerList handlers = new HandlerList();

    private Block b;

    public BlockChangeEvent(Block b) {
        this.b = b;
    }

    public Block getBlock() {
        return b;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
