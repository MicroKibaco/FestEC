package com.asiainfo.latte_core.delegates.web.event;


import android.support.annotation.NonNull;

import java.util.HashMap;

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public void addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }

    public static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

}
