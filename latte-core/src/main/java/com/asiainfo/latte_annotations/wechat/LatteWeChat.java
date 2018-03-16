package com.asiainfo.latte_annotations.wechat;

import android.app.Activity;

import com.asiainfo.latte_annotations.app.ConfigKeys;
import com.asiainfo.latte_annotations.app.Latte;
import com.asiainfo.latte_annotations.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by MicroKibaco on 16/03/2018.
 */

public class LatteWeChat {
    public static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback signInCallback = null;

    public LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public static final LatteWeChat getInstance() {
        return Holder.INSTANCE;
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return signInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback weChatSignInCallback) {
        this.signInCallback = weChatSignInCallback;
        return this;
    }

    private static final class Holder {
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }
}
