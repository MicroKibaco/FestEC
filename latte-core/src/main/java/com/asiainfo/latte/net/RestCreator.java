package com.asiainfo.latte.net;

import com.asiainfo.latte.app.ConfigType;
import com.asiainfo.latte.app.Latte;
import com.asiainfo.latte.net.scalars.ScalarsConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public class RestCreator {


    public static RestService getRestService() {
        return RetrofitHolder.RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {

        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit
                .Builder()
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        private static final class OkHttpHolder {

            private static final int TIME_OUT = 60;

            private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient
                    .Builder()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();

        }


        private static final class RestServiceHolder {

            private static final RestService REST_SERVICE =
                    RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

        }

    }

}
