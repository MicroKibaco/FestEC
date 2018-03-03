package com.asiainfo.latte.app;

import java.util.WeakHashMap;

/**
 * 项目配置文件
 */

public class Configurator {
    /*
    参数集容器
     */
    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    /**
     * 初始化配置开关OFF
     */
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 获取配置选项的实例对象
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 打开配置选项闸
     */
    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 配置选项ip路由器
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 检查当前虚拟配置的状态
     */
    private void checkConfiguration() {

        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());

        // TODO: 待校验
        if (!isReady) {

            throw new RuntimeException("Configuration is not ready,call configure");

        }

    }

    /**
     * 获取指定配置类型的value值
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    /**
     * 降低包的深度,管理类的结构
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }


}
