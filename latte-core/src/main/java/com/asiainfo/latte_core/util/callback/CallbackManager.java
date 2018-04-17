package com.asiainfo.latte_core.util.callback;

import java.util.WeakHashMap;

public class CallbackManager {
    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }
}
