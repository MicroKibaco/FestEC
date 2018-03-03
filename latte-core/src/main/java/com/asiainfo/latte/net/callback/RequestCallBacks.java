package com.asiainfo.latte.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * retrofit2 请求成功,失败,错误回调
 */

public class RequestCallBacks implements Callback<String> {

    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequest REQUEST;

    public RequestCallBacks(ISuccess success,
                            IError error,
                            IFailure failure,
                            IRequest request) {
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = request;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

    }
}
