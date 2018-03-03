package com.asiainfo.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }


    public static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

}
