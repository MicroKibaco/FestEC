package com.asiainfo.latte_core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.asiainfo.latte_core.delegates.web.event.Event;
import com.asiainfo.latte_core.delegates.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * 项目配置文件
 */

public class Configurator {
    /*
    参数集容器
     */
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();
    private ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    /**
     * 初始化配置开关OFF
     */
    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 获取配置选项的实例对象
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    final WeakHashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 打开配置选项闸
     */
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 配置选项ip路由器
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    /**
     * 检查当前虚拟配置的状态
     */
    private void checkConfiguration() {

        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);


        if (!isReady) {

            throw new RuntimeException("Configuration is not ready,call configure");

        }

    }

    /**
     * 获取指定配置类型的value值
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {

        ICONS.add(descriptor);
        return this;

    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public final Configurator withWxChatAppId(String wxChatAppId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, wxChatAppId);
        return this;
    }

    public final Configurator withWxChatAppSecret(String wxChatAppSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, wxChatAppSecret);
        return this;
    }

    private void initIcons() {

        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    /**
     * 降低包的深度,管理类的结构
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
}
