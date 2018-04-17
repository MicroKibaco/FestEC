package com.asiainfo.latte_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_core.delegates.IPageLoadListener;
import com.asiainfo.latte_core.delegates.web.WebDelegate;
import com.asiainfo.latte_core.delegates.web.route.Router;
import com.asiainfo.latte_core.ui.loader.LatteLoader;
import com.asiainfo.latte_core.util.log.LatteLogger;

/**
 * 实现类
 */
public class WebViewClientImpl extends WebViewClient {

    private static final Handler HANDLER = Latte.getHandler();
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.e("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}