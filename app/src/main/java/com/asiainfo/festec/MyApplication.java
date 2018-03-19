package com.asiainfo.festec;

import android.app.Application;

import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_core.net.interceptors.DebugInterceptor;
import com.asiainfo.latte_ec.database.DatabaseManager;
import com.asiainfo.latte_ec.icon.FontEcModel;
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
                .withWxChatAppId("WE_CHAT_APP_ID")
                .withWxChatAppSecret("WE_CHAT_APP_SECRET")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
