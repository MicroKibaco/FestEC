package com.asiainfo.latte.net;

import android.content.Context;
import android.util.Log;

import com.asiainfo.latte.net.callback.IError;
import com.asiainfo.latte.net.callback.IFailure;
import com.asiainfo.latte.net.callback.IRequest;
import com.asiainfo.latte.net.callback.ISuccess;
import com.asiainfo.latte.net.callback.RequestCallBacks;
import com.asiainfo.latte.ui.LatteLoader;
import com.asiainfo.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Restful请求的具体实现类
 */

public class RestClient {


    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request, ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle style,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    /**
     * request 请求产业园
     */
    private void request(HttpMethod method) {

        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {

            REQUEST.onRequestStart();

        }

        if (LOADER_STYLE != null) {

            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);

        }


        switch (method) {

            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;

        }

        Log.e("URL", URL);


        if (call != null) {

            call.enqueue(getRequestCallback());

        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBacks(
                SUCCESS,
                ERROR,
                FAILURE,
                REQUEST,
                LOADER_STYLE
        );
    }


    public final void get() {

        request(HttpMethod.GET);

    }

    public final void post() {

        request(HttpMethod.POST);

    }

    public final void put() {

        request(HttpMethod.PUT);

    }

    public final void delete() {

        request(HttpMethod.DELETE);

    }

}


