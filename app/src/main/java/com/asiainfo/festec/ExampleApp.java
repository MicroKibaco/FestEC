package com.asiainfo.festec;

import android.app.Application;

import com.asiainfo.latte.app.Latte;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .configure();
    }
}
