package com.asiainfo.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.asiainfo.latte.delegates.LatteDelegate;
import com.asiainfo.latte.net.RestClient;
import com.asiainfo.latte.net.callback.IError;
import com.asiainfo.latte.net.callback.IFailure;
import com.asiainfo.latte.net.callback.ISuccess;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public class MainDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {

        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //  Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();

    }
}
