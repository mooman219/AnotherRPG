package com.gmail.mooman219.test3D.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gmail.mooman219.shared.Order;
import com.gmail.mooman219.test3D.enums.GameState;
import com.gmail.mooman219.test3D.event.Event;
import com.gmail.mooman219.test3D.event.EventExecutor;
import com.gmail.mooman219.test3D.event.EventHandler;
import com.gmail.mooman219.test3D.event.HandlerList;
import com.gmail.mooman219.test3D.event.Listener;
import com.gmail.mooman219.test3D.event.ListenerRegistration;


public class EventManager{

    public EventManager(){}

    public static void registerEvents(Listener listener) {
        for (Map.Entry<Class<? extends Event>, Set<ListenerRegistration>> entry : createRegisteredListeners(listener).entrySet()) {
            Class<? extends Event> delegatedClass = getRegistrationClass(entry.getKey());
            if (!entry.getKey().equals(delegatedClass)) {
                System.out.println("Attempted to register delegated event class " + entry.getKey() + ". Should be using " + delegatedClass + ".");
                continue;
            }
            getEventListeners(delegatedClass).registerAll(entry.getValue());
        }
    }

    public static void registerEvent(Class<? extends Event> event, Order priority, GameState state, EventExecutor executor) {
        getEventListeners(event).register(new ListenerRegistration(executor, priority, state));
    }

    public static <T extends Event> T callEvent(T event) {
        HandlerList handlers = event.getHandlers();
        ArrayList<ListenerRegistration> listeners = handlers.getSorted();

        if (listeners != null) {
            for (ListenerRegistration listener : listeners) {
                if(StateManager.isCurrentState(listener.getState())){
                    if(!event.isCancelled() || listener.getOrderSlot().ignoreCancelled){
                        try {
                            listener.getExecutor().execute(event);
                        } catch (Throwable ex) {
                            System.out.println("Could not pass event " + event.getEventName() + " to " + listener.getClass().getName());
                        }
                    }
                }
            }
        }
        return event;
    }

    private static HandlerList getEventListeners(Class<? extends Event> type) {
        try {
            Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
            method.setAccessible(true);
            return (HandlerList) method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Class<? extends Event> getRegistrationClass(Class<? extends Event> classy) {
        try {
            classy.getDeclaredMethod("getHandlerList");
            return classy;
        } catch (NoSuchMethodException e) {
            if (classy.getSuperclass() != null && !classy.getSuperclass().equals(Event.class) && Event.class.isAssignableFrom(classy.getSuperclass())) {
                return getRegistrationClass(classy.getSuperclass().asSubclass(Event.class));
            } else {
                System.out.println("Unable to find handler list for event " + classy.getName());
                return null;
            }
        }
    }

    private static Map<Class<? extends Event>, Set<ListenerRegistration>> createRegisteredListeners(final Listener listener) {
        Map<Class<? extends Event>, Set<ListenerRegistration>> ret = new HashMap<Class<? extends Event>, Set<ListenerRegistration>>();
        List<Method> methods = new ArrayList<Method>();
        Class<?> listenerClass = listener.getClass();
        while (listenerClass != null && !listenerClass.equals(Object.class) && !listenerClass.equals(Listener.class)) {
            try {
                methods.addAll(Arrays.asList(listenerClass.getDeclaredMethods()));
            } catch (NoClassDefFoundError e) {
                return ret;
            }
            listenerClass = listenerClass.getSuperclass();
        }
        for (final Method method : methods) {
            final EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null) {
                continue;
            }
            final Class<?> checkClass = method.getParameterTypes()[0];
            Class<? extends Event> eventClass;
            if (!Event.class.isAssignableFrom(checkClass) || method.getParameterTypes().length != 1) {
                System.out.println("Wrong method arguments used for event type registered");
                continue;
            } else {
                eventClass = checkClass.asSubclass(Event.class);
            }
            method.setAccessible(true);
            Set<ListenerRegistration> eventSet = ret.get(eventClass);
            if (eventSet == null) {
                eventSet = new HashSet<ListenerRegistration>();
                ret.put(eventClass, eventSet);
            }
            eventSet.add(new ListenerRegistration(new EventExecutor() {

                public void execute(Event event) {
                    try {
                        if (!checkClass.isAssignableFrom(event.getClass())) {
                            System.out.println("Wrong event type passed to registered method");
                            return;
                        }
                        method.invoke(listener, event);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }

            }, eh.order(), eh.state()));
        }
        return ret;
    }
}
