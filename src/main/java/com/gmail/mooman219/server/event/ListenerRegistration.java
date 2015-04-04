package com.gmail.mooman219.server.event;

import com.gmail.mooman219.shared.Order;

public class ListenerRegistration {
    private final EventExecutor executor;
    private final Order orderSlot;

    public ListenerRegistration(EventExecutor executor, Order orderSlot){
        this.executor = executor;
        this.orderSlot = orderSlot;
    }

    public EventExecutor getExecutor() {
        return executor;
    }

    public Order getOrderSlot() {
        return orderSlot;
    }
}
