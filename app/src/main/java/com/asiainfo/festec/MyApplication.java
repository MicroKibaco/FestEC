package com.asiainfo.festec;

import android.app.Application;

import com.asiainfo.latte.app.Latte;
import com.asiainfo.latte.ec.icon.FontEcModel;
import com.asiainfo.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(2000)
                .withIcon(new FontEcModel())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
