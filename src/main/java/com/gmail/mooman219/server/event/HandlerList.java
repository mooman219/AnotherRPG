package com.gmail.mooman219.server.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

import com.gmail.mooman219.shared.Order;


public class HandlerList{

    private final EnumMap<Order, ArrayList<ListenerRegistration>> handlerSlots;

    public HandlerList(){
        handlerSlots = new EnumMap<Order, ArrayList<ListenerRegistration>>(Order.class);
        for (Order priority : Order.values()) {
            handlerSlots.put(priority, new ArrayList<ListenerRegistration>());
        }
    }

    public ArrayList<ListenerRegistration> getSorted(){
        ArrayList<ListenerRegistration> temp = new ArrayList<ListenerRegistration>();
        for(ArrayList<ListenerRegistration> set : handlerSlots.values()){
            temp.addAll(set);
        }
        return temp;
    }

    public void register(ListenerRegistration listener) {
        if (handlerSlots.get(listener.getOrderSlot()).contains(listener)) {
            System.out.println(("This listener is already registered to priority " + listener.getOrderSlot().toString()));
        }
        handlerSlots.get(listener.getOrderSlot()).add(listener);
    }

    public void registerAll(Collection<ListenerRegistration> listeners) {
        for (ListenerRegistration listener : listeners) {
            register(listener);
        }
    }

    public void unregister(ListenerRegistration listener) {
        if (handlerSlots.get(listener.getOrderSlot()).contains(listener)) {
            handlerSlots.get(listener.getOrderSlot()).remove(listener);
        }
    }

    public void unregisterAll(Collection<ListenerRegistration> listeners) {
        for (ListenerRegistration listener : listeners) {
            unregister(listener);
        }
    }
}
