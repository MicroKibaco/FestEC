package com.asiainfo.latte_annotations.net.download;

import android.os.AsyncTask;

import com.asiainfo.latte_annotations.net.RestCreator;
import com.asiainfo.latte_annotations.net.callback.IError;
import com.asiainfo.latte_annotations.net.callback.IFailure;
import com.asiainfo.latte_annotations.net.callback.IRequest;
import com.asiainfo.latte_annotations.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MicroKibaco on 06/03/2018.
 */

public class DownloadHandler {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IError error,
                           IFailure failure,
                           String download_dir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    /**
     *
     */
    public final void handleDownload() {

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {

                    final ResponseBody body = response.body();
                    final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, body, NAME);

                    // 这里一定要注意判断,否则文件下载不全
                    if (task.isCancelled()) {

                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        } else {

                            if (ERROR != null) {

                                ERROR.onError(response.code(), response.message());

                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });

    }

}
