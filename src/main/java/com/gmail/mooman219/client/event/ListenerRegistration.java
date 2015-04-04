package com.gmail.mooman219.client.event;

import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.shared.Order;

public class ListenerRegistration {
    private final EventExecutor executor;
    private final Order orderSlot;
    private final GameState state;

    public ListenerRegistration(EventExecutor executor, Order orderSlot, GameState state){
        this.executor = executor;
        this.orderSlot = orderSlot;
        this.state = state;
    }

    public EventExecutor getExecutor() {
        return executor;
    }

    public Order getOrderSlot() {
        return orderSlot;
    }

    public GameState getState() {
        return state;
    }
}
