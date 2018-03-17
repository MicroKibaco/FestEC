package com.asiainfo.latte_annotations.wechat.templates;

import com.asiainfo.latte_annotations.wechat.BaseWXEntryActivity;
import com.asiainfo.latte_annotations.wechat.LatteWeChat;

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
