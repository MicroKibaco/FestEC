package com.asiainfo.latte_core.delegates.web.event;


import com.asiainfo.latte_core.util.log.Lattelogger;


public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Lattelogger.e("UndefineEvent", params);
        return null;
    }
}
