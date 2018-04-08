package com.asiainfo.latte_core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_core.delegates.web.WebDelegate;
import com.asiainfo.latte_core.delegates.web.WebDelegateImpl;

/**
 * 路由类做跳转
 */

public class Router {

    private Router() {
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String uri) {

        //如果是电话协议
        if (uri.contains("tel:")) {
            callPhone(delegate.getContext(), uri);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(uri);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null！");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void callPhone(Context context, String uri) {

        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

}

