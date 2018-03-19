package com.asiainfo.latte_core.wechat.templates;


import com.asiainfo.latte_core.wechat.BaseWXEntryActivity;
import com.asiainfo.latte_core.wechat.LatteWeChat;

/**
 * Created by MicroKibaco on 17/03/2018.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String useInfo) {

        LatteWeChat
                .getInstance()
                .getSignInCallback()
                .onSignInSuccess(useInfo);

    }
}
