package com.asiainfo.latte_core.delegates.web;

/**
 * 这个类原生和JS交互
 */

public class LatteWebInterface {

    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }
}
