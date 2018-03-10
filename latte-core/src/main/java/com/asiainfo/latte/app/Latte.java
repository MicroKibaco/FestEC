package com.asiainfo.latte.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * 配置项中央管理器
 */

public final class Latte {
    /**
     * 初始化配置项
     *
     * @param context 全局上下文
     * @return Configurator
     */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取配置项参数集
     * @return WeakHashMap
     */
    public static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    /**
     * 获取配置管理器全局上下文
     */
    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }

}
