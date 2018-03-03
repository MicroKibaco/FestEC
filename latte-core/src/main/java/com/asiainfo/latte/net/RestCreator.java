package com.asiainfo.latte.net;

import com.asiainfo.latte.app.ConfigType;
import com.asiainfo.latte.app.Latte;
import com.asiainfo.latte.net.scalars.ScalarsConverterFactory;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Restful 孵化器
 */

public class RestCreator {

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RetrofitHolder.RestServiceHolder.REST_SERVICE;
    }

    // 惰性加载,持久化存储对象
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }

    /**
     * Retrofit 孵化器
     */
    private static final class RetrofitHolder {

        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit
                .Builder()
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        /**
         * OkHttp 请求连接器
         */
        private static final class OkHttpHolder {

            private static final int TIME_OUT = 60; // 超时时间

            private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient
                    .Builder()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();

        }

        /**
         * 仅限本类使用,严禁继承
         */
        private static final class RestServiceHolder {

            private static final RestService REST_SERVICE =
                    RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

        }

    }

}
