package com.dl630.casus.core;

import com.dl630.casus.Main;

import java.util.HashMap;

public class EventSubscriber {
    public HashMap<EventListener, String> listeners = new HashMap<>();

    public void subscribeEvent(EventListener eventListener, String eventId) {
        listeners.put(eventListener, eventId);
    }

    public Boolean unsubscribeEvent(EventListener eventListener) {
        for (EventListener subscribedListener : listeners.keySet()) {
            if (subscribedListener == eventListener) {
                listeners.remove(subscribedListener);
                return true;
            }
        }

        return false;
    }

    public void broadcastEvent(String eventId) {
        for (EventListener subscribedListener : listeners.keySet()) {
            if (listeners.get(subscribedListener).equals(eventId)) subscribedListener.onCallback();
        }
    }

    public static void broadcastEventGlobal(String eventId) {
        for (Session session : Main.getSessions()) {
            session.getEventSubscriber().broadcastEvent(eventId);
        }
    }
}
