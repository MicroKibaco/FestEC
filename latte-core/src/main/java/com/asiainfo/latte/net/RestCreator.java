package com.asiainfo.latte.net;

import com.asiainfo.latte.app.ConfigKeys;
import com.asiainfo.latte.app.Latte;
import com.asiainfo.latte.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    public static RxRestService getRxRestService() {
        return RetrofitHolder.RxRestSeiceHolder.REST_SERVICE;

    }

    // 惰性加载,持久化存储对象
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }

    /**
     * Retrofit 孵化器
     */
    private static final class RetrofitHolder {

        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);

        private static final Retrofit RETROFIT_CLIENT = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        /**
         * OkHttp 请求连接器
         */
        private static final class OkHttpHolder {

            private static final int TIME_OUT = 60; // 超时时间
            private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
            private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);
            private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();

            private static OkHttpClient.Builder addInterceptor() {
                if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                    for (Interceptor interceptor : INTERCEPTORS) {
                        BUILDER.addInterceptor(interceptor);
                    }
                }
                return BUILDER;
            }

        }

        /**
         * 仅限本类使用,严禁继承
         */
        private static final class RestServiceHolder {

            private static final RestService REST_SERVICE =
                    RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

        }

        /**
         * 仅限本类使用,严禁继承
         */
        private static final class RxRestSeiceHolder {

            private static final RxRestService REST_SERVICE =
                    RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);

        }

    }

}
