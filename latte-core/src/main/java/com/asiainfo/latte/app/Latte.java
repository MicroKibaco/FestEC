package com.asiainfo.latte.app;

import android.content.Context;

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
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    /**
     * 获取配置项参数集
     */
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取配置管理器全局上下文
     */
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

}
