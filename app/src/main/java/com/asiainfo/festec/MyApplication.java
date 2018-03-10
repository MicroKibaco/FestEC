package com.asiainfo.festec;

import android.app.Application;

import com.asiainfo.latte.app.Latte;
import com.asiainfo.latte.ec.icon.FontEcModel;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://news.baidu.com/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModel())
                .configure();
    }
}
