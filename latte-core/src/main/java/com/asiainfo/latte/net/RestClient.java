package com.asiainfo.latte.net;

import com.asiainfo.latte.net.callback.IError;
import com.asiainfo.latte.net.callback.IFailure;
import com.asiainfo.latte.net.callback.IRequest;
import com.asiainfo.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * 请求的具体实现类
 */

public class RestClient {


    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request, ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
}
