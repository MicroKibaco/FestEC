package com.asiainfo.latte_core.delegates.web;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class WebViewInitializer {

    @SuppressLint("SetJavaScriptEnabled")
    WebView createWebView(WebView webView) {
        // 设置可以调试
        WebView.setWebContentsDebuggingEnabled(true);

        // 不能横向滑动
        webView.setHorizontalScrollBarEnabled(false);

        // 不允许截图
        webView.setVerticalScrollBarEnabled(false);

        // 允许截图
        webView.setDrawingCacheEnabled(true);

        // 屏长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        // 初始化Setting
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "Latte");

        // 隐藏缩放控件
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(false);

        // 静止缩放
        settings.setSupportZoom(false);

        // 文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);

        // 缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;

    }

}
