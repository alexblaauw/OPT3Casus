package com.dl630.casus.core;

import java.util.HashMap;

public class EventSubscriber {
    public static HashMap<EventListener, String> listeners = new HashMap<>();

    public static void subscribeEvent(EventListener eventListener, String eventId) {
        listeners.put(eventListener, eventId);
    }

    public static Boolean unsubscribeEvent(EventListener eventListener) {
        for (EventListener subscribedListener : listeners.keySet()) {
            if (subscribedListener == eventListener) {
                listeners.remove(subscribedListener);
                return true;
            }
        }

        return false;
    }

    public static void broadcastEvent(String eventId) {
        for (EventListener subscribedListener : listeners.keySet()) {
            if (listeners.get(subscribedListener).equals(eventId)) subscribedListener.onCallback();
        }
    }
}
