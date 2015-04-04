package com.gmail.mooman219.client.event.core.game;

import com.gmail.mooman219.client.event.Event;
import com.gmail.mooman219.client.event.HandlerList;


public class MouseBlockMoveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled = false;

    private int blockX;
    private int blockY;
    private int blockDX;
    private int blockDY;

    public MouseBlockMoveEvent(int blockX, int blockY, int blockDX, int blockDY){
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockDX = blockDX;
        this.blockDY = blockDY;
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockDX() {
        return blockDX;
    }

    public int getBlockDY() {
        return blockDY;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

    public void setBlockDX(int blockDX) {
        this.blockDX = blockDX;
    }

    public void setBlockDY(int blockDY) {
        this.blockDY = blockDY;
    }

    public boolean isCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
