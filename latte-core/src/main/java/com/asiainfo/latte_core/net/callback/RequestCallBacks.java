package com.asiainfo.latte_core.net.callback;

import android.os.Handler;

import com.asiainfo.latte_core.ui.launcher.LatteLoader;
import com.asiainfo.latte_core.ui.launcher.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * retrofit2 请求成功,失败,错误回调
 */

public class RequestCallBacks implements Callback<String> {

    private static final Handler HANDLER = new Handler();
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUEST;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallBacks(ISuccess success,
                            IError error,
                            IFailure failure,
                            IRequest request,
                            LoaderStyle style) {
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = request;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null) {
            stopLoading();
        }
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }


    private void stopLoading() {
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
