package com.asiainfo.latte_core.net.rt;

import android.content.Context;

import com.asiainfo.latte_core.net.RestCreator;
import com.asiainfo.latte_core.net.callback.IError;
import com.asiainfo.latte_core.net.callback.IFailure;
import com.asiainfo.latte_core.net.callback.IRequest;
import com.asiainfo.latte_core.net.callback.ISuccess;
import com.asiainfo.latte_core.ui.launcher.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Restful 构建器
 */

public class RestClientBuilder {

    private static Map<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IError mIError = null;
    private IFailure mIFailure = null;
    private RequestBody mBody = null;
    private LoaderStyle mloaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder loader(Context context) {

        this.mContext = context;
        this.mloaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;

    }


    public final RestClientBuilder dir(String dir) {

        this.mDownloadDir = dir;
        return this;

    }


    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }


    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }


    public final RestClient build() {

        return new RestClient(mUrl,
                PARAMS,
                mIRequest,
                mISuccess,
                mIError,
                mIFailure,
                mBody,
                mloaderStyle,
                mContext,
                mFile,
                mDownloadDir,
                mExtension,
                mName);

    }


}
