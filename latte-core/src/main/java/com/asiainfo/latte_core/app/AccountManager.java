package com.asiainfo.latte_core.app;

import com.asiainfo.latte_core.util.storage.LattePreference;

/**
 * 管理用户信息
 */

public class AccountManager {

    // 保存用户登录状态,登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    // 获取当前用户的登录状态
    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    private enum SignTag {
        SIGN_TAG
    }

}
