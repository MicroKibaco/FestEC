package com.asiainfo.latte.net.rt;

import android.content.Context;

import com.asiainfo.latte.net.HttpMethod;
import com.asiainfo.latte.net.RestCreator;
import com.asiainfo.latte.net.callback.IError;
import com.asiainfo.latte.net.callback.IFailure;
import com.asiainfo.latte.net.callback.IRequest;
import com.asiainfo.latte.net.callback.ISuccess;
import com.asiainfo.latte.net.callback.RequestCallBacks;
import com.asiainfo.latte.net.download.DownloadHandler;
import com.asiainfo.latte.ui.LatteLoader;
import com.asiainfo.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request, ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle style,
                      Context context,
                      File file,
                      String dir,
                      String extension,
                      String name) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
        this.FILE = file;
        this.DOWNLOAD_DIR = dir;
        this.EXTENSION = extension;
        this.NAME = name;
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
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;

            default:
                break;

        }


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

        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be not null");
            }
            request(HttpMethod.POST_RAW);
        }


    }

    public final void put() {

        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be not null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete() {

        request(HttpMethod.DELETE);

    }

    public final void upload() {

        request(HttpMethod.UPLOAD);

    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, SUCCESS, ERROR, FAILURE, DOWNLOAD_DIR, EXTENSION, NAME).handleDownload();
    }

}


