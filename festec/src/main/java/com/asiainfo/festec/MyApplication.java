package com.asiainfo.festec;

import android.app.Application;
import android.support.annotation.Nullable;

import com.asiainfo.festec.event.ShareEvent;
import com.asiainfo.festec.event.TestEvent;
import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_core.net.interceptors.DebugInterceptor;
import com.asiainfo.latte_core.util.callback.CallbackManager;
import com.asiainfo.latte_core.util.callback.CallbackType;
import com.asiainfo.latte_core.util.callback.IGlobalCallback;
import com.asiainfo.latte_ec.database.DatabaseManager;
import com.asiainfo.latte_ec.icon.FontEcModel;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;


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
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);


        // 开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallBack(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            // 开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallBack(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }
}
